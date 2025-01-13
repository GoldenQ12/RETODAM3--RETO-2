<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()>
Partial Class CRUDEmpleados
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
        Dim DataGridViewCellStyle5 As System.Windows.Forms.DataGridViewCellStyle = New System.Windows.Forms.DataGridViewCellStyle()
        Dim DataGridViewCellStyle6 As System.Windows.Forms.DataGridViewCellStyle = New System.Windows.Forms.DataGridViewCellStyle()
        Dim DataGridViewCellStyle7 As System.Windows.Forms.DataGridViewCellStyle = New System.Windows.Forms.DataGridViewCellStyle()
        Dim DataGridViewCellStyle8 As System.Windows.Forms.DataGridViewCellStyle = New System.Windows.Forms.DataGridViewCellStyle()
        Dim resources As System.ComponentModel.ComponentResourceManager = New System.ComponentModel.ComponentResourceManager(GetType(CRUDEmpleados))
        Me.dataEmpleados = New System.Windows.Forms.DataGridView()
        Me.nombre = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.fechanac = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.categoria = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.edad = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.contrasena = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.codemp = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.EmpleadosBindingSource = New System.Windows.Forms.BindingSource(Me.components)
        Me.RETODataSetBindingSource = New System.Windows.Forms.BindingSource(Me.components)
        Me.RETODataSet = New AccesoADatos.RETODataSet()
        Me.EmpleadosTableAdapter = New AccesoADatos.RETODataSetTableAdapters.EmpleadosTableAdapter()
        Me.lblNombre = New System.Windows.Forms.Label()
        Me.lblFechaNac = New System.Windows.Forms.Label()
        Me.lblCategoria = New System.Windows.Forms.Label()
        Me.lblEdad = New System.Windows.Forms.Label()
        Me.lblContrasena = New System.Windows.Forms.Label()
        Me.lblCod = New System.Windows.Forms.Label()
        Me.PictureBox2 = New System.Windows.Forms.PictureBox()
        Me.lblName = New System.Windows.Forms.Label()
        Me.PictureBox3 = New System.Windows.Forms.PictureBox()
        Me.txtName = New System.Windows.Forms.TextBox()
        Me.btnSearch = New System.Windows.Forms.Button()
        Me.PictureBox1 = New System.Windows.Forms.PictureBox()
        Me.Panel1 = New System.Windows.Forms.Panel()
        Me.PictureBox4 = New System.Windows.Forms.PictureBox()
        CType(Me.dataEmpleados, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.EmpleadosBindingSource, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.RETODataSetBindingSource, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.RETODataSet, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.PictureBox2, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.PictureBox3, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.PictureBox1, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.Panel1.SuspendLayout()
        CType(Me.PictureBox4, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'dataEmpleados
        '
        Me.dataEmpleados.AllowUserToAddRows = False
        Me.dataEmpleados.AllowUserToDeleteRows = False
        Me.dataEmpleados.AutoGenerateColumns = False
        Me.dataEmpleados.BackgroundColor = System.Drawing.Color.White
        DataGridViewCellStyle5.Alignment = System.Windows.Forms.DataGridViewContentAlignment.MiddleCenter
        DataGridViewCellStyle5.WrapMode = System.Windows.Forms.DataGridViewTriState.[False]
        Me.dataEmpleados.ColumnHeadersDefaultCellStyle = DataGridViewCellStyle5
        Me.dataEmpleados.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize
        Me.dataEmpleados.Columns.AddRange(New System.Windows.Forms.DataGridViewColumn() {Me.nombre, Me.fechanac, Me.categoria, Me.edad, Me.contrasena, Me.codemp})
        Me.dataEmpleados.Cursor = System.Windows.Forms.Cursors.Hand
        Me.dataEmpleados.DataSource = Me.EmpleadosBindingSource
        DataGridViewCellStyle6.Alignment = System.Windows.Forms.DataGridViewContentAlignment.MiddleCenter
        DataGridViewCellStyle6.BackColor = System.Drawing.SystemColors.Window
        DataGridViewCellStyle6.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.25!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        DataGridViewCellStyle6.ForeColor = System.Drawing.SystemColors.ControlText
        DataGridViewCellStyle6.SelectionBackColor = System.Drawing.SystemColors.Highlight
        DataGridViewCellStyle6.SelectionForeColor = System.Drawing.SystemColors.HighlightText
        DataGridViewCellStyle6.WrapMode = System.Windows.Forms.DataGridViewTriState.[False]
        Me.dataEmpleados.DefaultCellStyle = DataGridViewCellStyle6
        Me.dataEmpleados.GridColor = System.Drawing.SystemColors.ActiveCaption
        Me.dataEmpleados.Location = New System.Drawing.Point(0, 0)
        Me.dataEmpleados.Margin = New System.Windows.Forms.Padding(25)
        Me.dataEmpleados.Name = "dataEmpleados"
        Me.dataEmpleados.ReadOnly = True
        DataGridViewCellStyle7.Alignment = System.Windows.Forms.DataGridViewContentAlignment.MiddleLeft
        DataGridViewCellStyle7.WrapMode = System.Windows.Forms.DataGridViewTriState.[False]
        Me.dataEmpleados.RowHeadersDefaultCellStyle = DataGridViewCellStyle7
        Me.dataEmpleados.RowHeadersWidth = 50
        DataGridViewCellStyle8.BackColor = System.Drawing.Color.FromArgb(CType(CType(192, Byte), Integer), CType(CType(255, Byte), Integer), CType(CType(192, Byte), Integer))
        DataGridViewCellStyle8.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.25!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        DataGridViewCellStyle8.ForeColor = System.Drawing.SystemColors.Desktop
        Me.dataEmpleados.RowsDefaultCellStyle = DataGridViewCellStyle8
        Me.dataEmpleados.Size = New System.Drawing.Size(728, 318)
        Me.dataEmpleados.TabIndex = 14
        '
        'nombre
        '
        Me.nombre.AutoSizeMode = System.Windows.Forms.DataGridViewAutoSizeColumnMode.ColumnHeader
        Me.nombre.DataPropertyName = "nombre"
        Me.nombre.FillWeight = 1000.0!
        Me.nombre.HeaderText = "Nombre"
        Me.nombre.MinimumWidth = 6
        Me.nombre.Name = "nombre"
        Me.nombre.ReadOnly = True
        Me.nombre.Width = 69
        '
        'fechanac
        '
        Me.fechanac.AutoSizeMode = System.Windows.Forms.DataGridViewAutoSizeColumnMode.ColumnHeader
        Me.fechanac.DataPropertyName = "fechanac"
        Me.fechanac.HeaderText = "Fecha de nacimiento"
        Me.fechanac.MinimumWidth = 6
        Me.fechanac.Name = "fechanac"
        Me.fechanac.ReadOnly = True
        Me.fechanac.Width = 131
        '
        'categoria
        '
        Me.categoria.AutoSizeMode = System.Windows.Forms.DataGridViewAutoSizeColumnMode.ColumnHeader
        Me.categoria.DataPropertyName = "categoria"
        Me.categoria.HeaderText = "Categoría"
        Me.categoria.MinimumWidth = 6
        Me.categoria.Name = "categoria"
        Me.categoria.ReadOnly = True
        Me.categoria.Width = 79
        '
        'edad
        '
        Me.edad.AutoSizeMode = System.Windows.Forms.DataGridViewAutoSizeColumnMode.ColumnHeader
        Me.edad.DataPropertyName = "edad"
        Me.edad.HeaderText = "Edad"
        Me.edad.MinimumWidth = 6
        Me.edad.Name = "edad"
        Me.edad.ReadOnly = True
        Me.edad.Width = 57
        '
        'contrasena
        '
        Me.contrasena.AutoSizeMode = System.Windows.Forms.DataGridViewAutoSizeColumnMode.ColumnHeader
        Me.contrasena.DataPropertyName = "contrasena"
        Me.contrasena.HeaderText = "password"
        Me.contrasena.MinimumWidth = 6
        Me.contrasena.Name = "contrasena"
        Me.contrasena.ReadOnly = True
        Me.contrasena.Visible = False
        '
        'codemp
        '
        Me.codemp.DataPropertyName = "codemp"
        Me.codemp.HeaderText = "codemp"
        Me.codemp.MinimumWidth = 6
        Me.codemp.Name = "codemp"
        Me.codemp.ReadOnly = True
        Me.codemp.Visible = False
        Me.codemp.Width = 125
        '
        'EmpleadosBindingSource
        '
        Me.EmpleadosBindingSource.DataMember = "Empleados"
        Me.EmpleadosBindingSource.DataSource = Me.RETODataSetBindingSource
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
        'EmpleadosTableAdapter
        '
        Me.EmpleadosTableAdapter.ClearBeforeFill = True
        '
        'lblNombre
        '
        Me.lblNombre.AutoSize = True
        Me.lblNombre.Location = New System.Drawing.Point(1007, 7)
        Me.lblNombre.Margin = New System.Windows.Forms.Padding(2, 0, 2, 0)
        Me.lblNombre.Name = "lblNombre"
        Me.lblNombre.Size = New System.Drawing.Size(39, 13)
        Me.lblNombre.TabIndex = 15
        Me.lblNombre.Text = "Label1"
        Me.lblNombre.Visible = False
        '
        'lblFechaNac
        '
        Me.lblFechaNac.AutoSize = True
        Me.lblFechaNac.Location = New System.Drawing.Point(1007, 28)
        Me.lblFechaNac.Margin = New System.Windows.Forms.Padding(2, 0, 2, 0)
        Me.lblFechaNac.Name = "lblFechaNac"
        Me.lblFechaNac.Size = New System.Drawing.Size(39, 13)
        Me.lblFechaNac.TabIndex = 16
        Me.lblFechaNac.Text = "Label2"
        Me.lblFechaNac.Visible = False
        '
        'lblCategoria
        '
        Me.lblCategoria.AutoSize = True
        Me.lblCategoria.Location = New System.Drawing.Point(1007, 59)
        Me.lblCategoria.Margin = New System.Windows.Forms.Padding(2, 0, 2, 0)
        Me.lblCategoria.Name = "lblCategoria"
        Me.lblCategoria.Size = New System.Drawing.Size(39, 13)
        Me.lblCategoria.TabIndex = 17
        Me.lblCategoria.Text = "Label3"
        Me.lblCategoria.Visible = False
        '
        'lblEdad
        '
        Me.lblEdad.AutoSize = True
        Me.lblEdad.Location = New System.Drawing.Point(1007, 85)
        Me.lblEdad.Margin = New System.Windows.Forms.Padding(2, 0, 2, 0)
        Me.lblEdad.Name = "lblEdad"
        Me.lblEdad.Size = New System.Drawing.Size(39, 13)
        Me.lblEdad.TabIndex = 18
        Me.lblEdad.Text = "Label4"
        Me.lblEdad.Visible = False
        '
        'lblContrasena
        '
        Me.lblContrasena.AutoSize = True
        Me.lblContrasena.Location = New System.Drawing.Point(1007, 115)
        Me.lblContrasena.Margin = New System.Windows.Forms.Padding(2, 0, 2, 0)
        Me.lblContrasena.Name = "lblContrasena"
        Me.lblContrasena.Size = New System.Drawing.Size(39, 13)
        Me.lblContrasena.TabIndex = 19
        Me.lblContrasena.Text = "Label5"
        Me.lblContrasena.Visible = False
        '
        'lblCod
        '
        Me.lblCod.AutoSize = True
        Me.lblCod.Location = New System.Drawing.Point(1007, 137)
        Me.lblCod.Margin = New System.Windows.Forms.Padding(2, 0, 2, 0)
        Me.lblCod.Name = "lblCod"
        Me.lblCod.Size = New System.Drawing.Size(39, 13)
        Me.lblCod.TabIndex = 20
        Me.lblCod.Text = "Label6"
        Me.lblCod.Visible = False
        '
        'PictureBox2
        '
        Me.PictureBox2.Cursor = System.Windows.Forms.Cursors.Hand
        Me.PictureBox2.Image = CType(resources.GetObject("PictureBox2.Image"), System.Drawing.Image)
        Me.PictureBox2.Location = New System.Drawing.Point(99, 207)
        Me.PictureBox2.Name = "PictureBox2"
        Me.PictureBox2.Size = New System.Drawing.Size(104, 99)
        Me.PictureBox2.SizeMode = System.Windows.Forms.PictureBoxSizeMode.CenterImage
        Me.PictureBox2.TabIndex = 21
        Me.PictureBox2.TabStop = False
        '
        'lblName
        '
        Me.lblName.Font = New System.Drawing.Font("Microsoft Sans Serif", 14.25!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.lblName.ImageAlign = System.Drawing.ContentAlignment.TopCenter
        Me.lblName.Location = New System.Drawing.Point(1088, 90)
        Me.lblName.Name = "lblName"
        Me.lblName.Size = New System.Drawing.Size(164, 98)
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
        'txtName
        '
        Me.txtName.Font = New System.Drawing.Font("Microsoft Sans Serif", 18.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.txtName.Location = New System.Drawing.Point(12, 166)
        Me.txtName.Name = "txtName"
        Me.txtName.Size = New System.Drawing.Size(230, 35)
        Me.txtName.TabIndex = 29
        '
        'btnSearch
        '
        Me.btnSearch.Image = CType(resources.GetObject("btnSearch.Image"), System.Drawing.Image)
        Me.btnSearch.Location = New System.Drawing.Point(248, 166)
        Me.btnSearch.Name = "btnSearch"
        Me.btnSearch.Size = New System.Drawing.Size(50, 35)
        Me.btnSearch.TabIndex = 30
        Me.btnSearch.UseVisualStyleBackColor = True
        '
        'PictureBox1
        '
        Me.PictureBox1.Cursor = System.Windows.Forms.Cursors.Hand
        Me.PictureBox1.Image = CType(resources.GetObject("PictureBox1.Image"), System.Drawing.Image)
        Me.PictureBox1.Location = New System.Drawing.Point(1148, 570)
        Me.PictureBox1.Name = "PictureBox1"
        Me.PictureBox1.Size = New System.Drawing.Size(104, 99)
        Me.PictureBox1.SizeMode = System.Windows.Forms.PictureBoxSizeMode.CenterImage
        Me.PictureBox1.TabIndex = 31
        Me.PictureBox1.TabStop = False
        '
        'Panel1
        '
        Me.Panel1.Controls.Add(Me.dataEmpleados)
        Me.Panel1.Location = New System.Drawing.Point(361, 166)
        Me.Panel1.Name = "Panel1"
        Me.Panel1.Size = New System.Drawing.Size(832, 400)
        Me.Panel1.TabIndex = 32
        '
        'PictureBox4
        '
        Me.PictureBox4.Image = CType(resources.GetObject("PictureBox4.Image"), System.Drawing.Image)
        Me.PictureBox4.Location = New System.Drawing.Point(359, 78)
        Me.PictureBox4.Name = "PictureBox4"
        Me.PictureBox4.Size = New System.Drawing.Size(596, 59)
        Me.PictureBox4.SizeMode = System.Windows.Forms.PictureBoxSizeMode.CenterImage
        Me.PictureBox4.TabIndex = 33
        Me.PictureBox4.TabStop = False
        '
        'CRUDEmpleados
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(1264, 681)
        Me.Controls.Add(Me.PictureBox4)
        Me.Controls.Add(Me.Panel1)
        Me.Controls.Add(Me.lblName)
        Me.Controls.Add(Me.PictureBox3)
        Me.Controls.Add(Me.PictureBox1)
        Me.Controls.Add(Me.btnSearch)
        Me.Controls.Add(Me.txtName)
        Me.Controls.Add(Me.PictureBox2)
        Me.Controls.Add(Me.lblCod)
        Me.Controls.Add(Me.lblContrasena)
        Me.Controls.Add(Me.lblEdad)
        Me.Controls.Add(Me.lblCategoria)
        Me.Controls.Add(Me.lblFechaNac)
        Me.Controls.Add(Me.lblNombre)
        Me.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle
        Me.MaximizeBox = False
        Me.MinimizeBox = False
        Me.Name = "CRUDEmpleados"
        Me.Text = "CRUDEmpleados"
        CType(Me.dataEmpleados, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.EmpleadosBindingSource, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.RETODataSetBindingSource, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.RETODataSet, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.PictureBox2, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.PictureBox3, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.PictureBox1, System.ComponentModel.ISupportInitialize).EndInit()
        Me.Panel1.ResumeLayout(False)
        CType(Me.PictureBox4, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub
    Friend WithEvents dataEmpleados As DataGridView
    Friend WithEvents RETODataSetBindingSource As BindingSource
    Friend WithEvents RETODataSet As RETODataSet
    Friend WithEvents EmpleadosBindingSource As BindingSource
    Friend WithEvents EmpleadosTableAdapter As RETODataSetTableAdapters.EmpleadosTableAdapter
    Public WithEvents lblNombre As Label
    Public WithEvents lblFechaNac As Label
    Public WithEvents lblCategoria As Label
    Public WithEvents lblEdad As Label
    Public WithEvents lblContrasena As Label
    Public WithEvents lblCod As Label
    Friend WithEvents PictureBox2 As PictureBox
    Public WithEvents lblName As Label
    Public WithEvents PictureBox3 As PictureBox
    Friend WithEvents nombre As DataGridViewTextBoxColumn
    Friend WithEvents fechanac As DataGridViewTextBoxColumn
    Friend WithEvents categoria As DataGridViewTextBoxColumn
    Friend WithEvents edad As DataGridViewTextBoxColumn
    Friend WithEvents contrasena As DataGridViewTextBoxColumn
    Friend WithEvents codemp As DataGridViewTextBoxColumn
    Friend WithEvents txtName As TextBox
    Friend WithEvents btnSearch As Button
    Friend WithEvents PictureBox1 As PictureBox
    Friend WithEvents Panel1 As Panel
    Friend WithEvents PictureBox4 As PictureBox
End Class
