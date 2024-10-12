package me.writt.server.domain.file

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface FileRepository: JpaRepository<File, UUID>