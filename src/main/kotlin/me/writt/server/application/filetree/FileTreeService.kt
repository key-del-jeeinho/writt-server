package me.writt.server.application.filetree

import me.writt.server.domain.file.File
import me.writt.server.domain.file.FileRepository
import me.writt.server.domain.filetree.FileTree
import me.writt.server.domain.filetree.FileTreeRepository
import me.writt.server.domain.folder.Folder
import me.writt.server.domain.folder.FolderRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class FileTreeService(
    private val fileTreeRepository: FileTreeRepository,
    private val fileRepository: FileRepository,
    private val folderRepository: FolderRepository,
) {
    fun getRoots(): List<FileTree> {
        return fileTreeRepository.findAllByParentIsNull()
    }

    fun getById(treeId: UUID): FileTree {
        val fileTree = fileTreeRepository.findByIdOrNull(treeId)
            ?: throw FileTreeNotFoundException(treeId)

        return fileTree
    }

    @Transactional
    fun createFile(
        treeId: UUID,
        name: String,
        ext: String
    ): File {
        val file = File.create(name = name, ext = ext)
        val fileTree = getById(treeId)
        fileTree.addFile(file)

        val savedFile = fileRepository.save(file)
        fileTreeRepository.save(fileTree)

        return savedFile
    }

    @Transactional
    fun createFolder(
        treeId: UUID,
        name: String,
    ): Folder {
        val folder = Folder.create(name = name)
        val newFileTree = FileTree.create(folder)

        val fileTree = getById(treeId)
        fileTree.addChild(newFileTree)

        val savedFolder = folderRepository.save(folder)
        fileTreeRepository.save(newFileTree)
        fileTreeRepository.save(fileTree)

        return savedFolder
    }
}