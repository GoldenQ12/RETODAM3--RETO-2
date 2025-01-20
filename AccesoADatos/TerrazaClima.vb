Imports System.Data.SqlClient
Imports System.IO
Imports System.Net
Imports System.Net.Http
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


        Dim xmlContent As String = File.ReadAllText(relativePath)
        Dim Scopes As String() = {DriveService.Scope.DriveFile}
        Dim ApplicationName As String = "Desktop App"
        Dim credential As UserCredential

        Using stream = New FileStream("credentials.json", FileMode.Open, FileAccess.Read)
            Dim credPath As String = "token.json"
            credential = GoogleWebAuthorizationBroker.AuthorizeAsync(
                GoogleClientSecrets.FromStream(stream).Secrets,
                Scopes,
                "user",
                CancellationToken.None,
                New FileDataStore(credPath, True)).Result
            Console.WriteLine("Credential file saved to: " & credPath)
        End Using

        Dim service As New DriveService(New BaseClientService.Initializer() With {
            .HttpClientInitializer = credential,
            .ApplicationName = ApplicationName
        })

        ' Upload a file
        UploadFile(service, relativePath, "1z5bUxXp1oKklz1OfDLx93mGhpwXd0kE3")

    End Sub

    Sub UploadFile(service As DriveService, filePath As String, folderId As String)
        Dim fileMetadata As New Google.Apis.Drive.v3.Data.File() With {
        .Name = Path.GetFileName(filePath),
        .Parents = New List(Of String) From {folderId} ' Set the parent folder ID
    }

        Using stream = New FileStream(filePath, FileMode.Open)
            Dim request = service.Files.Create(fileMetadata, stream, "text/plain")
            request.Fields = "id"
            Dim file = request.Upload()

            If file.Status = Google.Apis.Upload.UploadStatus.Completed Then
                Console.WriteLine("File ID: " & request.ResponseBody.Id)
            Else
                Console.WriteLine("Error uploading file.")
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