package me.writt.server.presentation.filetree

import io.swagger.v3.oas.annotations.Operation
import me.writt.server.domain.file.File
import me.writt.server.domain.filetree.FileTree
import me.writt.server.domain.folder.Folder
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import java.util.UUID

@RequestMapping("/api/v1/file-trees")
interface FileTreeAPI {
    @GetMapping("/root")
    @Operation(summary = "루트 파일트리 목록 조회")
    fun getRoots(): ResponseEntity<List<FileTree>>

    @GetMapping("/{treeId}")
    @Operation(summary = "ID로 파일트리 조회")
    fun getById(@PathVariable("treeId") treeId: UUID): ResponseEntity<FileTree>

    @PostMapping("/{treeId}/files")
    @Operation(summary = "파일 생성")
    fun createFile(
        @PathVariable("treeId") treeId: UUID,
        @RequestBody request: FileTreePayload.CreateFileRequest
    ): ResponseEntity<File>

    @PostMapping("/{treeId}/folders")
    @Operation(summary = "폴더 생성")
    fun createFolders(
        @PathVariable("treeId") treeId: UUID,
        @RequestBody request: FileTreePayload.CreateFolderRequest
    ): ResponseEntity<Folder>
}