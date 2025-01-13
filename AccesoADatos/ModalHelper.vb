Module ModalHelper
    Public Sub FatalError(message As String)
        Dim errorForm As New FatalError()
        errorForm.ShowModal(message)
    End Sub



    Public Sub Info(message As String)
        Dim infoForm As New Info()
        infoForm.ShowModal(message)
    End Sub
End Module
