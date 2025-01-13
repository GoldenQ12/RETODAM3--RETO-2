<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()>
Partial Class TPVApp
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
        Dim resources As System.ComponentModel.ComponentResourceManager = New System.ComponentModel.ComponentResourceManager(GetType(TPVApp))
        Me.pbExit = New System.Windows.Forms.PictureBox()
        Me.lblName = New System.Windows.Forms.Label()
        Me.PictureBox3 = New System.Windows.Forms.PictureBox()
        Me.layoutProductos = New System.Windows.Forms.FlowLayoutPanel()
        Me.Panel1 = New System.Windows.Forms.Panel()
        Me.layoutComanda = New System.Windows.Forms.FlowLayoutPanel()
        Me.lblTotal = New System.Windows.Forms.Label()
        Me.btnRegister = New System.Windows.Forms.Button()
        Me.lblTitulo = New System.Windows.Forms.Label()
        Me.btnShowPedidos = New System.Windows.Forms.Button()
        Me.PictureBox4 = New System.Windows.Forms.PictureBox()
        CType(Me.pbExit, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.PictureBox3, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.Panel1.SuspendLayout()
        CType(Me.PictureBox4, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'pbExit
        '
        Me.pbExit.Cursor = System.Windows.Forms.Cursors.Hand
        Me.pbExit.Image = CType(resources.GetObject("pbExit.Image"), System.Drawing.Image)
        Me.pbExit.Location = New System.Drawing.Point(1468, 750)
        Me.pbExit.Name = "pbExit"
        Me.pbExit.Size = New System.Drawing.Size(104, 99)
        Me.pbExit.SizeMode = System.Windows.Forms.PictureBoxSizeMode.CenterImage
        Me.pbExit.TabIndex = 12
        Me.pbExit.TabStop = False
        '
        'lblName
        '
        Me.lblName.Font = New System.Drawing.Font("Microsoft Sans Serif", 14.25!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.lblName.ImageAlign = System.Drawing.ContentAlignment.TopCenter
        Me.lblName.Location = New System.Drawing.Point(1408, 113)
        Me.lblName.Name = "lblName"
        Me.lblName.Size = New System.Drawing.Size(164, 26)
        Me.lblName.TabIndex = 26
        Me.lblName.Text = "Hello World"
        Me.lblName.TextAlign = System.Drawing.ContentAlignment.TopCenter
        '
        'PictureBox3
        '
        Me.PictureBox3.Image = CType(resources.GetObject("PictureBox3.Image"), System.Drawing.Image)
        Me.PictureBox3.Location = New System.Drawing.Point(1408, 12)
        Me.PictureBox3.Name = "PictureBox3"
        Me.PictureBox3.Size = New System.Drawing.Size(164, 98)
        Me.PictureBox3.SizeMode = System.Windows.Forms.PictureBoxSizeMode.CenterImage
        Me.PictureBox3.TabIndex = 25
        Me.PictureBox3.TabStop = False
        '
        'layoutProductos
        '
        Me.layoutProductos.AutoScroll = True
        Me.layoutProductos.Location = New System.Drawing.Point(444, 142)
        Me.layoutProductos.Name = "layoutProductos"
        Me.layoutProductos.Size = New System.Drawing.Size(1128, 602)
        Me.layoutProductos.TabIndex = 27
        '
        'Panel1
        '
        Me.Panel1.Controls.Add(Me.layoutComanda)
        Me.Panel1.Location = New System.Drawing.Point(27, 181)
        Me.Panel1.Name = "Panel1"
        Me.Panel1.Size = New System.Drawing.Size(382, 563)
        Me.Panel1.TabIndex = 28
        '
        'layoutComanda
        '
        Me.layoutComanda.AutoScroll = True
        Me.layoutComanda.BackColor = System.Drawing.SystemColors.GradientActiveCaption
        Me.layoutComanda.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle
        Me.layoutComanda.Dock = System.Windows.Forms.DockStyle.Left
        Me.layoutComanda.FlowDirection = System.Windows.Forms.FlowDirection.TopDown
        Me.layoutComanda.Location = New System.Drawing.Point(0, 0)
        Me.layoutComanda.Margin = New System.Windows.Forms.Padding(10)
        Me.layoutComanda.Name = "layoutComanda"
        Me.layoutComanda.Padding = New System.Windows.Forms.Padding(10)
        Me.layoutComanda.Size = New System.Drawing.Size(382, 563)
        Me.layoutComanda.TabIndex = 1
        Me.layoutComanda.WrapContents = False
        '
        'lblTotal
        '
        Me.lblTotal.Font = New System.Drawing.Font("Microsoft Sans Serif", 14.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.lblTotal.Location = New System.Drawing.Point(277, 774)
        Me.lblTotal.Name = "lblTotal"
        Me.lblTotal.Size = New System.Drawing.Size(132, 82)
        Me.lblTotal.TabIndex = 0
        Me.lblTotal.TextAlign = System.Drawing.ContentAlignment.MiddleRight
        '
        'btnRegister
        '
        Me.btnRegister.Font = New System.Drawing.Font("Microsoft Sans Serif", 14.25!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.btnRegister.Location = New System.Drawing.Point(27, 774)
        Me.btnRegister.Name = "btnRegister"
        Me.btnRegister.Size = New System.Drawing.Size(220, 82)
        Me.btnRegister.TabIndex = 29
        Me.btnRegister.Text = "REGISTRAR"
        Me.btnRegister.UseVisualStyleBackColor = True
        '
        'lblTitulo
        '
        Me.lblTitulo.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle
        Me.lblTitulo.Font = New System.Drawing.Font("Microsoft Sans Serif", 24.0!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.lblTitulo.Location = New System.Drawing.Point(27, 142)
        Me.lblTitulo.Name = "lblTitulo"
        Me.lblTitulo.Size = New System.Drawing.Size(382, 39)
        Me.lblTitulo.TabIndex = 0
        Me.lblTitulo.Text = "COMANDA"
        Me.lblTitulo.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        Me.lblTitulo.UseCompatibleTextRendering = True
        '
        'btnShowPedidos
        '
        Me.btnShowPedidos.Font = New System.Drawing.Font("Microsoft Sans Serif", 14.25!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.btnShowPedidos.Location = New System.Drawing.Point(27, 12)
        Me.btnShowPedidos.Name = "btnShowPedidos"
        Me.btnShowPedidos.Size = New System.Drawing.Size(382, 82)
        Me.btnShowPedidos.TabIndex = 30
        Me.btnShowPedidos.Text = "MOSTRAR COMANDAS"
        Me.btnShowPedidos.UseVisualStyleBackColor = True
        '
        'PictureBox4
        '
        Me.PictureBox4.BackColor = System.Drawing.Color.FromArgb(CType(CType(0, Byte), Integer), CType(CType(0, Byte), Integer), CType(CType(0, Byte), Integer), CType(CType(0, Byte), Integer))
        Me.PictureBox4.Image = CType(resources.GetObject("PictureBox4.Image"), System.Drawing.Image)
        Me.PictureBox4.Location = New System.Drawing.Point(444, 12)
        Me.PictureBox4.Name = "PictureBox4"
        Me.PictureBox4.Size = New System.Drawing.Size(958, 82)
        Me.PictureBox4.SizeMode = System.Windows.Forms.PictureBoxSizeMode.CenterImage
        Me.PictureBox4.TabIndex = 39
        Me.PictureBox4.TabStop = False
        '
        'TPVApp
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(1584, 861)
        Me.Controls.Add(Me.lblTotal)
        Me.Controls.Add(Me.PictureBox4)
        Me.Controls.Add(Me.btnShowPedidos)
        Me.Controls.Add(Me.lblTitulo)
        Me.Controls.Add(Me.btnRegister)
        Me.Controls.Add(Me.Panel1)
        Me.Controls.Add(Me.layoutProductos)
        Me.Controls.Add(Me.lblName)
        Me.Controls.Add(Me.PictureBox3)
        Me.Controls.Add(Me.pbExit)
        Me.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle
        Me.MaximizeBox = False
        Me.MinimizeBox = False
        Me.Name = "TPVApp"
        Me.Text = " "
        CType(Me.pbExit, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.PictureBox3, System.ComponentModel.ISupportInitialize).EndInit()
        Me.Panel1.ResumeLayout(False)
        CType(Me.PictureBox4, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)

    End Sub

    Friend WithEvents pbExit As PictureBox
    Public WithEvents lblName As Label
    Public WithEvents PictureBox3 As PictureBox
    Friend WithEvents layoutProductos As FlowLayoutPanel
    Friend WithEvents Panel1 As Panel
    Friend WithEvents lblTotal As Label
    Friend WithEvents layoutComanda As FlowLayoutPanel
    Friend WithEvents VScrollBar1 As VScrollBar
    Friend WithEvents btnRegister As Button
    Friend WithEvents lblTitulo As Label
    Friend WithEvents btnShowPedidos As Button
    Friend WithEvents PictureBox4 As PictureBox
End Class
