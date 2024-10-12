package me.writt.server.presentation.folder

import me.writt.server.application.folder.FolderService
import me.writt.server.domain.folder.Folder
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class FolderController(
    private val folderService: FolderService,
): FolderAPI {
    override fun getById(
        folderId: UUID
    ): ResponseEntity<Folder> {
        val folder = folderService.getById(folderId)
        return ResponseEntity.ok(folder)
    }

    override fun create(
        request: FolderPayload.CreateRequest
    ): ResponseEntity<Folder> {
        val folder = folderService.create(request.name)
        return ResponseEntity.ok(folder)
    }

    override fun updateName(
        folderId: UUID,
        request: FolderPayload.NameUpdateRequest
    ): ResponseEntity<Unit> {
        folderService.updateName(folderId, request.name)
        return ResponseEntity.ok().build()
    }
}