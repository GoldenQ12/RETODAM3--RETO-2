Imports System.IO
Imports System.Security.Cryptography

Public Class CRUDEmpleados
    Private Sub PictureBox1_Click(sender As Object, e As EventArgs)
        AdminPanel.Show()
        Me.Close()

    End Sub

    Private Sub CRUDEmpleados_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        lblName.Text = AccesoADatos.Login.txtUser.Text
        Me.CenterToScreen()
        GetEmpleados()
        AddIconColumns()
        StyleDataGridView()
        dataEmpleados.Sort(dataEmpleados.Columns("nombre"), System.ComponentModel.ListSortDirection.Ascending)
    End Sub

    Public Sub GetEmpleados()
        Try
            Dim query As String = "SELECT * FROM Empleados"
            Dim adapter As New SqlClient.SqlDataAdapter(query, connection)
            Dim dataSet As New DataSet()
            adapter.Fill(dataSet, "Empleados")
            dataEmpleados.DataSource = dataSet.Tables("Empleados")
            dataEmpleados.AutoSize = True
        Catch ex As Exception
            ModalHelper.FatalError("Error al obtener los empleados: " & ex.Message)
        End Try
    End Sub

    Private Sub AddIconColumns()
        Dim deleteColumn As New DataGridViewImageColumn()
        Try
            deleteColumn.Image = Image.FromFile(Path.GetFullPath("src/deleteicon (2).png"))
            deleteColumn.Name = "Eliminar"
            dataEmpleados.Columns.Add(deleteColumn)
        Catch ex As Exception
            MsgBox(ex.Message())
        End Try

        Dim updateColumn As New DataGridViewImageColumn()
        Try
            updateColumn.Image = Image.FromFile(Path.GetFullPath("src/editing (2) (1).png"))
            updateColumn.Name = "Actualizar"
            dataEmpleados.Columns.Add(updateColumn)
        Catch ex As Exception
            MsgBox(ex.Message)
        End Try

    End Sub

    Private Sub DataEmpleados_CellContentClick(sender As Object, data As DataGridViewCellEventArgs) Handles dataEmpleados.CellContentClick

        If data.RowIndex >= 0 Then
            If data.ColumnIndex = dataEmpleados.Columns("Eliminar").Index Then

                Dim diag As DialogResult = MessageBox.Show("Estas seguro que quieres borrar a este empleado?", "Advertencia", MessageBoxButtons.YesNoCancel, MessageBoxIcon.Warning)
                If diag = DialogResult.Yes Then
                    Dim query As String = "DELETE FROM Empleados WHERE codemp = " & dataEmpleados.Rows(data.RowIndex).Cells("codemp").Value
                    Dim command As New SqlClient.SqlCommand(query, connection)
                    Try
                        If connection.State = ConnectionState.Closed Then
                            connection.Open()
                        End If
                        command.ExecuteNonQuery()
                        connection.Close()
                        ModalHelper.Info("Empleado borrado correctamente")
                    Catch ex As Exception
                        ModalHelper.FatalError("Error al eliminar el empleado " & ex.Message)
                    End Try
                    GetEmpleadosByNombre("")
                End If
            ElseIf data.ColumnIndex = dataEmpleados.Columns("Actualizar").Index Then

                lblCod.Text = dataEmpleados.Rows(data.RowIndex).Cells("codemp").Value
                lblNombre.Text = dataEmpleados.Rows(data.RowIndex).Cells("nombre").Value
                lblCategoria.Text = dataEmpleados.Rows(data.RowIndex).Cells("categoria").Value
                lblFechaNac.Text = dataEmpleados.Rows(data.RowIndex).Cells("fechanac").Value
                lblEdad.Text = dataEmpleados.Rows(data.RowIndex).Cells("edad").Value
                lblContrasena.Text = dataEmpleados.Rows(data.RowIndex).Cells("contrasena").Value
                connection.Close()
                Me.Hide()
                UpdateEmpleado.Show()

            End If
        End If
    End Sub

    Private Sub PictureBox2_Click(sender As Object, e As EventArgs) Handles PictureBox2.Click
        Me.Close()
        AddEmpleado.Show()
    End Sub

    Public Sub StyleDataGridView()
        ' Overall DataGridView style
        dataEmpleados.BorderStyle = BorderStyle.None
        dataEmpleados.BackgroundColor = Color.FromArgb(240, 240, 240)
        dataEmpleados.CellBorderStyle = DataGridViewCellBorderStyle.SingleHorizontal
        dataEmpleados.GridColor = Color.FromArgb(220, 220, 220)

        ' Row styles
        dataEmpleados.RowTemplate.Height = 60
        dataEmpleados.AlternatingRowsDefaultCellStyle.BackColor = Color.FromArgb(250, 250, 250)
        dataEmpleados.DefaultCellStyle.BackColor = Color.White
        dataEmpleados.DefaultCellStyle.SelectionBackColor = Color.FromArgb(87, 166, 74)
        dataEmpleados.DefaultCellStyle.SelectionForeColor = Color.White
        dataEmpleados.RowsDefaultCellStyle.Font = New Font("Segoe UI", 14)
        dataEmpleados.DefaultCellStyle.Alignment = DataGridViewContentAlignment.MiddleCenter

        ' Header styles
        dataEmpleados.EnableHeadersVisualStyles = False
        dataEmpleados.ColumnHeadersDefaultCellStyle.BackColor = Color.FromArgb(52, 152, 219)
        dataEmpleados.ColumnHeadersDefaultCellStyle.ForeColor = Color.White
        dataEmpleados.ColumnHeadersDefaultCellStyle.Font = New Font("Segoe UI Semibold", 18)
        dataEmpleados.ColumnHeadersDefaultCellStyle.Padding = New Padding(0, 8, 0, 8)
        dataEmpleados.ColumnHeadersDefaultCellStyle.Alignment = DataGridViewContentAlignment.MiddleCenter
        dataEmpleados.ColumnHeadersHeight = 50

        dataEmpleados.RowHeadersVisible = False
        dataEmpleados.AutoResizeColumnHeadersHeight()
        dataEmpleados.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill
        dataEmpleados.ScrollBars = ScrollBars.Both

        ' Set Dock property to fill the container
        dataEmpleados.Dock = DockStyle.Fill

        ' Disable AutoSize
        dataEmpleados.AutoSize = False

        ' Set column sizing mode
        dataEmpleados.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill
        dataEmpleados.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.DisableResizing

        ' Enable both scrollbars
        dataEmpleados.ScrollBars = ScrollBars.Both

        ' Ensure all columns have a reasonable minimum width
        For Each column As DataGridViewColumn In dataEmpleados.Columns
            column.MinimumWidth = 100
        Next

        For Each column As DataGridViewColumn In dataEmpleados.Columns
            column.DefaultCellStyle.Padding = New Padding(5, 0, 5, 0)
            column.DefaultCellStyle.Alignment = DataGridViewContentAlignment.MiddleCenter
        Next

        If dataEmpleados.Columns.Contains("Fecha de nacimiento") Then
            dataEmpleados.Columns("Fecha de nacimiento").HeaderCell.Style.Padding = New Padding(0, 3, 0, 3)
        End If
    End Sub

    Private Sub BtnSearch_Click(sender As Object, e As EventArgs) Handles btnSearch.Click
        GetEmpleadosByNombre(txtName.Text)
    End Sub

    Public Sub GetEmpleadosByNombre(ByVal nombre As String)
        Try
            Dim query As String = "SELECT * FROM Empleados WHERE nombre LIKE @nombre"
            Using adapter As New SqlClient.SqlDataAdapter(query, connection)
                adapter.SelectCommand.Parameters.AddWithValue("@nombre", "%" & nombre & "%")
                Dim dataSet As New DataSet()
                adapter.Fill(dataSet, "Empleados")
                dataEmpleados.DataSource = dataSet.Tables("Empleados")
            End Using
            StyleDataGridView()  ' Apply styling after setting the DataSource
        Catch ex As Exception
            ModalHelper.FatalError("Error al buscar empleados: " & ex.Message)
        End Try
    End Sub

    Private Sub PictureBox1_Click_1(sender As Object, e As EventArgs) Handles PictureBox1.Click
        Me.Close()
        connection.Close()
        AdminPanel.Show()
    End Sub
End Class
