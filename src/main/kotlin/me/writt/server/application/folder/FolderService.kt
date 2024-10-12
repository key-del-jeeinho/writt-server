package me.writt.server.application.folder

import me.writt.server.domain.folder.Folder
import me.writt.server.domain.folder.FolderRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class FolderService(
    private val folderRepository: FolderRepository,
) {
    fun getById(folderId: UUID): Folder {
        val folder = folderRepository.findByIdOrNull(folderId)
            ?: throw FolderNotFoundException(folderId)

        return folder
    }

    fun create(name: String): Folder {
        val folder = Folder.create(name)
        return folderRepository.save(folder)
    }

    fun updateName(folderId: UUID, name: String) {
        val folder = getById(folderId)
        folder.updateName(name)
        folderRepository.save(folder)
    }
}