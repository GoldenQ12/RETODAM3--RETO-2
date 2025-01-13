Imports System.IO

Public Class AddArticulo


    Private Sub PictureBox1_Click(sender As Object, e As EventArgs) Handles PictureBox1.Click
        Me.CenterToScreen()
        Me.Close()
        CRUDArticulos.Show()
    End Sub

    Private Sub AddEmpleado_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        lblName.Text = AccesoADatos.Login.txtUser.Text
        Me.CenterToScreen()
        txtCod.Text = GenerarCodigo()
    End Sub

    Private Sub BtnAdd_Click(sender As Object, e As EventArgs) Handles btnAdd.Click
        AñadirArticulo()
    End Sub

    Public Function GenerarCodigo() As Integer
        Dim query As String = "SELECT MAX(codart) from Articulos"
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
            ModalHelper.FatalError("Error al añadir articulo: " & ex.Message)
            Return -1
        End Try
    End Function


    Public Function RealizarComprobaciones() As Boolean

        'Dim errorImage As Image = Image.FromFile(Path.GetFullPath("src/error (1).png"))
        If Not (txtUrl.Text.StartsWith("http://") Or txtUrl.Text.StartsWith("https://")) Then
            ModalHelper.FatalError("La url introducida no es valida")
            Return False
        End If

        If Not System.Text.RegularExpressions.Regex.IsMatch(txtNombre.Text, "^[a-zA-Z\s]+$") Then
            ModalHelper.FatalError("El nombre del articulo solo puede contener letras y espacios.")
            Return False
        End If


        If txtCantidad.Text = "" Or txtNombre.Text = "" Or txtPrecio.Text = "" Or txtUrl.Text = "" Then
            ModalHelper.FatalError("Todos los campos son obligatorios")
            Return False
        End If

        If Not System.Text.RegularExpressions.Regex.IsMatch(txtPrecio.Text, "\d+\.\d{2}") Then
            ModalHelper.FatalError("El precio solo puede contener numeros, Ej: 2.50")
            Return False
        End If

        Return True
    End Function

    Public Sub AñadirArticulo()
        Dim isComprobado As Boolean = RealizarComprobaciones()
        If isComprobado = False Then
            Return
        End If
        Dim cod As Integer = GenerarCodigo()
        Dim query As String = "INSERT INTO Articulos (codart, nombre, precio, cantidad, URL) VALUES ('" & cod & "', '" & txtNombre.Text & "', '" & txtPrecio.Text & "', '" & txtCantidad.Text & "', '" & txtUrl.Text & "')"
        Dim command As New SqlClient.SqlCommand(query, connection)
        Try
            If connection.State = ConnectionState.Closed Then
                connection.Open()
            End If
            command.ExecuteNonQuery()
            connection.Close()
            CRUDArticulos.GetArticulos()
            ModalHelper.Info("Articulo añadido correctamente")
            Me.Close()
            CRUDArticulos.Show()
        Catch ex As Exception
            ModalHelper.FatalError("Error al añadir articulo: " & ex.Message)
        Finally
            If connection.State = ConnectionState.Open Then
                connection.Close()
            End If
        End Try
    End Sub

    Private Sub TxtUrl_TextChanged(sender As Object, e As EventArgs) Handles txtUrl.TextChanged
        Try
            Dim request As System.Net.WebRequest = System.Net.WebRequest.Create(txtUrl.Text)
            Dim response As System.Net.WebResponse = request.GetResponse()
            Dim stream As System.IO.Stream = response.GetResponseStream()

            ' Load the original image
            Dim originalImage As Image = Image.FromStream(stream)

            ' Calculate scaling factors to maintain aspect ratio
            Dim ratioX As Double = pbPhoto.Width / originalImage.Width
            Dim ratioY As Double = pbPhoto.Height / originalImage.Height
            Dim ratio As Double = Math.Min(ratioX, ratioY)

            ' Calculate new dimensions
            Dim newWidth As Integer = CInt(originalImage.Width * ratio)
            Dim newHeight As Integer = CInt(originalImage.Height * ratio)

            ' Create new bitmap with calculated dimensions
            Dim newImage As New Bitmap(newWidth, newHeight)

            ' Draw the scaled image
            Using g As Graphics = Graphics.FromImage(newImage)
                g.InterpolationMode = Drawing2D.InterpolationMode.HighQualityBicubic
                g.DrawImage(originalImage, 0, 0, newWidth, newHeight)
            End Using

            ' Set the PictureBox properties
            pbPhoto.SizeMode = PictureBoxSizeMode.CenterImage
            pbPhoto.Image = newImage

            ' Clean up
            originalImage.Dispose()
        Catch ex As Exception
            pbPhoto.Image = Image.FromFile(Path.GetFullPath("src/error (1).png"))
        End Try
    End Sub
End Class
