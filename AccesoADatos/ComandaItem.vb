Public Class ComandaItem
    Private Sub PictureBox1_Click(sender As Object, e As EventArgs) Handles PictureBox1.Click
        Me.Close()
        Comandas.Show()
    End Sub

    Private Sub ComandaItem_Load(sender As Object, e As EventArgs) Handles MyBase.Load

        lblName.Text = AccesoADatos.Login.txtUser.Text
        lblFecha.Text = Comandas.lblFecha.Text
        PictureBox1.SizeMode = PictureBoxSizeMode.CenterImage
        lblCod.Text = "Pedido: " & Comandas.lblCod.Text
        lblPrecio.Text = "TOTAL: " & GetPrecioTotalById() & "€"
        If Comandas.lblDone.Text.Equals("Si") Then
            cbDone.Checked = True
            cbDone.Enabled = False
        Else
            cbDone.Checked = False
        End If
        layoutComanda.HorizontalScroll.Visible = False
        Me.CenterToScreen()
        LoadApp()
    End Sub

    Public Sub LoadApp()
        Dim nombres As String() = GetArticulosById()
        Dim cantidades As Integer() = GetCantidadesById()
        For i As Integer = 0 To nombres.Length - 1
            Try
                Dim panel As New Panel With {
                    .Width = 300,
                    .Height = 30,
                    .Margin = New Padding(10),
                    .Name = $"panel{1}",
                    .BackColor = Color.FromArgb(253, 246, 236, 255),
                    .BorderStyle = BorderStyle.FixedSingle,
                    .Padding = New Padding(10)
                }

                Dim labelPrueba As New Label With {
                    .Text = $"{nombres(i)} - x{cantidades(i)} ",
                    .TextAlign = ContentAlignment.MiddleCenter,
                    .AutoSize = False,
                    .Width = layoutComanda.Width - 20,
                    .Height = 30,
                    .Font = New Font("Segoe UI", 12, FontStyle.Bold),
                    .ForeColor = Color.DarkSlateGray,
                    .Padding = New Padding(10, 5, 10, 5),
                    .Margin = New Padding(5)
                    }
                layoutComanda.Controls.Add(panel)
                panel.Controls.Add(labelPrueba)
            Catch ex As Exception
                MsgBox("Error" & ex.Message, Title:="Error")
            End Try
        Next i
    End Sub

    Public Function GetArticulosById() As String()
        Dim query As String = "SELECT nombre FROM Articulos WHERE codart IN (SELECT codart FROM LineaPedidos WHERE codped = @codped)"
        Dim command As New SqlClient.SqlCommand(query, connection)
        command.Parameters.AddWithValue("@codped", Comandas.lblCod.Text)
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
            Return nombres.ToArray()
        Catch ex As Exception
            MsgBox("Error articulos: " & ex.Message)
            Return Nothing
        End Try
    End Function

    Public Function GetCantidadesById() As Integer()
        Dim query As String = "SELECT cantidad FROM LineaPedidos WHERE codped IN (SELECT codped FROM Pedidos WHERE codped = @codped)"
        Dim command As New SqlClient.SqlCommand(query, connection)
        command.Parameters.AddWithValue("@codped", Comandas.lblCod.Text)
        Try
            If connection.State = ConnectionState.Closed Then
                connection.Open()
            End If
            Dim reader As SqlClient.SqlDataReader = command.ExecuteReader()
            Dim cantidades As New List(Of Integer)
            While reader.Read()
                cantidades.Add(reader("cantidad").ToString())
            End While
            reader.Close()
            connection.Close()
            Return cantidades.ToArray()
        Catch ex As Exception
            MsgBox("Error articulos: " & ex.Message)
            Return Nothing
        End Try
    End Function

    Public Function GetPrecioTotalById() As Double
        Dim query As String = "SELECT precio_final FROM LineaPedidos WHERE codped = @codped"
        Dim command As New SqlClient.SqlCommand(query, connection)
        command.Parameters.AddWithValue("@codped", Comandas.lblCod.Text)
        Try
            If connection.State = ConnectionState.Closed Then
                connection.Open()
            End If
            Dim reader As SqlClient.SqlDataReader = command.ExecuteReader()
            Dim precio As Double = 0.00
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

    Private Sub BtnShowPedidos_Click(sender As Object, e As EventArgs) Handles btnShowPedidos.Click
        Dim query As String = ""
        If cbDone.Checked Then
            query = "UPDATE LineaPedidos SET realizado = 1 WHERE codped = @codped"
            Dim command As New SqlClient.SqlCommand(query, connection)
            command.Parameters.AddWithValue("@codped", Comandas.lblCod.Text)
            Try
                If connection.State = ConnectionState.Closed Then
                    connection.Open()
                End If
                command.ExecuteNonQuery()
                connection.Close()
                ModalHelper.Info("Actualizado correctamente")
            Catch ex As Exception
                MsgBox("Error " & ex.Message, Title:="Error")
            End Try
        Else
            query = "UPDATE LineaPedidos SET realizado = 0 WHERE codped = @codped"
            Dim command As New SqlClient.SqlCommand(query, connection)
            command.Parameters.AddWithValue("@codped", Comandas.lblCod.Text)
            Try
                If connection.State = ConnectionState.Closed Then
                    connection.Open()
                End If
                command.ExecuteNonQuery()
                connection.Close()
                ModalHelper.Info("Actualizado correctamente")
            Catch ex As Exception
                MsgBox("Error " & ex.Message, Title:="Error")
            End Try
        End If
    End Sub

    Private Sub layoutComanda_Paint(sender As Object, e As PaintEventArgs) Handles layoutComanda.Paint

    End Sub
End Class