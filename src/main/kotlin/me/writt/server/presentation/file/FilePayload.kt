package me.writt.server.presentation.file

object FilePayload {
    data class CreateRequest(
        val name: String,
        val ext: String,
    )

    data class NameUpdateRequest(
        val name: String,
    )

    data class DescriptionUpdateRequest(
        val description: String,
    )

    data class ContentUpdateRequest(
        val content: String,
    )
}