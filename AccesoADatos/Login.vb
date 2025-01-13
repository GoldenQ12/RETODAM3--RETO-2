Imports System.Data.SqlClient

Public Class Login
    Private Sub Login_load(sender As Object, e As EventArgs) Handles MyBase.Load
        Me.CenterToScreen()
        txtUser.Text = ""
        txtPassword.Text = ""
    End Sub

    Private Sub BtnExit_Click(sender As Object, e As EventArgs)
        connection.Close()
        Me.Close()
    End Sub

    Public Sub LoginEmpleado()
        Try
            connection.Open()
            Dim query As String = "SELECT * FROM Empleados WHERE nombre = '" & txtUser.Text & "' AND contrasena = '" & txtPassword.Text & "'"
            Dim adapter As New SqlDataAdapter(query, connection)
            Dim dataSet As New DataSet()
            adapter.Fill(dataSet, "Empleados")
            If dataSet.Tables("Empleados").Rows.Count > 0 Then
                If dataSet.Tables("Empleados").Rows(0)("categoria").Equals("Camarero") Then
                    ModalHelper.Info("Acceso concedido. Bienvenido " & dataSet.Tables("Empleados").Rows(0)("nombre").ToString)
                    TPVApp.Show()
                    Me.Hide()
                ElseIf dataSet.Tables("Empleados").Rows(0)("categoria").Equals("Gerente") Then
                    ModalHelper.Info("Acceso concedido. Bienvenido " & dataSet.Tables("Empleados").Rows(0)("nombre").ToString)
                    AdminPanel.Show()
                    Me.Hide()
                Else
                    ModalHelper.Info("No puedes acceder a ningun panel administrativo.")
                End If
            Else
                ModalHelper.FatalError("Usuario o contraseña incorrectos")
            End If
        Catch ex As Exception
            ModalHelper.FatalError("Error al obtener los empleados: " & ex.Message)
        End Try
    End Sub

    Private Sub BtnLogin_Click(sender As Object, e As EventArgs) Handles btnLogin.Click
        LoginEmpleado()
        connection.Close()
    End Sub

    Private Sub ckPasswordChar_CheckedChanged(sender As Object, e As EventArgs) Handles ckPasswordChar.CheckedChanged
        If ckPasswordChar.Checked Then
            txtPassword.PasswordChar = ChrW(0)
        Else
            txtPassword.PasswordChar = "*"
        End If
    End Sub

    Private Sub PictureBox1_Click(sender As Object, e As EventArgs) Handles PictureBox1.Click
        Dim res = MessageBox.Show("Estas seguro que deseas salir?", "Advertencia", MessageBoxButtons.OKCancel)
        If res = DialogResult.OK Then
            Me.Close()
        End If
    End Sub


    Private Sub Button1_Click(sender As Object, e As EventArgs) Handles Button1.Click
        MsgBox("Acceso concedido. Bienvenido, Camarero!")
        TPVApp.Show()
        Me.Hide()
    End Sub

    Private Sub Button2_Click(sender As Object, e As EventArgs) Handles Button2.Click
        MsgBox("Acceso concedido. Bienvenido, Gerente!")
        AdminPanel.Show()
        Me.Hide()
    End Sub

    Private Sub BtnShowPedidos_Click(sender As Object, e As EventArgs) Handles btnShowPedidos.Click
        Me.Hide()
        Comandas.Show()
    End Sub

    Private Sub btnModalCustomShow_Click(sender As Object, e As EventArgs) Handles btnModalCustomShow.Click
        Modals.Show()
    End Sub

    Private Sub Button3_Click(sender As Object, e As EventArgs) Handles Button3.Click
        Me.Hide()
        TerrazaClima.Show()
    End Sub
End Class
