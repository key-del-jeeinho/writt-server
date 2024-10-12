package me.writt.server.presentation.folder

import io.swagger.v3.oas.annotations.Operation
import me.writt.server.domain.folder.Folder
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RequestMapping("/api/v1/folders")
interface FolderAPI {
    @GetMapping("/{folderId}")
    @Operation(summary = "ID로 폴더 조회")
    fun getById(@PathVariable folderId: UUID): ResponseEntity<Folder>

    @PostMapping
    @Operation(summary = "폴더 생성")
    fun create(
        @RequestBody request: FolderPayload.CreateRequest,
    ): ResponseEntity<Folder>

    @PutMapping("/{folderId}/name")
    @Operation(summary = "폴더명 변경")
    fun updateName(
        @PathVariable folderId: UUID,
        @RequestBody request: FolderPayload.NameUpdateRequest,
    ): ResponseEntity<Unit>
}