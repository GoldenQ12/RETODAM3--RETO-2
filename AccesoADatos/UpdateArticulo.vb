Imports System.IO

Public Class UpdateArticulo

    Private Sub PictureBox1_Click(sender As Object, e As EventArgs) Handles PictureBox1.Click
        Me.Close()
        CRUDArticulos.Show()
    End Sub

    Private Sub UpdateEmpleado_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        txtCod.Text = CRUDArticulos.lblCod.Text
        txtNombre.Text = CRUDArticulos.lblNombre.Text
        txtPrecio.Text = CRUDArticulos.lblPrecio.Text
        txtUrl.Text = CRUDArticulos.lblUrl.Text
        txtCantidad.Value = CRUDArticulos.lblCantidad.Text
        TxtUrl_TextChanged(sender, e)

        lblName.Text = AccesoADatos.Login.txtUser.Text
        Me.CenterToScreen()

    End Sub

    Public Function RealizarComprobaciones() As Boolean


        If Not (txtUrl.Text.StartsWith("http://") Or txtUrl.Text.StartsWith("https://")) Then
            ModalHelper.FatalError("La url introducida no es valida")
            Return False
        End If

        If Not System.Text.RegularExpressions.Regex.IsMatch(txtNombre.Text, "^[a-zA-Z\s]+$") Then
            ModalHelper.FatalError("El nombre solo puede contener letras y espacios.")
            Return False
        End If

        If txtUrl.Text = "" Or txtPrecio.Text = "" Or txtCod.Text = "" Or txtNombre.Text = "" Then
            ModalHelper.FatalError("Todos los campos son obligatorios")
            Return False
        End If

        If txtCantidad.Value < 0 Then
            ModalHelper.FatalError("La cantidad debe de ser mayor o igual a 0")
            Return False
        End If

        Return True
    End Function

    Private Sub ActualizarDatosArticulo()
        Dim isComprobado As Boolean = RealizarComprobaciones()
        If isComprobado = False Then
            Return
        End If
        Dim query As String = "UPDATE Articulos SET nombre = '" & txtNombre.Text & "', precio = '" & txtPrecio.Text & "', cantidad = '" & txtCantidad.Value.ToString() & "', URL = '" & txtUrl.Text & "' WHERE codart = " & CRUDArticulos.lblCod.Text
        Dim command As New SqlClient.SqlCommand(query, connection)
        Try
            If connection.State = ConnectionState.Closed Then
                connection.Open()
            End If
            command.ExecuteNonQuery()
            connection.Close()
            CRUDArticulos.GetArticulos()
            ModalHelper.Info("Articulo actualizado correctamente")
        Catch ex As Exception
            ModalHelper.FatalError("Error " & ex.Message)
        End Try
    End Sub

    Private Sub TxtUrl_TextChanged(sender As Object, e As EventArgs) Handles txtUrl.TextChanged
        Try
            Dim request As System.Net.WebRequest = System.Net.WebRequest.Create(txtUrl.Text)
            Dim response As System.Net.WebResponse = request.GetResponse()
            Dim stream As System.IO.Stream = response.GetResponseStream()

            Dim originalImage As Image = Image.FromStream(stream)

            Dim ratioX As Double = pbPhoto.Width / originalImage.Width
            Dim ratioY As Double = pbPhoto.Height / originalImage.Height
            Dim ratio As Double = Math.Min(ratioX, ratioY)

            Dim newWidth As Integer = CInt(originalImage.Width * ratio)
            Dim newHeight As Integer = CInt(originalImage.Height * ratio)

            Dim newImage As New Bitmap(newWidth, newHeight)

            Using g As Graphics = Graphics.FromImage(newImage)
                g.InterpolationMode = Drawing2D.InterpolationMode.HighQualityBicubic
                g.DrawImage(originalImage, 0, 0, newWidth, newHeight)
            End Using

            pbPhoto.SizeMode = PictureBoxSizeMode.CenterImage
            pbPhoto.Image = newImage

            originalImage.Dispose()
        Catch ex As Exception
            pbPhoto.Image = Image.FromFile(Path.GetFullPath("src/error (1).png"))
        End Try
    End Sub

    Private Sub BtnAdd_Click(sender As Object, e As EventArgs) Handles btnAdd.Click
        ActualizarDatosArticulo()
    End Sub
End Class