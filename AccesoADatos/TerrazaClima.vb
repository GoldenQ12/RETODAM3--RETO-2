Imports System.Globalization
Imports System.IO
Imports System.Net
Imports System.Net.Http
Imports System.Text
Imports System.Xml
Imports Newtonsoft.Json
Imports Newtonsoft.Json.Linq

Public Class TerrazaClima

    Dim cods As String()

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
        Dim relativePath As String = "clima.xml"

        If Not File.Exists(relativePath) Then
            Console.WriteLine("File not found: " & relativePath)
            Return
        End If

        Dim xmlContent As String = File.ReadAllText(relativePath)

        Dim serverUrl As String = "http://localhost/upload.php"

        Try
            Using client As New HttpClient()
                Using formData As New MultipartFormDataContent()
                    Dim fileContent As New ByteArrayContent(Encoding.UTF8.GetBytes(xmlContent))
                    fileContent.Headers.ContentType = New System.Net.Http.Headers.MediaTypeHeaderValue("application/xml")
                    formData.Add(fileContent, "file", Path.GetFileName(relativePath))

                    Dim response As HttpResponseMessage = client.PostAsync(serverUrl, formData).Result

                End Using
            End Using
        Catch ex As Exception
            MsgBox("An error occurred: " & ex.Message)
        End Try
    End Sub




    Private Async Function GetWeatherData(apiUrl As String) As Task
        Using client As New HttpClient()
            Try

                Dim response As HttpResponseMessage = Await client.GetAsync(apiUrl)
                response.EnsureSuccessStatusCode()

                Dim responseBody As String = Await response.Content.ReadAsStringAsync()



                Dim xmlData As String = JsonConvertToXml(responseBody)

                Dim filePath As String = "weather_data.xml"



                File.WriteAllText(filePath, xmlData)
                ' Cargar el XML desde un archivo o una cadena
                Dim xmlDoc As New XmlDocument()
                xmlDoc.Load("weather_data.xml") ' Reemplazar con la ruta del archivo si es necesario

                ' Obtener la ciudad
                Dim city As String = xmlDoc.SelectSingleNode("//Ciudad").InnerText
                Dim provincia As String = xmlDoc.SelectSingleNode("//Provincia").InnerText

                ' Obtener todas las temperaturas
                Dim tempNodes As XmlNodeList = xmlDoc.SelectNodes("//hourly/temperature_2m")
                Dim rainNodes As XmlNodeList = xmlDoc.SelectNodes("//hourly/rain")
                Dim temperatures As List(Of Double) = tempNodes.Cast(Of XmlNode)() _
                    .Select(Function(n) Convert.ToDouble(n.InnerText, CultureInfo.InvariantCulture)) _
                    .ToList()

                Dim lluvias As List(Of Double) = rainNodes.Cast(Of XmlNode)() _
                    .Select(Function(n) Convert.ToDouble(n.InnerText, CultureInfo.InvariantCulture)) _
                    .ToList()

                ' Calcular valores
                Dim maxTemperature As Double = temperatures.Max()
                Dim minTemperature As Double = temperatures.Min()
                Dim avgTemperature As Double = temperatures.Average()
                Dim avgRain As Integer = lluvias.Average() * 100


                ' Obtener la temperatura actual basada en la fecha
                Dim currentTime As String = DateTime.UtcNow.ToString("yyyy-MM-ddTHH:00")
                Dim currentTempNode As XmlNode = xmlDoc.SelectSingleNode($"//hourly[time='{currentTime}']/temperature_2m")
                Dim currentTemperature As String = If(currentTempNode IsNot Nothing, currentTempNode.InnerText.Replace(".", ","), "0,0")

                ' Crear el nuevo XML
                Dim newXml As New XmlDocument()
                Dim root As XmlElement = newXml.CreateElement("Clima")
                newXml.AppendChild(root)

                Dim cityElem As XmlElement = newXml.CreateElement("City")
                cityElem.InnerText = city
                root.AppendChild(cityElem)



                Dim provinciaElem As XmlElement = newXml.CreateElement("Provincia")
                provinciaElem.InnerText = provincia
                root.AppendChild(provinciaElem)

                Dim maxTempElem As XmlElement = newXml.CreateElement("MaxTemperatura")
                maxTempElem.InnerText = maxTemperature.ToString()
                root.AppendChild(maxTempElem)

                Dim minTempElem As XmlElement = newXml.CreateElement("MinTemperatura")
                minTempElem.InnerText = minTemperature.ToString()
                root.AppendChild(minTempElem)

                Dim currentTempElem As XmlElement = newXml.CreateElement("TemperaturaActual")
                currentTempElem.InnerText = If(Double.IsNaN(currentTemperature), "N/A", currentTemperature.ToString())
                root.AppendChild(currentTempElem)

                Dim avgTempElem As XmlElement = newXml.CreateElement("TemperaturaMedia")
                avgTempElem.InnerText = avgTemperature.ToString("F2")
                root.AppendChild(avgTempElem)

                Dim avglluviaElem As XmlElement = newXml.CreateElement("Lluvia")
                avglluviaElem.InnerText = avgRain
                root.AppendChild(avglluviaElem)

                newXml.Save("clima.xml")

                XmlToSQL()

                MessageBox.Show($"Guardado correctamente")

            Catch ex As Exception
                MessageBox.Show($" {ex.Message}")
            End Try
        End Using
    End Function

    Private Function JsonConvertToXml(json As String) As String
        Dim xmlDoc As New System.Xml.XmlDocument()
        xmlDoc.LoadXml(JsonConvert.DeserializeXmlNode(json, "Clima").OuterXml)
        Dim ciudadNode As XmlElement = xmlDoc.CreateElement("Ciudad")
        Dim provinciaElem As XmlElement = xmlDoc.CreateElement("Provincia")
        Dim parts As String() = ComboBox1.SelectedItem.ToString.Split("-")
        provinciaElem.InnerText = parts(0).ToString()
        ciudadNode.InnerText = ComboBox2.SelectedItem.ToString
        xmlDoc.DocumentElement.AppendChild(ciudadNode)
        xmlDoc.DocumentElement.AppendChild(provinciaElem)
        Return xmlDoc.OuterXml
    End Function

    Private Sub Form1_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        Me.CenterToScreen()
        LoadProvincias()
    End Sub

    Private Sub Button1_Click(sender As Object, e As EventArgs) Handles Button1.Click
        Dim city As String = ComboBox2.SelectedItem
        If city Is Nothing Then
            ModalHelper.FatalError("Por favor, introduce una ciudad")
        Else
            Dim coords As (Double, Double) = LoadMap(city)
            Dim lat As String = coords.Item1.ToString.Replace(",", ".")
            Dim lng As String = coords.Item2.ToString.Replace(",", ".")
            If lat <> "0" Or lng <> "0" Then
                Dim apiURL As String = $"https://api.open-meteo.com/v1/forecast?latitude={lat}&longitude={lng}&current=temperature_2m,apparent_temperature&hourly=temperature_2m,rain,snowfall&daily=weather_code,temperature_2m_max,temperature_2m_min&timezone=GMT&forecast_days=1"
                Dim task = GetWeatherData(apiURL)
            End If
        End If

    End Sub

    Private Sub TextBox1_TextChanged(sender As Object, e As EventArgs)
        Label2.Text = ComboBox2.SelectedItem.Text
    End Sub

    Public Async Sub LoadProvincias()
        Dim url As String = "https://apiv1.geoapi.es/provincias?key=224ac21bd315d23eb4f9e202fb8c46b0b4961200f4dbde19a765ba8698f8493d"

        Using client As New HttpClient()
            Try
                Dim response As HttpResponseMessage = Await client.GetAsync(url)
                response.EnsureSuccessStatusCode()

                Dim responseBody As String = Await response.Content.ReadAsStringAsync()
                Dim json As JObject = JObject.Parse(responseBody)
                Dim data As JArray = json("data")

                For Each item In data
                    ComboBox1.Items.Add(item("PRO").ToString() + "-" + item("CPRO").ToString)
                Next
            Catch ex As Exception
                Console.WriteLine($"Error fetching provincias: {ex.Message}")
            End Try
        End Using

    End Sub

    Public Async Sub LoadMunicipios(ByVal codProv As String)
        ComboBox2.Items.Clear()
        Dim url As String = "https://apiv1.geoapi.es/municipios?key=224ac21bd315d23eb4f9e202fb8c46b0b4961200f4dbde19a765ba8698f8493d&CPRO=" + codProv

        Using client As New HttpClient()
            Try
                Dim response As HttpResponseMessage = Await client.GetAsync(url)
                response.EnsureSuccessStatusCode()

                Dim responseBody As String = Await response.Content.ReadAsStringAsync()
                Dim json As JObject = JObject.Parse(responseBody)
                Dim data As JArray = json("data")

                For Each item In data
                    ComboBox2.Items.Add(item("DMUN50").ToString())
                Next
            Catch ex As Exception
                Console.WriteLine($"Error fetching provincias: {ex.Message}")
            End Try
        End Using

    End Sub

    Private Sub ComboBox1_SelectedIndexChanged(sender As Object, e As EventArgs) Handles ComboBox1.SelectedIndexChanged
        Dim parts As String() = ComboBox1.SelectedItem.ToString.Split("-")
        Dim codProv As String = parts(1)
        LoadMunicipios(codProv)
    End Sub

    Private Sub ComboBox2_SelectedIndexChanged(sender As Object, e As EventArgs) Handles ComboBox2.SelectedIndexChanged

    End Sub

    Private Sub PictureBox1_Click(sender As Object, e As EventArgs) Handles PictureBox1.Click
        Me.Close()
        Login.Show()
    End Sub
End Class