<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()>
Partial Class Comandas
    Inherits System.Windows.Forms.Form

    'Form overrides dispose to clean up the component list.
    <System.Diagnostics.DebuggerNonUserCode()>
    Protected Overrides Sub Dispose(ByVal disposing As Boolean)
        Try
            If disposing AndAlso components IsNot Nothing Then
                components.Dispose()
            End If
        Finally
            MyBase.Dispose(disposing)
        End Try
    End Sub

    'Required by the Windows Form Designer
    Private components As System.ComponentModel.IContainer

    'NOTE: The following procedure is required by the Windows Form Designer
    'It can be modified using the Windows Form Designer.  
    'Do not modify it using the code editor.
    <System.Diagnostics.DebuggerStepThrough()>
    Private Sub InitializeComponent()
        Me.components = New System.ComponentModel.Container()
        Dim DataGridViewCellStyle1 As System.Windows.Forms.DataGridViewCellStyle = New System.Windows.Forms.DataGridViewCellStyle()
        Dim DataGridViewCellStyle2 As System.Windows.Forms.DataGridViewCellStyle = New System.Windows.Forms.DataGridViewCellStyle()
        Dim DataGridViewCellStyle3 As System.Windows.Forms.DataGridViewCellStyle = New System.Windows.Forms.DataGridViewCellStyle()
        Dim DataGridViewCellStyle4 As System.Windows.Forms.DataGridViewCellStyle = New System.Windows.Forms.DataGridViewCellStyle()
        Dim resources As System.ComponentModel.ComponentResourceManager = New System.ComponentModel.ComponentResourceManager(GetType(Comandas))
        Me.dataComandas = New System.Windows.Forms.DataGridView()
        Me.RETODataSetBindingSource = New System.Windows.Forms.BindingSource(Me.components)
        Me.RETODataSet = New AccesoADatos.RETODataSet()
        Me.PictureBox1 = New System.Windows.Forms.PictureBox()
        Me.lblName = New System.Windows.Forms.Label()
        Me.PictureBox3 = New System.Windows.Forms.PictureBox()
        Me.lblFecha = New System.Windows.Forms.Label()
        Me.lblCod = New System.Windows.Forms.Label()
        Me.lblDone = New System.Windows.Forms.Label()
        Me.Panel1 = New System.Windows.Forms.Panel()
        Me.btnSearch = New System.Windows.Forms.Button()
        Me.txtCod = New System.Windows.Forms.TextBox()
        Me.PictureBox4 = New System.Windows.Forms.PictureBox()
        CType(Me.dataComandas, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.RETODataSetBindingSource, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.RETODataSet, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.PictureBox1, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.PictureBox3, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.Panel1.SuspendLayout()
        CType(Me.PictureBox4, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'dataComandas
        '
        Me.dataComandas.AllowUserToAddRows = False
        Me.dataComandas.AllowUserToDeleteRows = False
        Me.dataComandas.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.AllCells
        Me.dataComandas.BackgroundColor = System.Drawing.Color.White
        DataGridViewCellStyle1.Alignment = System.Windows.Forms.DataGridViewContentAlignment.MiddleCenter
        DataGridViewCellStyle1.Font = New System.Drawing.Font("Microsoft Sans Serif", 48.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        DataGridViewCellStyle1.WrapMode = System.Windows.Forms.DataGridViewTriState.[False]
        Me.dataComandas.ColumnHeadersDefaultCellStyle = DataGridViewCellStyle1
        Me.dataComandas.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize
        Me.dataComandas.Cursor = System.Windows.Forms.Cursors.Hand
        DataGridViewCellStyle2.Alignment = System.Windows.Forms.DataGridViewContentAlignment.MiddleCenter
        DataGridViewCellStyle2.BackColor = System.Drawing.SystemColors.Window
        DataGridViewCellStyle2.Font = New System.Drawing.Font("Microsoft Sans Serif", 48.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        DataGridViewCellStyle2.ForeColor = System.Drawing.SystemColors.ControlText
        DataGridViewCellStyle2.SelectionBackColor = System.Drawing.SystemColors.Highlight
        DataGridViewCellStyle2.SelectionForeColor = System.Drawing.SystemColors.HighlightText
        DataGridViewCellStyle2.WrapMode = System.Windows.Forms.DataGridViewTriState.[False]
        Me.dataComandas.DefaultCellStyle = DataGridViewCellStyle2
        Me.dataComandas.GridColor = System.Drawing.SystemColors.ActiveCaption
        Me.dataComandas.Location = New System.Drawing.Point(0, 0)
        Me.dataComandas.Margin = New System.Windows.Forms.Padding(25)
        Me.dataComandas.Name = "dataComandas"
        Me.dataComandas.ReadOnly = True
        DataGridViewCellStyle3.Alignment = System.Windows.Forms.DataGridViewContentAlignment.MiddleLeft
        DataGridViewCellStyle3.WrapMode = System.Windows.Forms.DataGridViewTriState.[False]
        Me.dataComandas.RowHeadersDefaultCellStyle = DataGridViewCellStyle3
        Me.dataComandas.RowHeadersWidth = 80
        DataGridViewCellStyle4.BackColor = System.Drawing.Color.FromArgb(CType(CType(192, Byte), Integer), CType(CType(255, Byte), Integer), CType(CType(192, Byte), Integer))
        DataGridViewCellStyle4.Font = New System.Drawing.Font("Microsoft Sans Serif", 15.75!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        DataGridViewCellStyle4.ForeColor = System.Drawing.SystemColors.Desktop
        Me.dataComandas.RowsDefaultCellStyle = DataGridViewCellStyle4
        Me.dataComandas.Size = New System.Drawing.Size(600, 318)
        Me.dataComandas.TabIndex = 15
        '
        'RETODataSetBindingSource
        '
        Me.RETODataSetBindingSource.DataSource = Me.RETODataSet
        Me.RETODataSetBindingSource.Position = 0
        '
        'RETODataSet
        '
        Me.RETODataSet.DataSetName = "RETODataSet"
        Me.RETODataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema
        '
        'PictureBox1
        '
        Me.PictureBox1.Cursor = System.Windows.Forms.Cursors.Hand
        Me.PictureBox1.Image = CType(resources.GetObject("PictureBox1.Image"), System.Drawing.Image)
        Me.PictureBox1.Location = New System.Drawing.Point(1148, 570)
        Me.PictureBox1.Name = "PictureBox1"
        Me.PictureBox1.Size = New System.Drawing.Size(104, 99)
        Me.PictureBox1.SizeMode = System.Windows.Forms.PictureBoxSizeMode.CenterImage
        Me.PictureBox1.TabIndex = 16
        Me.PictureBox1.TabStop = False
        '
        'lblName
        '
        Me.lblName.Font = New System.Drawing.Font("Microsoft Sans Serif", 14.25!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.lblName.ImageAlign = System.Drawing.ContentAlignment.TopCenter
        Me.lblName.Location = New System.Drawing.Point(1088, 113)
        Me.lblName.Name = "lblName"
        Me.lblName.Size = New System.Drawing.Size(164, 26)
        Me.lblName.TabIndex = 28
        Me.lblName.Text = "Hello World"
        Me.lblName.TextAlign = System.Drawing.ContentAlignment.TopCenter
        '
        'PictureBox3
        '
        Me.PictureBox3.Image = CType(resources.GetObject("PictureBox3.Image"), System.Drawing.Image)
        Me.PictureBox3.Location = New System.Drawing.Point(1088, 12)
        Me.PictureBox3.Name = "PictureBox3"
        Me.PictureBox3.Size = New System.Drawing.Size(164, 98)
        Me.PictureBox3.SizeMode = System.Windows.Forms.PictureBoxSizeMode.CenterImage
        Me.PictureBox3.TabIndex = 27
        Me.PictureBox3.TabStop = False
        '
        'lblFecha
        '
        Me.lblFecha.AutoSize = True
        Me.lblFecha.Location = New System.Drawing.Point(1007, 84)
        Me.lblFecha.Margin = New System.Windows.Forms.Padding(2, 0, 2, 0)
        Me.lblFecha.Name = "lblFecha"
        Me.lblFecha.Size = New System.Drawing.Size(39, 13)
        Me.lblFecha.TabIndex = 46
        Me.lblFecha.Text = "Label2"
        Me.lblFecha.Visible = False
        '
        'lblCod
        '
        Me.lblCod.AutoSize = True
        Me.lblCod.Location = New System.Drawing.Point(1007, 71)
        Me.lblCod.Margin = New System.Windows.Forms.Padding(2, 0, 2, 0)
        Me.lblCod.Name = "lblCod"
        Me.lblCod.Size = New System.Drawing.Size(39, 13)
        Me.lblCod.TabIndex = 45
        Me.lblCod.Text = "Label1"
        Me.lblCod.Visible = False
        '
        'lblDone
        '
        Me.lblDone.AutoSize = True
        Me.lblDone.Location = New System.Drawing.Point(1007, 97)
        Me.lblDone.Margin = New System.Windows.Forms.Padding(2, 0, 2, 0)
        Me.lblDone.Name = "lblDone"
        Me.lblDone.Size = New System.Drawing.Size(39, 13)
        Me.lblDone.TabIndex = 47
        Me.lblDone.Text = "Label2"
        Me.lblDone.Visible = False
        '
        'Panel1
        '
        Me.Panel1.Controls.Add(Me.dataComandas)
        Me.Panel1.Location = New System.Drawing.Point(361, 150)
        Me.Panel1.Name = "Panel1"
        Me.Panel1.Size = New System.Drawing.Size(668, 351)
        Me.Panel1.TabIndex = 48
        '
        'btnSearch
        '
        Me.btnSearch.Image = CType(resources.GetObject("btnSearch.Image"), System.Drawing.Image)
        Me.btnSearch.Location = New System.Drawing.Point(272, 150)
        Me.btnSearch.Name = "btnSearch"
        Me.btnSearch.Size = New System.Drawing.Size(50, 35)
        Me.btnSearch.TabIndex = 50
        Me.btnSearch.UseVisualStyleBackColor = True
        '
        'txtCod
        '
        Me.txtCod.Font = New System.Drawing.Font("Microsoft Sans Serif", 18.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.txtCod.Location = New System.Drawing.Point(12, 150)
        Me.txtCod.Name = "txtCod"
        Me.txtCod.Size = New System.Drawing.Size(259, 35)
        Me.txtCod.TabIndex = 49
        '
        'PictureBox4
        '
        Me.PictureBox4.Image = CType(resources.GetObject("PictureBox4.Image"), System.Drawing.Image)
        Me.PictureBox4.Location = New System.Drawing.Point(359, 78)
        Me.PictureBox4.Name = "PictureBox4"
        Me.PictureBox4.Size = New System.Drawing.Size(596, 59)
        Me.PictureBox4.SizeMode = System.Windows.Forms.PictureBoxSizeMode.CenterImage
        Me.PictureBox4.TabIndex = 51
        Me.PictureBox4.TabStop = False
        '
        'Comandas
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(1264, 681)
        Me.Controls.Add(Me.PictureBox4)
        Me.Controls.Add(Me.btnSearch)
        Me.Controls.Add(Me.txtCod)
        Me.Controls.Add(Me.Panel1)
        Me.Controls.Add(Me.lblDone)
        Me.Controls.Add(Me.lblFecha)
        Me.Controls.Add(Me.lblCod)
        Me.Controls.Add(Me.lblName)
        Me.Controls.Add(Me.PictureBox3)
        Me.Controls.Add(Me.PictureBox1)
        Me.Name = "Comandas"
        CType(Me.dataComandas, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.RETODataSetBindingSource, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.RETODataSet, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.PictureBox1, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.PictureBox3, System.ComponentModel.ISupportInitialize).EndInit()
        Me.Panel1.ResumeLayout(False)
        CType(Me.PictureBox4, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub

    Friend WithEvents dataComandas As DataGridView
    Friend WithEvents RETODataSetBindingSource As BindingSource
    Friend WithEvents RETODataSet As RETODataSet
    Friend WithEvents PictureBox1 As PictureBox
    Public WithEvents lblName As Label
    Public WithEvents PictureBox3 As PictureBox
    Public WithEvents lblFecha As Label
    Public WithEvents lblCod As Label
    Public WithEvents lblDone As Label
    Friend WithEvents Panel1 As Panel
    Friend WithEvents btnSearch As Button
    Friend WithEvents txtCod As TextBox
    Friend WithEvents PictureBox4 As PictureBox
End Class
