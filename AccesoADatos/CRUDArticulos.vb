Imports System.IO

Public Class CRUDArticulos

    Private Sub PictureBox1_Click(sender As Object, e As EventArgs)
        AdminPanel.Show()
        Me.Close()

    End Sub

    Private Sub CRUDEmpleados_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        lblName.Text = AccesoADatos.Login.txtUser.Text
        Me.CenterToScreen()
        GetArticulosByNombre("")
        AddIconColumns()
        StyleDataGridView()
        dataArticulos.Sort(dataArticulos.Columns("nombre"), System.ComponentModel.ListSortDirection.Ascending)
    End Sub

    Public Sub GetArticulos()
        Try
            Dim query As String = "SELECT * FROM Articulos"
            Dim adapter As New SqlClient.SqlDataAdapter(query, connection)
            Dim dataSet As New DataSet()
            adapter.Fill(dataSet, "Articulos")
            dataArticulos.DataSource = dataSet.Tables("Articulos")
            dataArticulos.AutoSize = True
        Catch ex As Exception
            ModalHelper.FatalError("Error al obtener los articulos: " & ex.Message)
        End Try
    End Sub

    Private Sub AddIconColumns()
        Dim deleteColumn As New DataGridViewImageColumn()
        Try
            deleteColumn.Image = Image.FromFile(Path.GetFullPath("src/deleteicon (2).png"))
            deleteColumn.Name = "Eliminar"
            dataArticulos.Columns.Add(deleteColumn)
        Catch ex As Exception
            MsgBox(ex.Message())
        End Try

        Dim updateColumn As New DataGridViewImageColumn()
        Try
            updateColumn.Image = Image.FromFile(Path.GetFullPath("src/editing (2) (1).png"))
            updateColumn.Name = "Actualizar"
            dataArticulos.Columns.Add(updateColumn)
        Catch ex As Exception
            MsgBox(ex.Message)
        End Try

    End Sub

    Private Sub DataEmpleados_CellContentClick(sender As Object, data As DataGridViewCellEventArgs) Handles dataArticulos.CellContentClick

        If data.RowIndex >= 0 Then
            If data.ColumnIndex = dataArticulos.Columns("Eliminar").Index Then

                Dim diag As DialogResult = MessageBox.Show("Estas seguro que quieres borrar este articulo?", "Advertencia", MessageBoxButtons.YesNoCancel, MessageBoxIcon.Warning)
                If diag = DialogResult.Yes Then
                    Dim query As String = "DELETE FROM Articulos WHERE codart = " & dataArticulos.Rows(data.RowIndex).Cells("codart").Value
                    Dim command As New SqlClient.SqlCommand(query, connection)
                    Try
                        If connection.State = ConnectionState.Closed Then
                            connection.Open()
                        End If
                        command.ExecuteNonQuery()
                        connection.Close()
                        ModalHelper.Info("Articulo borrado correctamente")
                    Catch ex As Exception
                        ModalHelper.FatalError("Error al eliminar el articulo: " & ex.Message)
                    End Try
                    GetArticulosByNombre("")
                End If
            ElseIf data.ColumnIndex = dataArticulos.Columns("Actualizar").Index Then

                lblCod.Text = dataArticulos.Rows(data.RowIndex).Cells("codart").Value
                lblNombre.Text = dataArticulos.Rows(data.RowIndex).Cells("nombre").Value
                lblPrecio.Text = dataArticulos.Rows(data.RowIndex).Cells("precio").Value
                lblUrl.Text = dataArticulos.Rows(data.RowIndex).Cells("url").Value
                lblCantidad.Text = dataArticulos.Rows(data.RowIndex).Cells("cantidad").Value
                connection.Close()
                Me.Hide()
                UpdateArticulo.Show()

            End If
        End If
    End Sub

    Private Sub PictureBox2_Click(sender As Object, e As EventArgs) Handles PictureBox2.Click
        Me.Close()
        AddArticulo.Show()
    End Sub

    Public Sub StyleDataGridView()
        ' Overall DataGridView style
        dataArticulos.BorderStyle = BorderStyle.None
        dataArticulos.BackgroundColor = Color.FromArgb(240, 240, 240)
        dataArticulos.CellBorderStyle = DataGridViewCellBorderStyle.SingleHorizontal
        dataArticulos.GridColor = Color.FromArgb(220, 220, 220)

        ' Row styles
        dataArticulos.RowTemplate.Height = 45
        dataArticulos.AlternatingRowsDefaultCellStyle.BackColor = Color.FromArgb(250, 250, 250)
        dataArticulos.DefaultCellStyle.BackColor = Color.White
        dataArticulos.DefaultCellStyle.SelectionBackColor = Color.FromArgb(87, 166, 74)
        dataArticulos.DefaultCellStyle.SelectionForeColor = Color.White
        dataArticulos.RowsDefaultCellStyle.Font = New Font("Segoe UI", 14)
        dataArticulos.DefaultCellStyle.Alignment = DataGridViewContentAlignment.MiddleCenter

        ' Header styles
        dataArticulos.EnableHeadersVisualStyles = False
        dataArticulos.ColumnHeadersBorderStyle = DataGridViewHeaderBorderStyle.None
        dataArticulos.ColumnHeadersDefaultCellStyle.BackColor = Color.FromArgb(52, 152, 219)
        dataArticulos.ColumnHeadersDefaultCellStyle.ForeColor = Color.White
        dataArticulos.ColumnHeadersDefaultCellStyle.Font = New Font("Segoe UI Semibold", 18)
        dataArticulos.ColumnHeadersDefaultCellStyle.Padding = New Padding(0, 8, 0, 8)
        dataArticulos.ColumnHeadersDefaultCellStyle.Alignment = DataGridViewContentAlignment.MiddleCenter
        dataArticulos.ColumnHeadersHeight = 40

        dataArticulos.RowHeadersVisible = False
        dataArticulos.AutoResizeColumnHeadersHeight()
        dataArticulos.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill
        dataArticulos.ScrollBars = ScrollBars.Both

        ' Format price columns with € symbol
        For Each column As DataGridViewColumn In dataArticulos.Columns
            If column.Name.ToLower().Contains("precio") OrElse column.Name.ToLower().Contains("price") Then
                column.DefaultCellStyle.Format = " #,##0.00 €"
            End If
        Next

        ' Set Dock property to fill the container
        dataArticulos.Dock = DockStyle.Fill

        ' Disable AutoSize
        dataArticulos.AutoSize = False

        ' Set column sizing mode
        dataArticulos.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill
        dataArticulos.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.DisableResizing

        ' Enable both scrollbars
        dataArticulos.ScrollBars = ScrollBars.Both

        ' Ensure all columns have a reasonable minimum width
        For Each column As DataGridViewColumn In dataArticulos.Columns
            column.MinimumWidth = 100
        Next

        For Each column As DataGridViewColumn In dataArticulos.Columns
            column.DefaultCellStyle.Padding = New Padding(5, 0, 5, 0)
            column.DefaultCellStyle.Alignment = DataGridViewContentAlignment.MiddleCenter
        Next
    End Sub

    Private Sub BtnSearch_Click(sender As Object, e As EventArgs) Handles btnSearch.Click
        GetArticulosByNombre(txtName.Text)
    End Sub

    Public Sub GetArticulosByNombre(ByVal nombre As String)
        Try
            Dim query As String = "SELECT * FROM Articulos WHERE nombre LIKE @nombre"
            Using adapter As New SqlClient.SqlDataAdapter(query, connection)
                adapter.SelectCommand.Parameters.AddWithValue("@nombre", "%" & nombre & "%")
                Dim dataSet As New DataSet()
                adapter.Fill(dataSet, "Articulos")
                dataArticulos.DataSource = dataSet.Tables("Articulos")
            End Using
            StyleDataGridView()
        Catch ex As Exception
            ModalHelper.FatalError("Error al buscar articulos: " & ex.Message)
        End Try
    End Sub

    Private Sub PictureBox1_Click_1(sender As Object, e As EventArgs) Handles PictureBox1.Click
            Me.Close()
            connection.Close()
            AdminPanel.Show()
        End Sub

End Class