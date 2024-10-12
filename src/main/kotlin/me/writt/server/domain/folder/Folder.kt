package me.writt.server.domain.folder

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.OffsetDateTime
import java.util.UUID

@Entity
class Folder(
    @Id
    val folderId: UUID,
    name: String,
    val createdAt: OffsetDateTime
) {
    var name: String = name
        private set

    companion object {
        fun create(name: String): Folder {
            return Folder(
                folderId = UUID.randomUUID(),
                name = name,
                createdAt = OffsetDateTime.now()
            )
        }
    }

    fun updateName(name: String) {
        this.name = name
    }
}