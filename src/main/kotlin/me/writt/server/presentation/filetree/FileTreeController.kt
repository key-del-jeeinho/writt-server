package me.writt.server.presentation.filetree

import me.writt.server.application.filetree.FileTreeService
import me.writt.server.domain.file.File
import me.writt.server.domain.filetree.FileTree
import me.writt.server.domain.folder.Folder
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class FileTreeController(
    private val fileTreeService: FileTreeService,
): FileTreeAPI {
    override fun getRoots(): ResponseEntity<List<FileTree>> {
        val roots = fileTreeService.getRoots()
        return ResponseEntity.ok(roots)
    }

    override fun getById(treeId: UUID): ResponseEntity<FileTree> {
        val fileTree = fileTreeService.getById(treeId)
        return ResponseEntity.ok(fileTree)
    }

    override fun createFile(
        treeId: UUID,
        request: FileTreePayload.CreateFileRequest
    ): ResponseEntity<File> {
        val file = fileTreeService.createFile(
            treeId,
            name = request.name,
            ext = request.ext
        )
        return ResponseEntity.ok(file)
    }

    override fun createFolders(
        treeId: UUID,
        request: FileTreePayload.CreateFolderRequest,
    ): ResponseEntity<Folder> {
        val folder = fileTreeService.createFolder(treeId, request.name)
        return ResponseEntity.ok(folder)
    }
}