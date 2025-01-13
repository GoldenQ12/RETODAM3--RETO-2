Imports System.IO
Imports System.Net.Configuration

Public Class Comandas
    Private Sub Comandas_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        lblName.Text = AccesoADatos.Login.txtUser.Text
        Me.CenterToScreen()
        GetPedidos()
        StyleDataGridView()
        AddIconColumns()
        dataComandas.Sort(dataComandas.Columns("Código"), System.ComponentModel.ListSortDirection.Descending)

    End Sub

    Private Sub PictureBox1_Click(sender As Object, e As EventArgs) Handles PictureBox1.Click
        Me.Close()
        Login.Show()
    End Sub

    Public Sub GetPedidos()
        Try
            Dim query As String = "
                SELECT DISTINCT p.codped AS Código, p.fecha AS Fecha, 
                       CASE WHEN lp.realizado = 1 THEN 'Si' ELSE 'No' END AS Realizado
                FROM Pedidos p
                LEFT JOIN LineaPedidos lp ON p.codped = lp.codped"

            Dim adapter As New SqlClient.SqlDataAdapter(query, connection)
            Dim dataSet As New DataSet()
            adapter.Fill(dataSet, "LineaPedidos")

            dataComandas.DataSource = dataSet.Tables("LineaPedidos")

        Catch ex As Exception
            ModalHelper.FatalError("Error al obtener los pedidos: " & ex.Message)
        End Try
    End Sub

    Private Sub DataComandas_CellContentClick(sender As Object, Data As DataGridViewCellEventArgs) Handles dataComandas.CellContentClick
        If Data.ColumnIndex = dataComandas.Columns("Mostrar").Index Then

            lblCod.Text = dataComandas.Rows(Data.RowIndex).Cells("Código").Value
            lblFecha.Text = dataComandas.Rows(Data.RowIndex).Cells("Fecha").Value
            lblDone.Text = dataComandas.Rows(Data.RowIndex).Cells("Realizado").Value
            connection.Close()
            Me.Hide()
            ComandaItem.Show()
        End If

    End Sub

    Private Sub AddIconColumns()
        Dim showColumn As New DataGridViewImageColumn()
        Try
            showColumn.Image = Image.FromFile(Path.GetFullPath("src/show (1) (1) (1).png"))
            showColumn.Name = "Mostrar"
            dataComandas.Columns.Add(showColumn)
        Catch ex As Exception
            MsgBox(ex.Message())
        End Try

    End Sub

    Public Sub StyleDataGridView()
        ' Overall DataGridView style
        dataComandas.BorderStyle = BorderStyle.None
        dataComandas.BackgroundColor = Color.FromArgb(240, 240, 240)
        dataComandas.CellBorderStyle = DataGridViewCellBorderStyle.SingleHorizontal
        dataComandas.GridColor = Color.FromArgb(220, 220, 220)

        ' Row styles
        dataComandas.RowTemplate.Height = 60
        dataComandas.AlternatingRowsDefaultCellStyle.BackColor = Color.FromArgb(250, 250, 250)
        dataComandas.DefaultCellStyle.BackColor = Color.White
        dataComandas.DefaultCellStyle.SelectionBackColor = Color.FromArgb(87, 166, 74)
        dataComandas.DefaultCellStyle.SelectionForeColor = Color.White
        dataComandas.RowsDefaultCellStyle.Font = New Font("Segoe UI", 14)
        dataComandas.DefaultCellStyle.Alignment = DataGridViewContentAlignment.MiddleCenter

        ' Header styles
        dataComandas.EnableHeadersVisualStyles = False
        dataComandas.ColumnHeadersBorderStyle = DataGridViewHeaderBorderStyle.None
        dataComandas.ColumnHeadersDefaultCellStyle.BackColor = Color.FromArgb(52, 152, 219)
        dataComandas.ColumnHeadersDefaultCellStyle.ForeColor = Color.White
        dataComandas.ColumnHeadersDefaultCellStyle.Font = New Font("Segoe UI Semibold", 18)
        dataComandas.ColumnHeadersDefaultCellStyle.Padding = New Padding(0, 8, 0, 8)
        dataComandas.ColumnHeadersDefaultCellStyle.Alignment = DataGridViewContentAlignment.MiddleCenter
        dataComandas.ColumnHeadersHeight = 50

        dataComandas.RowHeadersVisible = False
        dataComandas.AutoResizeColumnHeadersHeight()
        dataComandas.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill
        dataComandas.ScrollBars = ScrollBars.Both

        ' Set Dock property to fill the container
        dataComandas.Dock = DockStyle.Fill

        ' Disable AutoSize
        dataComandas.AutoSize = False

        ' Set column sizing mode
        dataComandas.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.DisableResizing

        ' Enable both scrollbars
        dataComandas.ScrollBars = ScrollBars.Both

        ' Ensure all columns have a reasonable minimum width
        For Each column As DataGridViewColumn In dataComandas.Columns
            column.MinimumWidth = 100
        Next

        For Each column As DataGridViewColumn In dataComandas.Columns
            column.DefaultCellStyle.Padding = New Padding(5, 0, 5, 0)
            column.DefaultCellStyle.Alignment = DataGridViewContentAlignment.MiddleCenter
        Next

    End Sub

    Private Sub Panel1_Paint(sender As Object, e As PaintEventArgs) Handles Panel1.Paint

    End Sub

    Private Sub BtnSearch_Click(sender As Object, e As EventArgs) Handles btnSearch.Click
        If txtCod.Text = "" Then
            GetPedidos()
        Else
            GetPedidosByCod(txtCod.Text)
        End If
    End Sub

    Public Sub GetPedidosByCod(ByVal codped As String)
        Try
            Dim query As String = "
                SELECT DISTINCT p.codped AS Código, p.fecha AS Fecha, 
                       CASE WHEN lp.realizado = 1 THEN 'Si' ELSE 'No' END AS Realizado
                FROM Pedidos p 
                LEFT JOIN LineaPedidos lp ON p.codped = lp.codped
                WHERE p.codped = @codped"

            Dim command As New SqlClient.SqlCommand(query, connection)
            command.Parameters.AddWithValue("@codped", codped)

            If connection.State = ConnectionState.Closed Then
                connection.Open()
            End If

            Dim adapter As New SqlClient.SqlDataAdapter(command)
            Dim dataSet As New DataSet()
            adapter.Fill(dataSet, "Pedidos")

            dataComandas.DataSource = dataSet.Tables("Pedidos")

        Catch ex As Exception
            MsgBox("Error al obtener los pedidos: " & ex.Message, Title:="Error")
        Finally
            If connection.State = ConnectionState.Open Then
                connection.Close()
            End If
        End Try
    End Sub
End Class