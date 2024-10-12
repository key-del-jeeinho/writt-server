package me.writt.server.presentation.file

import me.writt.server.application.file.FileService
import me.writt.server.domain.file.File
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class FileController(
    private val fileService: FileService,
): FileAPI {
    override fun getById(
        fileId: UUID
    ): ResponseEntity<File> {
        val file = fileService.getById(fileId)
        return ResponseEntity.ok(file)
    }

    override fun create(
        request: FilePayload.CreateRequest
    ): ResponseEntity<File> {
        val file = fileService.create(
            name = request.name,
            ext = request.ext,
        )
        return ResponseEntity.ok(file)
    }

    override fun updateName(
        fileId: UUID,
        request: FilePayload.NameUpdateRequest,
    ): ResponseEntity<Unit> {
        fileService.updateName(fileId, request.name)
        return ResponseEntity.ok().build()
    }

    override fun updateDescription(
        fileId: UUID,
        request: FilePayload.DescriptionUpdateRequest,
    ): ResponseEntity<Unit> {
        fileService.updateDescription(fileId, request.description)
        return ResponseEntity.ok().build()
    }

    override fun updateContent(
        fileId: UUID,
        request: FilePayload.ContentUpdateRequest
    ): ResponseEntity<Unit> {
        fileService.updateContent(fileId, request.content)
        return ResponseEntity.ok().build()
    }
}