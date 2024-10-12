package me.writt.server.domain.folder

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface FolderRepository: JpaRepository<Folder, UUID>