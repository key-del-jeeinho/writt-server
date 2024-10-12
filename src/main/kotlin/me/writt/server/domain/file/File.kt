package me.writt.server.domain.file

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.OffsetDateTime
import java.util.UUID

@Entity
class File(
    @Id
    val fileId: UUID,
    name: String,
    description: String,
    val ext: String,
    content: String,
    val createdAt: OffsetDateTime,
    lastModifiedAt: OffsetDateTime,
) {
    var name: String = name
        private set

    var description: String = description
        private set

    var content: String = content
        private set

    var lastModifiedAt: OffsetDateTime = lastModifiedAt
        private set

    companion object {
        fun create(name: String, ext: String): File {
            val now = OffsetDateTime.now()
            return File(
                fileId = UUID.randomUUID(),
                name = name,
                description = "",
                ext = ext,
                content = "",
                createdAt = now,
                lastModifiedAt = now,
            )
        }
    }

    fun updateName(name: String) {
        this.name = name
        this.lastModifiedAt = OffsetDateTime.now()
    }

    fun updateDescription(description: String) {
        this.description = description
        this.lastModifiedAt = OffsetDateTime.now()
    }

    fun updateContent(content: String) {
        this.content = content
        this.lastModifiedAt = OffsetDateTime.now()
    }
}