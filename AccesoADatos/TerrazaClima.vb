Imports System.IO
Imports System.Net
Imports System.Net.Http
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

                    Dim lat As Double = jsonObject("results")(0)("geometry")("lat")
                    Dim lng As Double = jsonObject("results")(0)("geometry")("lng")
                    Return (lat, lng)
                End Using
            End Using

        Catch ex As WebException
            Console.WriteLine("Error: " & ex.Message)
            Return (0, 0)
        End Try

    End Function



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
        Me.Text = "Google Maps and Weather Data"
        Me.CenterToScreen()

        Button1.Text = "Get Weather Data"

    End Sub

    Private Sub Button1_Click(sender As Object, e As EventArgs) Handles Button1.Click
        Dim city As String = TextBox1.Text
        Dim coords As (Double, Double) = LoadMap(city)
        Dim lat As String = coords.Item1.ToString.Replace(",", ".")
        Dim lng As String = coords.Item2.ToString.Replace(",", ".")
        LoadMap(city)
        Dim apiURL As String = $"https://api.open-meteo.com/v1/forecast?latitude={lat}&longitude={lng}&current=temperature_2m,apparent_temperature&hourly=temperature_2m,rain,snowfall&daily=weather_code,temperature_2m_max,temperature_2m_min&timezone=GMT&forecast_days=1"
        Dim task = GetWeatherData(apiURL)
        MsgBox("Datos generados correctamente")
    End Sub
End Class