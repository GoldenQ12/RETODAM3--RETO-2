
Public Class UpdateEmpleado
    Private Sub PictureBox1_Click(sender As Object, e As EventArgs) Handles PictureBox1.Click
        Me.Close()
        CRUDEmpleados.Show()
    End Sub

    Private Sub UpdateEmpleado_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        txtNombre.Text = CRUDEmpleados.lblNombre.Text
        txtCategoria.Text = CRUDEmpleados.lblCategoria.Text
        dtFechaNac.Text = CRUDEmpleados.lblFechaNac.Text
        txtEdad.Text = CRUDEmpleados.lblEdad.Text
        txtContrasena.Text = CRUDEmpleados.lblContrasena.Text
        txtCod.Text = CRUDEmpleados.lblCod.Text
        lblName.Text = AccesoADatos.Login.txtUser.Text
        Me.CenterToScreen()
    End Sub

    Private Sub Button1_Click(sender As Object, e As EventArgs) Handles btnAdd.Click
        ActualizarDatosEmpleado()
    End Sub

    Public Function RealizarComprobaciones() As Boolean


        If Not System.Text.RegularExpressions.Regex.IsMatch(txtNombre.Text, "^[a-zA-Z\s]+$") Then
            ModalHelper.FatalError("El nombre solo puede contener letras y espacios.")
            Return False
        End If

        If txtCategoria.Text = "" Or txtEdad.Text = "" Or txtContrasena.Text = "" Or txtNombre.Text = "" Then
            ModalHelper.FatalError("Todos los campos son obligatorios")
            Return False
        End If

        If txtContrasena.Text.Length < 8 Then
            ModalHelper.FatalError("La contraseña debe de tener una longitud de mínimo 8 caractéres")
            Return False
        End If

        Return True
    End Function

    Private Sub ActualizarDatosEmpleado()
        Dim isComprobado As Boolean = RealizarComprobaciones()
        If isComprobado = False Then
            Return
        End If
        Dim query As String = "UPDATE Empleados SET nombre = '" & txtNombre.Text & "', categoria = '" & txtCategoria.Text & "', fechanac = '" & dtFechaNac.Text & "', edad = '" & txtEdad.Text & "', contrasena = '" & txtContrasena.Text & "' WHERE codemp = " & CRUDEmpleados.lblCod.Text
        Dim command As New SqlClient.SqlCommand(query, connection)
        Try
            If connection.State = ConnectionState.Closed Then
                connection.Open()
            End If
            command.ExecuteNonQuery()
            connection.Close()
            CRUDEmpleados.GetEmpleados()
            ModalHelper.Info("Empleado actualizado correctamente")
        Catch ex As Exception
            ModalHelper.FatalError("Error al actualizar el empleado " & ex.Message)
        End Try
    End Sub

    Private Sub CkPasswordChar_CheckedChanged(sender As Object, e As EventArgs) Handles ckPasswordChar.CheckedChanged
        If ckPasswordChar.Checked Then
            txtContrasena.PasswordChar = ChrW(0)
        Else
            txtContrasena.PasswordChar = "*"
        End If
    End Sub
    Public Function GenerarEdad() As Integer

        Dim fechaNac As Date = dtFechaNac.Text
        Dim edad As Integer = DateDiff(DateInterval.Year, fechaNac, Now)
        txtEdad.Text = edad
        Return edad

    End Function

    Private Sub DtFechaNac_ValueChanged(sender As Object, e As EventArgs) Handles dtFechaNac.ValueChanged
        GenerarEdad()
    End Sub

End Class