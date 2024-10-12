package me.writt.server.application.file

import me.writt.server.domain.file.File
import me.writt.server.domain.file.FileRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class FileService(
    private val fileRepository: FileRepository,
) {
    fun getById(fileId: UUID): File {
        val file = fileRepository.findByIdOrNull(fileId)
            ?: throw FileNotFoundException(fileId)

        return file
    }

    fun create(name: String, ext: String): File {
        val file = File.create(name = name, ext = ext)
        return fileRepository.save(file)
    }

    fun updateName(fileId: UUID, name: String) {
        val file = getById(fileId)
        file.updateName(name)
        fileRepository.save(file)
    }

    fun updateDescription(fileId: UUID, description: String) {
        val file = getById(fileId)
        file.updateDescription(description)
        fileRepository.save(file)
    }

    fun updateContent(fileId: UUID, content: String) {
        val file = getById(fileId)
        file.updateContent(content)
        fileRepository.save(file)
    }
}