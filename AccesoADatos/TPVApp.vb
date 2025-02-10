Imports System.IO
Imports System.Net
Imports System.Xml

Imports AccesoADatos
Public Class TPVApp

    Public selectedItems As New Dictionary(Of String, Integer)  ' Tracks item name and quantity
    Private totalAmount As Decimal = 0

    Private Sub TPVApp_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        Me.CenterToScreen()
        LoadApp()
        lblName.Text = AccesoADatos.Login.txtUser.Text
    End Sub

    Private Sub PbExit_Click(sender As Object, e As EventArgs) Handles pbExit.Click
        Dim res = MessageBox.Show("Estas seguro que deseas cerrar sesion?", "Advertencia", MessageBoxButtons.OKCancel)
        If res = DialogResult.OK Then
            Me.Close()
            AccesoADatos.Login.txtUser.Text = ""
            AccesoADatos.Login.txtPassword.Text = ""
            Login.Show()
        End If
    End Sub



    Public Sub LoadApp()
        Dim urls As List(Of String) = GetUrls()
        Dim precios As List(Of Double) = GetPrecios()
        Dim nombres As List(Of String) = GetNombres()
        Dim cantidades As List(Of Integer) = GetCantidad()

        For i As Integer = 0 To urls.Count - 1
            Try
                Dim panel As New Panel With {
                    .Width = 180,
                    .Height = 250,
                    .Margin = New Padding(10),
                    .Name = $"panel{i + 1}",
                    .BackColor = Color.LightGray,
                    .BorderStyle = BorderStyle.FixedSingle,
                    .Padding = New Padding(10)
                }

                Dim button As New Button With {
                    .Dock = DockStyle.Fill,
                    .Margin = New Padding(10),
                    .Name = $"btn{i + 1}",
                    .FlatStyle = FlatStyle.Flat,
                    .Cursor = Cursors.Hand,
                    .BackColor = Color.White,
                    .Font = New Font("Segoe UI", 10, FontStyle.Regular)
                }

                Dim nombre As New Label With {
                    .Text = nombres(i),
                    .TextAlign = ContentAlignment.MiddleCenter,
                    .Dock = DockStyle.Fill,
                    .AutoEllipsis = True,
                    .Font = New Font("Segoe UI", 12, FontStyle.Bold),
                    .ForeColor = Color.FromArgb(34, 34, 34)
                }
                Dim precio As New Label With {
                    .Text = precios(i) & "€",
                    .TextAlign = ContentAlignment.MiddleCenter,
                    .Font = New Font("Segoe UI", 14, FontStyle.Bold),
                    .ForeColor = Color.FromArgb(76, 175, 80),
                    .Height = 30,
                    .Dock = DockStyle.Left,
                    .Width = panel.Width \ 2
                }

                Dim nombreContainer As New Panel With {.Dock = DockStyle.Top, .Height = 50}

                Dim precioContainer As New Panel With {.Dock = DockStyle.Bottom, .Height = 30}

                precio.Dock = DockStyle.Left
                precio.Width = panel.Width \ 2

                Dim cantidad As New Label With {
                    .Text = $"x{cantidades(i)}",
                    .TextAlign = ContentAlignment.MiddleCenter,
                    .Dock = DockStyle.Right,
                    .Width = panel.Width \ 2,
                    .Font = New Font("Segoe UI", 14, FontStyle.Bold),
                    .ForeColor = If(cantidades(i) = 0, Color.Red, Color.FromArgb(76, 175, 80))
                }

                precioContainer.Controls.Add(precio)
                precioContainer.Controls.Add(cantidad)
                nombreContainer.Controls.Add(nombre)
                panel.Controls.Add(precioContainer)
                panel.Controls.Add(nombreContainer)
                panel.Controls.Add(button)

                Using webClient As New WebClient()
                    Using stream As New IO.MemoryStream(webClient.DownloadData(urls(i)))
                        Dim originalImage As Image = Image.FromStream(stream)

                        Dim targetWidth As Integer = CInt(button.Width * 0.75)
                        Dim targetHeight As Integer = CInt(button.Height * 0.75)

                        Dim ratioX As Double = targetWidth / originalImage.Width
                        Dim ratioY As Double = targetHeight / originalImage.Height
                        Dim ratio As Double = Math.Min(ratioX, ratioY)

                        Dim newWidth As Integer = CInt(originalImage.Width * ratio)
                        Dim newHeight As Integer = CInt(originalImage.Height * ratio)

                        Dim finalImage As New Bitmap(button.Width, button.Height)

                        Using g As Graphics = Graphics.FromImage(finalImage)
                            g.InterpolationMode = Drawing2D.InterpolationMode.HighQualityBicubic
                            Dim x As Integer = (button.Width - newWidth) \ 2
                            Dim y As Integer = (button.Height - newHeight) \ 2
                            g.DrawImage(originalImage, x, y, newWidth, newHeight)
                        End Using

                        button.Image = finalImage
                        button.ImageAlign = ContentAlignment.MiddleCenter
                    End Using
                End Using

                AddHandler button.Click, AddressOf Button_Click

                layoutProductos.Controls.Add(panel)
            Catch ex As Exception
                ModalHelper.FatalError("Error" & ex.Message())
            End Try
        Next
    End Sub



    Public Function GetUrls() As List(Of String)
        Dim query As String = "SELECT url FROM Articulos"
        Dim command As New SqlClient.SqlCommand(query, connection)
        Try
            If connection.State = ConnectionState.Closed Then
                connection.Open()
            End If
            Dim reader As SqlClient.SqlDataReader = command.ExecuteReader()
            Dim urls As New List(Of String)
            While reader.Read()
                urls.Add(reader("url").ToString())
            End While
            reader.Close()
            connection.Close()
            Return urls
        Catch ex As Exception
            ModalHelper.FatalError("Error al obtener las urls: " & ex.Message)
            Return Nothing
        End Try
    End Function

    Public Function GetPrecios() As List(Of Double)
        Dim query As String = "SELECT precio FROM Articulos"
        Dim command As New SqlClient.SqlCommand(query, connection)
        Try
            If connection.State = ConnectionState.Closed Then
                connection.Open()
            End If
            Dim reader As SqlClient.SqlDataReader = command.ExecuteReader()
            Dim precios As New List(Of Double)
            While reader.Read()
                precios.Add(reader("precio").ToString())
            End While
            reader.Close()
            connection.Close()
            Return precios
        Catch ex As Exception
            ModalHelper.FatalError("Error al obtener los precios: " & ex.Message)
            Return Nothing
        End Try
    End Function

    Public Function GetNombres() As List(Of String)
        Dim query As String = "SELECT nombre FROM Articulos"
        Dim command As New SqlClient.SqlCommand(query, connection)
        Try
            If connection.State = ConnectionState.Closed Then
                connection.Open()
            End If
            Dim reader As SqlClient.SqlDataReader = command.ExecuteReader()
            Dim nombres As New List(Of String)
            While reader.Read()
                nombres.Add(reader("nombre").ToString())
            End While
            reader.Close()
            connection.Close()
            Return nombres
        Catch ex As Exception
            ModalHelper.FatalError("Error al obtener los nombres: " & ex.Message)
            Return Nothing
        End Try
    End Function

    Public Function GetCantidad() As List(Of Integer)
        Dim query As String = "SELECT cantidad FROM Articulos"
        Dim command As New SqlClient.SqlCommand(query, connection)
        Try
            If connection.State = ConnectionState.Closed Then
                connection.Open()
            End If
            Dim reader As SqlClient.SqlDataReader = command.ExecuteReader()
            Dim cantidad As New List(Of Integer)
            While reader.Read()
                cantidad.Add(reader("cantidad").ToString())
            End While
            reader.Close()
            connection.Close()
            Return cantidad
        Catch ex As Exception
            ModalHelper.FatalError("Error al obtener los nombres: " & ex.Message)
            Return Nothing
        End Try
    End Function


    Private Sub Button_Click(sender As Object, e As EventArgs)
        Dim button As Button = DirectCast(sender, Button)
        Dim panel As Panel = DirectCast(button.Parent, Panel)

        Dim nombreContainer As Panel = panel.Controls.OfType(Of Panel)().FirstOrDefault(Function(p) p.Dock = DockStyle.Top)
        Dim precioContainer As Panel = panel.Controls.OfType(Of Panel)().FirstOrDefault(Function(p) p.Dock = DockStyle.Bottom)


        Dim nombreLabel As Label = DirectCast(nombreContainer.Controls(0), Label)
        Dim precioLabel As Label = DirectCast(precioContainer.Controls(0), Label)
        Dim cantidadLabel As Label = DirectCast(precioContainer.Controls(1), Label)

        Dim precio As Decimal = Decimal.Parse(precioLabel.Text.TrimEnd("€"c))

        If RecogerArticulo(nombreLabel.Text) Then
            If Not selectedItems.ContainsKey(nombreLabel.Text) Then
                selectedItems(nombreLabel.Text) = 0
            End If
            selectedItems(nombreLabel.Text) += 1

            totalAmount += precio
            lblTotal.Text = $"Total: {totalAmount:N2}€"

            ReloadCantidad()

            Dim existingLabel = layoutComanda.Controls.OfType(Of Label)().
                FirstOrDefault(Function(lbl) lbl.Text.StartsWith(nombreLabel.Text))

            Dim quantity As Integer = Integer.Parse(cantidadLabel.Text.TrimStart("x"c))
            Dim total As Decimal = precio * selectedItems(nombreLabel.Text)

            If existingLabel IsNot Nothing Then
                ' Update existing label
                existingLabel.Text = $"{nombreLabel.Text}- x{selectedItems(nombreLabel.Text)} - {total:N2}€"
            Else
                Dim labelArticulo As New Label With {
                    .Text = $"{nombreLabel.Text}- x{selectedItems(nombreLabel.Text)} - {total:N2}€",
                    .TextAlign = ContentAlignment.MiddleLeft,
                    .AutoSize = False,
                    .Width = layoutComanda.Width - 20,
                    .Height = 30,
                    .Font = New Font("Segoe UI", 12, FontStyle.Bold),
                    .ForeColor = Color.DarkSlateGray,
                    .Padding = New Padding(10, 5, 10, 5),
                    .Margin = New Padding(5)
                    }
                Dim restaImagen As New Button With {
                    .Image = Image.FromFile(Path.GetFullPath("src/minus (1) (1).png")),
                    .Width = 32,
                    .Height = 32,
                    .ImageAlign = ContentAlignment.MiddleCenter,
                    .FlatStyle = FlatStyle.Flat,
                    .Cursor = Cursors.Hand,
                    .Location = New Point(labelArticulo.Width - 40)
                }
                AddHandler restaImagen.Click, AddressOf RestaImagen_Click
                labelArticulo.Controls.Add(restaImagen)
                layoutComanda.Controls.Add(labelArticulo)
            End If
        End If

    End Sub

    Public Sub Reload(sender As Object, e As EventArgs)
        Dim button As Button = DirectCast(sender, Button)
        Dim label As Label = DirectCast(button.Parent, Label)
        Dim nombre As String = label.Text.Split("-"c)(0)

        If selectedItems.ContainsKey(nombre) Then
            Dim itemTotalPrice As Decimal = Decimal.Parse(label.Text.Split("-"c)(2).TrimEnd(New Char() {"€"c}))
            Dim pricePerItem As Decimal = itemTotalPrice / selectedItems(nombre)
            totalAmount -= pricePerItem
            selectedItems(nombre) -= 1

            Dim total As Decimal = pricePerItem * selectedItems(nombre)

            If selectedItems(nombre) <= 0 Then
                lblTotal.Text = $"Total: {totalAmount:N2}€"
                label.Dispose()
            Else
                lblTotal.Text = $"Total: {totalAmount:N2}€"
                label.Text = $"{nombre}- x{selectedItems(nombre)} - {total:N2}€"
            End If
        End If
    End Sub

    Public Sub RestaImagen_Click(sender As Object, e As EventArgs)
        Dim button As Button = DirectCast(sender, Button)
        Dim label As Label = DirectCast(button.Parent, Label)
        Dim nombre As String = label.Text.Split("-"c)(0)


        Try
            Dim query As String = "UPDATE Articulos SET cantidad = cantidad + 1 WHERE nombre = @nombre"
            Dim command As New SqlClient.SqlCommand(query, connection)
            command.Parameters.AddWithValue("@nombre", nombre)
            If connection.State = ConnectionState.Closed Then
                connection.Open()
            End If
            command.ExecuteNonQuery()
            connection.Close()
            ReloadCantidad()
            Reload(sender, e)
        Catch ex As Exception
            MsgBox(ex.Message(), "Error")
        End Try

    End Sub

    Public Function RecogerArticulo(nombre As String) As Boolean
        Dim query As String = "SELECT * FROM Articulos WHERE nombre = @nombre"
        Dim command As New SqlClient.SqlCommand(query, connection)
        command.Parameters.AddWithValue("@nombre", nombre)

        Try
            If connection.State = ConnectionState.Closed Then
                connection.Open()
            End If

            Dim reader As SqlClient.SqlDataReader = command.ExecuteReader()

            If Not reader.HasRows Then
                reader.Close()
                connection.Close()
                ModalHelper.FatalError("Artículo no encontrado")
                Return False
            End If

            reader.Read()
            Dim currentQuantity As Integer = reader("cantidad")
            reader.Close()

            If currentQuantity > 0 Then
                query = "UPDATE Articulos SET cantidad = cantidad - 1 WHERE nombre = @nombre"
                command = New SqlClient.SqlCommand(query, connection)
                command.Parameters.AddWithValue("@nombre", nombre)
                command.ExecuteNonQuery()
                connection.Close()
                ReloadCantidad()
                Return True
            Else
                connection.Close()
                MsgBox("No hay stock")
                Return False
            End If
        Catch ex As Exception
            If connection.State = ConnectionState.Open Then
                connection.Close()
            End If
            Return False
        End Try
    End Function

    Public Sub ReloadCantidad()
        Dim cantidad As List(Of Integer) = GetCantidad()
        For i As Integer = 0 To cantidad.Count - 1
            Dim panel As Panel = DirectCast(layoutProductos.Controls(i), Panel)
            Dim precioContainer As Panel = panel.Controls.OfType(Of Panel)().FirstOrDefault(Function(p) p.Dock = DockStyle.Bottom)
            Dim cantidadLabel As Label = DirectCast(precioContainer.Controls(1), Label)
            cantidadLabel.Text = $"x{cantidad(i)}"
            If cantidadLabel.Text.Equals("x0") Then
                cantidadLabel.ForeColor = Color.Red
            End If
        Next
    End Sub

    Function CreateXmlElement(doc As XmlDocument, tagName As String, value As String) As XmlElement
        Dim element As XmlElement = doc.CreateElement(tagName)
        element.InnerText = value
        Return element
    End Function

    Public Sub AlmacenarXML()
        Dim ticketXml As New XmlDocument()
        Dim root As XmlElement = ticketXml.CreateElement("TicketVenta")
        ticketXml.AppendChild(root)

        ' Crear nodo de productos
        Dim productosNode As XmlElement = ticketXml.CreateElement("Productos")

        ' Recorrer el array dinámico y agregar productos al XML
        For Each venta In selectedItems
            Dim productoNode As XmlElement = ticketXml.CreateElement("Producto")
            productoNode.AppendChild(CreateXmlElement(ticketXml, "Nombre", venta.Key))
            productoNode.AppendChild(CreateXmlElement(ticketXml, "Cantidad", venta.Value))
            productosNode.AppendChild(productoNode)
        Next

        Dim precio_total As Double = GetPrecioTotalById()

        root.AppendChild(CreateXmlElement(ticketXml, "precio_total", precio_total & "€"))

        root.AppendChild(productosNode)


        Dim savePath As String = "tickets\"
        If Not Directory.Exists(savePath) Then
            Directory.CreateDirectory(savePath)
        End If

        Dim fileName As String = savePath & "Ticket_" & DateTime.Now.ToString("yyyyMMdd_HHmmss") & ".xml"
        ticketXml.Save(fileName)

        Console.WriteLine("Ticket guardado en: " & fileName)
    End Sub

    Public Function GetPrecioTotalById() As Double
        Dim query As String = "SELECT precio_final FROM LineaPedidos WHERE codped = @codped"
        Dim queryCods As String = "SELECT COUNT(codped) AS ped_max FROM Pedidos"
        Dim command As New SqlClient.SqlCommand(query, connection)
        Dim command2 As New SqlClient.SqlCommand(queryCods, connection)
        Dim codped As Integer = 0
        Try
            If connection.State = ConnectionState.Closed Then
                connection.Open()
            End If
            Dim reader2 As SqlClient.SqlDataReader = command2.ExecuteReader()
            Dim precio As Double = 0.00
            If reader2.Read() Then
                codped = reader2("ped_max")
            End If

            reader2.Close()
            command.Parameters.AddWithValue("@codped", codped)
            Dim reader As SqlClient.SqlDataReader = command.ExecuteReader()
            If reader.Read() Then
                precio = reader("precio_final")
            End If
            reader.Close()
            connection.Close()
            Return precio
        Catch ex As Exception
            MsgBox("Error al obtener los precios: " & ex.Message, Title:="Error")
            Return 0.00

        End Try
    End Function

    Private Sub BtnRegister_Click(sender As Object, e As EventArgs) Handles btnRegister.Click
        AddToPedidos()
        AddToLineaPedidos()
        AlmacenarXML()
        ModalHelper.Info("Mandando comanda")
    End Sub

    Public Function GenerarCodigoLineaPed() As Integer
        Dim query As String = "SELECT MAX(codartped) from LineaPedidos"
        Dim command As New SqlClient.SqlCommand(query, connection)
        Try
            If connection.State = ConnectionState.Closed Then
                connection.Open()
            End If
            Dim maxCod As Object = command.ExecuteScalar()
            Dim cod As Integer
            cod = CInt(maxCod) + 1
            connection.Close()
            Return cod
        Catch ex As Exception
            MsgBox("Error al añadir pedido: " & ex.Message, Title:="Error")
            Return -1
        End Try
    End Function

    Public Function GenerarCodigoPed() As Integer
        Dim query As String = "SELECT MAX(codped) from Pedidos"
        Dim command As New SqlClient.SqlCommand(query, connection)
        Try
            If connection.State = ConnectionState.Closed Then
                connection.Open()
            End If
            Dim maxCod As Object = command.ExecuteScalar()
            Dim cod As Integer
            cod = CInt(maxCod) + 1
            connection.Close()

            Return cod
        Catch ex As Exception
            MsgBox("Error al añadir pedido: " & ex.Message, Title:="Error")
            Return -1
        End Try
    End Function

    Public Sub AddToPedidos()
        Try
            Dim query As String = "INSERT INTO Pedidos (codped, fecha) VALUES (@codigo, @fecha)"
            Dim command As New SqlClient.SqlCommand(query, connection)
            command.Parameters.AddWithValue("@fecha", DateTime.Now)
            command.Parameters.AddWithValue("@codigo", GenerarCodigoPed())
            If connection.State = ConnectionState.Closed Then
                connection.Open()
            End If
            command.ExecuteNonQuery()
            ModalHelper.Info("Agregando pedido...")
        Catch ex As Exception
            MsgBox("Error" & ex.Message, Title:="Error")
        End Try

    End Sub

    Public Function GetPedido() As Integer
        Dim cod As Integer
        Dim query As String = "SELECT MAX(codped) FROM Pedidos"
        Dim command As New SqlClient.SqlCommand(query, connection)
        Try
            If connection.State = ConnectionState.Closed Then
                connection.Open()
            End If
            Dim maxCod As Object = command.ExecuteScalar()
            cod = CInt(maxCod)

            connection.Close()
            Return cod
        Catch ex As Exception
            MsgBox("Error al recoger pedido: " & ex.Message, Title:="Error")
            Return -1
        End Try

    End Function
    Public Function GetCodsOfSelectedItems() As Integer()
        Dim cods As New List(Of Integer)()
        For Each nombre In selectedItems.Keys
            Dim query As String = "SELECT codart FROM Articulos WHERE nombre = @nombre"
            Dim command As New SqlClient.SqlCommand(query, connection)
            command.Parameters.AddWithValue("@nombre", nombre)
            Dim codart = 0
            Try
                If connection.State = ConnectionState.Closed Then
                    connection.Open()
                End If

                Dim reader As SqlClient.SqlDataReader = command.ExecuteReader()
                If reader.Read() Then
                    codart = reader("codart")
                End If
                reader.Close()
                connection.Close()

            Catch ex As Exception
                If connection.State = ConnectionState.Open Then
                    connection.Close()
                End If
                MsgBox("Error: " & ex.Message)
            End Try
            cods.Add(codart)
        Next nombre
        Return cods.ToArray()
    End Function

    Public Function GetNombresOfSelectedItems() As String()
        Dim nombres As New List(Of String)
        For Each cod In GetCodsOfSelectedItems()
            Dim query As String = "SELECT nombre FROM Articulos WHERE codart = @codart"
            Dim command As New SqlClient.SqlCommand(query, connection)
            command.Parameters.AddWithValue("@codart", cod)
            Dim nombre = ""
            Try
                If connection.State = ConnectionState.Closed Then
                    connection.Open()
                End If

                Dim reader As SqlClient.SqlDataReader = command.ExecuteReader()
                If reader.Read() Then
                    nombre = reader("nombre")
                End If
                reader.Close()
                connection.Close()

            Catch ex As Exception
                If connection.State = ConnectionState.Open Then
                    connection.Close()
                End If
                MsgBox("Error: " & ex.Message)
            End Try
            nombres.Add(nombre)
        Next cod
        Return nombres.ToArray()
    End Function

    Public Sub AddToLineaPedidos()
        Try
            Dim codPed As Integer = GetPedido()
            Dim cods As Integer() = GetCodsOfSelectedItems()
            Dim nombres As String() =  GetNombresOfSelectedItems()
            Dim realizado As Boolean = False
            Dim precioTotal As Decimal = totalAmount
            For i As Integer = 0 To cods.Length - 1
                Dim query As String = "INSERT INTO LineaPedidos (codartped, codart, codped, realizado, precio_final, cantidad) VALUES (@codartped, @codart, @codped, @realizado, @precio_final, @cantidad)"
                Dim command As New SqlClient.SqlCommand(query, connection)
                command.Parameters.AddWithValue("@codartped", GenerarCodigoLineaPed)
                command.Parameters.AddWithValue("@codart", cods(i))
                command.Parameters.AddWithValue("@codped", codPed)
                command.Parameters.AddWithValue("@realizado", realizado)
                command.Parameters.AddWithValue("@precio_final", precioTotal)
                command.Parameters.AddWithValue("@cantidad", selectedItems(nombres(i)))
                If connection.State = ConnectionState.Closed Then
                    connection.Open()
                End If
                command.ExecuteNonQuery()
            Next i
            connection.Close()
        Catch ex As Exception
            MsgBox("Error: " & ex.Message, Title:="Error")
        End Try
    End Sub

    Private Sub BtnShowPedidos_Click(sender As Object, e As EventArgs) Handles btnShowPedidos.Click
        Me.Close()
        Comandas.Show()
    End Sub

    Private Sub layoutProductos_Paint(sender As Object, e As PaintEventArgs) Handles layoutProductos.Paint

    End Sub
End Class