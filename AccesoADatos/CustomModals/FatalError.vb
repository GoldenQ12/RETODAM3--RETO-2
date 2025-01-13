Public Class FatalError
    Private Sub FatalError_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        Me.CenterToScreen()
    End Sub

    Private Sub BtnYes_Click(sender As Object, e As EventArgs) Handles btnYes.Click
        Me.Close()
    End Sub
End Class