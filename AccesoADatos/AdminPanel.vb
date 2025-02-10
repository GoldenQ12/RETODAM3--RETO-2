Imports System.Data.SqlClient
Imports System.IO
Imports AccesoADatos

Public Class AdminPanel
    Private Sub PictureBox1_Click(sender As Object, e As EventArgs) Handles PictureBox1.Click
        Dim res = MessageBox.Show("Estas seguro que deseas cerrar sesion?", "Advertencia", MessageBoxButtons.OKCancel)
        If res = DialogResult.OK Then
            AccesoADatos.Login.txtUser.Text = ""
            AccesoADatos.Login.txtPassword.Text = ""
            Login.Show()
            Me.Close()
        End If
    End Sub

    Private Sub AdminPanel_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        Me.CenterToScreen()
        lblName.Text = AccesoADatos.Login.txtUser.Text
    End Sub

    Private Sub Button2_Click(sender As Object, e As EventArgs) Handles Button2.Click
        CRUDEmpleados.Show()
    End Sub

    Private Sub Button1_Click(sender As Object, e As EventArgs) Handles Button1.Click
        CRUDArticulos.Show()
    End Sub

    Private Sub Button3_Click(sender As Object, e As EventArgs) Handles Button3.Click
        LoadTXT()
    End Sub

    Public Sub LoadTXT()
        If connection.State = ConnectionState.Closed Then
            connection.Open()
        End If
        Dim filePath As String = "cambiar_stock.txt"
        Dim selectedArticles As New Dictionary(Of String, Integer)

        If File.Exists(filePath) Then
            Using reader As StreamReader = New StreamReader(filePath)
                Dim line As String
                Do While (reader.Peek() >= 0)
                    line = reader.ReadLine()
                    Dim parts As String() = line.Split(":"c)

                    If parts.Length = 2 Then
                        Dim nombre As String = parts(0).Trim()
                        Dim cantidad As Integer

                        ' Ensure quantity is a valid integer
                        If Integer.TryParse(parts(1).Trim(), cantidad) Then
                            selectedArticles(nombre) = cantidad
                        End If
                    End If
                Loop
            End Using
        Else
            Console.WriteLine("File not found!")
            Exit Sub
        End If

        For Each item In selectedArticles
            Dim nombre As String = item.Key
            Dim cantidad As Integer = item.Value


            ' Update query with parameters
            Dim query As String = "UPDATE Articulos SET cantidad = cantidad + @cantidad WHERE nombre = @nombre"
            Using command As New SqlCommand(query, connection)
                command.Parameters.AddWithValue("@cantidad", cantidad)
                command.Parameters.AddWithValue("@nombre", nombre)

                command.ExecuteNonQuery()
                Console.WriteLine($"Updated '{nombre}' with new quantity: {cantidad}")
            End Using
        Next

        ModalHelper.Info("Articulos actualizados correctamente")
    End Sub
End Class