package me.writt.server.application.folder

import me.writt.server.domain.folder.Folder
import me.writt.server.domain.folder.FolderCreatedEvent
import me.writt.server.domain.folder.FolderRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class FolderService(
    private val folderRepository: FolderRepository,
    private val eventPublisher: ApplicationEventPublisher,
) {
    fun getById(folderId: UUID): Folder {
        val folder = folderRepository.findByIdOrNull(folderId)
            ?: throw FolderNotFoundException(folderId)

        return folder
    }

    @Transactional
    fun create(name: String): Folder {
        val folder = Folder.create(name)
        val created = folderRepository.save(folder)

        val event = FolderCreatedEvent(created)
        eventPublisher.publishEvent(event)

        return created
    }

    fun updateName(folderId: UUID, name: String) {
        val folder = getById(folderId)
        folder.updateName(name)
        folderRepository.save(folder)
    }
}