package me.writt.server.presentation.file

import io.swagger.v3.oas.annotations.Operation
import me.writt.server.domain.file.File
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("/api/v1/files")
interface FileAPI {
    @GetMapping("/{fileId}")
    @Operation(summary = "ID로 파일 조회")
    fun getById(@PathVariable fileId: UUID): ResponseEntity<File>

    @PostMapping
    @Operation(summary = "파일 생성")
    fun create(request: FilePayload.CreateRequest): ResponseEntity<File>

    @PutMapping("/{fileId}/name")
    @Operation(summary = "파일 이름 변경")
    fun updateName(
        @PathVariable fileId: UUID,
        @RequestBody request: FilePayload.NameUpdateRequest,
    ): ResponseEntity<Unit>

    @PutMapping("/{fileId}/description")
    @Operation(summary = "파일 설명 변경")
    fun updateDescription(
        @PathVariable fileId: UUID,
        @RequestBody request: FilePayload.DescriptionUpdateRequest,
    ): ResponseEntity<Unit>

    @PutMapping("/{fileId}/content")
    @Operation(summary = "파일 컨텐츠 변경")
    fun updateContent(
        @PathVariable fileId: UUID,
        @RequestBody request: FilePayload.ContentUpdateRequest,
    ): ResponseEntity<Unit>
}