package me.writt.server.presentation.folder

object FolderPayload {
    data class CreateRequest(
        val name: String,
    )

    data class NameUpdateRequest(
        val name: String,
    )
}