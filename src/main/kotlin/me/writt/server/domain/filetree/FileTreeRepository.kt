package me.writt.server.domain.filetree

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface FileTreeRepository: JpaRepository<FileTree, UUID> {
    fun findAllByParentIsNull(): List<FileTree>
}