<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()>
Partial Class ComandaItem
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
        Dim resources As System.ComponentModel.ComponentResourceManager = New System.ComponentModel.ComponentResourceManager(GetType(ComandaItem))
        Me.lblName = New System.Windows.Forms.Label()
        Me.PictureBox3 = New System.Windows.Forms.PictureBox()
        Me.PictureBox1 = New System.Windows.Forms.PictureBox()
        Me.PictureBox2 = New System.Windows.Forms.PictureBox()
        Me.lblFecha = New System.Windows.Forms.Label()
        Me.lblCod = New System.Windows.Forms.Label()
        Me.layoutComanda = New System.Windows.Forms.FlowLayoutPanel()
        Me.cbDone = New System.Windows.Forms.CheckBox()
        Me.lblPrecio = New System.Windows.Forms.Label()
        Me.btnShowPedidos = New System.Windows.Forms.Button()
        Me.PictureBox4 = New System.Windows.Forms.PictureBox()
        CType(Me.PictureBox3, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.PictureBox1, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.PictureBox2, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.PictureBox4, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'lblName
        '
        Me.lblName.Font = New System.Drawing.Font("Microsoft Sans Serif", 14.25!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.lblName.ImageAlign = System.Drawing.ContentAlignment.TopCenter
        Me.lblName.Location = New System.Drawing.Point(1088, 113)
        Me.lblName.Name = "lblName"
        Me.lblName.Size = New System.Drawing.Size(164, 26)
        Me.lblName.TabIndex = 31
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
        Me.PictureBox3.TabIndex = 30
        Me.PictureBox3.TabStop = False
        '
        'PictureBox1
        '
        Me.PictureBox1.Cursor = System.Windows.Forms.Cursors.Hand
        Me.PictureBox1.Image = CType(resources.GetObject("PictureBox1.Image"), System.Drawing.Image)
        Me.PictureBox1.Location = New System.Drawing.Point(1148, 570)
        Me.PictureBox1.Name = "PictureBox1"
        Me.PictureBox1.Size = New System.Drawing.Size(104, 99)
        Me.PictureBox1.SizeMode = System.Windows.Forms.PictureBoxSizeMode.CenterImage
        Me.PictureBox1.TabIndex = 29
        Me.PictureBox1.TabStop = False
        '
        'PictureBox2
        '
        Me.PictureBox2.Image = CType(resources.GetObject("PictureBox2.Image"), System.Drawing.Image)
        Me.PictureBox2.Location = New System.Drawing.Point(277, 47)
        Me.PictureBox2.Name = "PictureBox2"
        Me.PictureBox2.Size = New System.Drawing.Size(602, 591)
        Me.PictureBox2.SizeMode = System.Windows.Forms.PictureBoxSizeMode.CenterImage
        Me.PictureBox2.TabIndex = 32
        Me.PictureBox2.TabStop = False
        '
        'lblFecha
        '
        Me.lblFecha.Font = New System.Drawing.Font("Microsoft Sans Serif", 24.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.lblFecha.Location = New System.Drawing.Point(384, 113)
        Me.lblFecha.Name = "lblFecha"
        Me.lblFecha.Size = New System.Drawing.Size(188, 41)
        Me.lblFecha.TabIndex = 33
        Me.lblFecha.Text = "FECHA"
        Me.lblFecha.TextAlign = System.Drawing.ContentAlignment.BottomCenter
        '
        'lblCod
        '
        Me.lblCod.BackColor = System.Drawing.Color.FromArgb(CType(CType(253, Byte), Integer), CType(CType(246, Byte), Integer), CType(CType(236, Byte), Integer))
        Me.lblCod.Font = New System.Drawing.Font("Microsoft Sans Serif", 18.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.lblCod.Location = New System.Drawing.Point(412, 176)
        Me.lblCod.Name = "lblCod"
        Me.lblCod.Size = New System.Drawing.Size(144, 41)
        Me.lblCod.TabIndex = 34
        Me.lblCod.Text = "FECHA"
        Me.lblCod.TextAlign = System.Drawing.ContentAlignment.MiddleLeft
        '
        'layoutComanda
        '
        Me.layoutComanda.AutoScroll = True
        Me.layoutComanda.BackColor = System.Drawing.Color.FromArgb(CType(CType(253, Byte), Integer), CType(CType(246, Byte), Integer), CType(CType(236, Byte), Integer))
        Me.layoutComanda.Location = New System.Drawing.Point(412, 228)
        Me.layoutComanda.Name = "layoutComanda"
        Me.layoutComanda.Size = New System.Drawing.Size(328, 299)
        Me.layoutComanda.TabIndex = 35
        '
        'cbDone
        '
        Me.cbDone.AutoSize = True
        Me.cbDone.BackColor = System.Drawing.Color.FromArgb(CType(CType(253, Byte), Integer), CType(CType(246, Byte), Integer), CType(CType(236, Byte), Integer))
        Me.cbDone.Font = New System.Drawing.Font("Microsoft Sans Serif", 12.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.cbDone.Location = New System.Drawing.Point(609, 537)
        Me.cbDone.Name = "cbDone"
        Me.cbDone.Size = New System.Drawing.Size(121, 24)
        Me.cbDone.TabIndex = 36
        Me.cbDone.Text = "REALIZADO"
        Me.cbDone.UseVisualStyleBackColor = False
        '
        'lblPrecio
        '
        Me.lblPrecio.BackColor = System.Drawing.Color.FromArgb(CType(CType(253, Byte), Integer), CType(CType(246, Byte), Integer), CType(CType(236, Byte), Integer))
        Me.lblPrecio.Font = New System.Drawing.Font("Microsoft Sans Serif", 18.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.lblPrecio.Location = New System.Drawing.Point(441, 530)
        Me.lblPrecio.Name = "lblPrecio"
        Me.lblPrecio.Size = New System.Drawing.Size(162, 31)
        Me.lblPrecio.TabIndex = 37
        Me.lblPrecio.Text = "PRECIO"
        Me.lblPrecio.TextAlign = System.Drawing.ContentAlignment.MiddleLeft
        '
        'btnShowPedidos
        '
        Me.btnShowPedidos.Font = New System.Drawing.Font("Microsoft Sans Serif", 24.0!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.btnShowPedidos.Location = New System.Drawing.Point(412, 597)
        Me.btnShowPedidos.Name = "btnShowPedidos"
        Me.btnShowPedidos.Size = New System.Drawing.Size(341, 82)
        Me.btnShowPedidos.TabIndex = 31
        Me.btnShowPedidos.Text = "GUARDAR"
        Me.btnShowPedidos.UseVisualStyleBackColor = True
        '
        'PictureBox4
        '
        Me.PictureBox4.BackColor = System.Drawing.Color.FromArgb(CType(CType(0, Byte), Integer), CType(CType(0, Byte), Integer), CType(CType(0, Byte), Integer), CType(CType(0, Byte), Integer))
        Me.PictureBox4.Image = CType(resources.GetObject("PictureBox4.Image"), System.Drawing.Image)
        Me.PictureBox4.Location = New System.Drawing.Point(277, -2)
        Me.PictureBox4.Name = "PictureBox4"
        Me.PictureBox4.Size = New System.Drawing.Size(602, 59)
        Me.PictureBox4.SizeMode = System.Windows.Forms.PictureBoxSizeMode.CenterImage
        Me.PictureBox4.TabIndex = 38
        Me.PictureBox4.TabStop = False
        '
        'ComandaItem
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(1264, 681)
        Me.Controls.Add(Me.PictureBox4)
        Me.Controls.Add(Me.btnShowPedidos)
        Me.Controls.Add(Me.lblPrecio)
        Me.Controls.Add(Me.cbDone)
        Me.Controls.Add(Me.layoutComanda)
        Me.Controls.Add(Me.lblCod)
        Me.Controls.Add(Me.lblFecha)
        Me.Controls.Add(Me.PictureBox2)
        Me.Controls.Add(Me.lblName)
        Me.Controls.Add(Me.PictureBox3)
        Me.Controls.Add(Me.PictureBox1)
        Me.Name = "ComandaItem"
        Me.Text = "ComandaItem"
        CType(Me.PictureBox3, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.PictureBox1, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.PictureBox2, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.PictureBox4, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub

    Public WithEvents lblName As Label
    Public WithEvents PictureBox3 As PictureBox
    Friend WithEvents PictureBox1 As PictureBox
    Friend WithEvents PictureBox2 As PictureBox
    Friend WithEvents lblFecha As Label
    Friend WithEvents lblCod As Label
    Friend WithEvents layoutComanda As FlowLayoutPanel
    Friend WithEvents cbDone As CheckBox
    Friend WithEvents lblPrecio As Label
    Friend WithEvents btnShowPedidos As Button
    Friend WithEvents PictureBox4 As PictureBox
End Class
