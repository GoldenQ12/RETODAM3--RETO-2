﻿Imports System.Data.SqlClient
Imports System.IO
Imports System.Net
Imports System.Net.Http
Imports System.Xml
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

        ' Combine the relative path with the current directory
        Dim xmlFilePath As String = Path.Combine(Directory.GetCurrentDirectory(), relativePath)

        ' Create the XML file
        Using fs As FileStream = File.Create(xmlFilePath)
            ' Optionally, you can write some initial content to the file here
        End Using

        ' Create the XML file
        Using fs As FileStream = File.Create(xmlFilePath)
            ' Optionally, you can write some initial content to the file here
        End Using

        Dim xmlContent As String = File.ReadAllText(xmlFilePath)

        ' Connection string to your SQL Server database
        Dim connectionString As String = "Server=PC-ALEXANDER;Database=Reto;user=user;Password=root;"

        Dim insertQuery As String = "INSERT INTO weather (_XML) VALUES(@XML)"
        ' Create a new XmlDocument
        Using connection As New SqlConnection(connectionString)
            connection.Open()



            ' Prepare the SQL insert command
            Using command As New SqlCommand(insertQuery, connection)
                ' Add the XML content as a parameter
                command.Parameters.AddWithValue("@XML", xmlContent)

                ' Execute the insert command
                command.ExecuteNonQuery()
            End Using
        End Using

        Console.WriteLine("Data inserted successfully.")
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
            XmlToSQL()
        End If
    End Sub

    Private Sub TextBox1_TextChanged(sender As Object, e As EventArgs) Handles TextBox1.TextChanged
        Label2.Text = TextBox1.Text
    End Sub
End Class