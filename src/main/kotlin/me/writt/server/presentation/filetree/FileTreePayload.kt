package me.writt.server.presentation.filetree

object FileTreePayload {
    data class CreateFileRequest(
        val name: String,
        val ext: String,
    )

    data class CreateFolderRequest(
        val name: String,
    )
}