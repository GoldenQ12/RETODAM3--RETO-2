Imports System.Data.SqlClient
Imports System.IO
Imports System.Net
Imports System.Net.Http
Imports System.Text
Imports System.Threading
Imports System.Xml
Imports Google.Apis.Auth.OAuth2
Imports Google.Apis.Drive.v3
Imports Google.Apis.Services
Imports Google.Apis.Util.Store
Imports Newtonsoft.Json
Imports Newtonsoft.Json.Linq

Public Class TerrazaClima
    Private Function LoadMap(ByVal city As String) As (Double, Double)
        Dim url As String = $"https://api.opencagedata.com/geocode/v1/json?q={city}&key=03c3a12972df43628ab0e6bd06d44f64"
        Dim request As HttpWebRequest = CType(WebRequest.Create(url), HttpWebRequest)
        request.Method = "GET"

        Try
            Using response As HttpWebResponse = CType(request.GetResponse(), HttpWebResponse)
                Using reader As New StreamReader(response.GetResponseStream())
                    Dim jsonResponse As String = reader.ReadToEnd()

                    jsonResponse.Replace(",", ".")

                    Dim jsonObject As JObject = JObject.Parse(jsonResponse)

                    If jsonObject("total_results") = "0" Then
                        MsgBox($"Lo siento, pero no existen datos para la ciudad {city}")
                        Return (0, 0)
                    Else
                        Dim lat As Double = jsonObject("results")(0)("geometry")("lat")
                        Dim lng As Double = jsonObject("results")(0)("geometry")("lng")
                        Return (lat, lng)
                    End If


                End Using
            End Using

        Catch ex As WebException
            Console.WriteLine("Error: " & ex.Message)
            Return (0, 0)
        End Try

    End Function

    Public Sub XmlToSQL()
        ' Path to the XML file
        Dim relativePath As String = "weather_data.xml"

        ' Check if the file exists
        If Not File.Exists(relativePath) Then
            Console.WriteLine("File not found: " & relativePath)
            Return
        End If

        ' Read the content of the XML file
        Dim xmlContent As String = File.ReadAllText(relativePath)

        ' Define the XAMPP server URL (e.g., pointing to a PHP script for handling uploads)
        Dim serverUrl As String = "http://localhost/upload.php"

        Try
            ' Create an HttpClient instance
            Using client As New HttpClient()
                ' Create a MultipartFormDataContent object to hold the file data
                Using formData As New MultipartFormDataContent()
                    ' Add the XML file as a ByteArrayContent
                    Dim fileContent As New ByteArrayContent(Encoding.UTF8.GetBytes(xmlContent))
                    fileContent.Headers.ContentType = New System.Net.Http.Headers.MediaTypeHeaderValue("application/xml")
                    formData.Add(fileContent, "file", Path.GetFileName(relativePath))

                    ' Send the POST request to the server
                    Dim response As HttpResponseMessage = client.PostAsync(serverUrl, formData).Result

                    ' Check the response
                    If response.IsSuccessStatusCode Then
                        MsgBox("File uploaded successfully.")
                    Else
                        MsgBox("File upload failed. Status: " & response.StatusCode)
                    End If
                End Using
            End Using
        Catch ex As Exception
            MsgBox("An error occurred: " & ex.Message)
        End Try
    End Sub


    Sub UploadFile(service As DriveService, filePath As String, folderId As String)
        Dim fileName As String = Path.GetFileName(filePath)

        ' Check if a file with the same name exists in the folder
        Dim query As String = $"name = '{fileName}' and '{folderId}' in parents and trashed = false"
        Dim listRequest = service.Files.List()
        listRequest.Q = query
        listRequest.Fields = "files(id, name)"
        Dim existingFiles = listRequest.Execute().Files

        ' Delete existing file if found
        If existingFiles IsNot Nothing AndAlso existingFiles.Count > 0 Then
            For Each file In existingFiles
                service.Files.Delete(file.Id).Execute()
                Console.WriteLine($"Deleted existing file: {file.Name}")
            Next
        End If

        ' Upload the new file
        Dim fileMetadata = New Data.File() With {
            .Name = fileName,
            .Parents = New List(Of String) From {folderId}
        }
        Using fileStream = New FileStream(filePath, FileMode.Open, FileAccess.Read)
            Dim uploadRequest = service.Files.Create(fileMetadata, fileStream, "application/octet-stream")
            uploadRequest.Fields = "id"
            Dim uploadedFile = uploadRequest.Upload()
            If uploadedFile.Status = Google.Apis.Upload.UploadStatus.Completed Then
                Console.WriteLine($"Uploaded file: {fileName}")
            Else
                Console.WriteLine($"Failed to upload file: {fileName}")
            End If
        End Using
    End Sub

    Private Async Function GetWeatherData(apiUrl As String) As Task
        Using client As New HttpClient()
            Try

                Dim response As HttpResponseMessage = Await client.GetAsync(apiUrl)
                response.EnsureSuccessStatusCode()

                Dim responseBody As String = Await response.Content.ReadAsStringAsync()

                ' Convert JSON to XML
                Dim xmlData As String = JsonConvertToXml(responseBody)

                ' Specify the directory and file path where you want to save the XML data
                Dim filePath As String = "weather_data.xml"

                File.WriteAllText(filePath, xmlData)

                XmlToSQL()

                MessageBox.Show($"Guardado correctamente")

            Catch ex As Exception
                MessageBox.Show($" {ex.Message}")
            End Try
        End Using
    End Function

    Private Function JsonConvertToXml(json As String) As String
        ' Convert JSON string to XML string
        Dim xmlDoc As New System.Xml.XmlDocument()
        xmlDoc.LoadXml(JsonConvert.DeserializeXmlNode(json, "Root").OuterXml)
        Return xmlDoc.OuterXml
    End Function

    Private Sub Form1_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        Me.CenterToScreen()


    End Sub

    Private Sub Button1_Click(sender As Object, e As EventArgs) Handles Button1.Click
        Dim city As String = TextBox1.Text
        Dim coords As (Double, Double) = LoadMap(city)
        Dim lat As String = coords.Item1.ToString.Replace(",", ".")
        Dim lng As String = coords.Item2.ToString.Replace(",", ".")
        If lat <> "0" Or lng <> "0" Then
            Dim apiURL As String = $"https://api.open-meteo.com/v1/forecast?latitude={lat}&longitude={lng}&current=temperature_2m,apparent_temperature&hourly=temperature_2m,rain,snowfall&daily=weather_code,temperature_2m_max,temperature_2m_min&timezone=GMT&forecast_days=1"
            Dim task = GetWeatherData(apiURL)
        End If
    End Sub

    Private Sub TextBox1_TextChanged(sender As Object, e As EventArgs) Handles TextBox1.TextChanged
        Label2.Text = TextBox1.Text
    End Sub
End Class