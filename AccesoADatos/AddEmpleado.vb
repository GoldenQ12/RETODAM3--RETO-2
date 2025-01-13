Imports System.Linq.Expressions
Imports System.Security.Permissions

Public Class AddEmpleado
    Private Sub PictureBox1_Click(sender As Object, e As EventArgs) Handles PictureBox1.Click
        Me.CenterToScreen()
        Me.Close()
        CRUDEmpleados.Show()
    End Sub

    Private Sub AddEmpleado_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        lblName.Text = AccesoADatos.Login.txtUser.Text
        Me.CenterToScreen()
        txtCod.Text = GenerarCodigo()
    End Sub

    Private Sub BtnAdd_Click(sender As Object, e As EventArgs) Handles btnAdd.Click
        AñadirEmpleado()
    End Sub

    Public Function GenerarCodigo() As Integer
        Dim query As String = "SELECT MAX(codemp) from Empleados"
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
            ModalHelper.FatalError("Error al añadir empleado: " & ex.Message)
            Return -1
        End Try

    End Function


    Public Function RealizarComprobaciones() As Boolean


        If Not System.Text.RegularExpressions.Regex.IsMatch(txtNombre.Text, "^[a-zA-Z\s]+$") Then
            ModalHelper.FatalError("El nombre solo puede contener letras y espacios")
            Return False
        End If

        If GenerarEdad() < 18 Then
            ModalHelper.FatalError("No puede registrarse este empleado, debe de ser mayor de edad")
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

    Public Sub AñadirEmpleado()
        Dim isComprobado As Boolean = RealizarComprobaciones()
        If isComprobado = False Then
            Return
        End If
        Dim cod As Integer = GenerarCodigo()
        Dim query As String = "INSERT INTO Empleados (codemp, nombre, fechanac, categoria, edad, contrasena) VALUES ('" & cod & "', '" & txtNombre.Text & "', '" & dtFechaNac.Text & "', '" & txtCategoria.Text & "', '" & txtEdad.Text & "', '" & txtContrasena.Text & "')"
        Dim command As New SqlClient.SqlCommand(query, connection)
        Try
            If connection.State = ConnectionState.Closed Then
                connection.Open()
            End If
            command.ExecuteNonQuery()
            connection.Close()
            CRUDEmpleados.GetEmpleados()
            ModalHelper.Info("Empleado añadido correctamente")
            Me.Close()
            CRUDEmpleados.Show()
        Catch ex As Exception
            ModalHelper.FatalError("Error al añadir empleado: " & ex.Message)
        Finally
            If connection.State = ConnectionState.Open Then
                connection.Close()
            End If
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