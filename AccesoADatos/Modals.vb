Public Class Modals
    Private Sub Button1_Click(sender As Object, e As EventArgs) Handles Button1.Click
        FatalError.ShowDialog()
    End Sub

    Private Sub Button2_Click(sender As Object, e As EventArgs) Handles Button2.Click
        Info.ShowDialog()
    End Sub


End Class