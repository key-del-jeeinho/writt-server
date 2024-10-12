package me.writt.server.domain.filetree

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import me.writt.server.domain.file.File
import me.writt.server.domain.folder.Folder
import java.util.*

@Entity
class FileTree(
    @Id
    val treeId: UUID,

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "folder_id")
    val folder: Folder,

    files: List<File>,

    children: List<FileTree> = mutableListOf(),

    parent: FileTree?
) {
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "tree_id")
    private val mutableFiles: MutableList<File> = files.toMutableList()

    @JsonIgnore
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    private val mutableChildren: MutableList<FileTree> = children.toMutableList()

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    var parent: FileTree? = parent
        private set

    @get:JsonIgnore
    val files get() = mutableFiles

    @get:JsonIgnore
    val children get() = mutableChildren

    val folderId: UUID get() = folder.folderId
    @get: JsonProperty("x_folderName")
    val folderName: String get() = folder.name
    val fileIds: List<UUID> get() = files.map(File::fileId)
    val folderIds: List<UUID> get() = children.map(FileTree::folder).map(Folder::folderId)
    val childIds: List<UUID> get() = children.map(FileTree::treeId)
    val parentId: UUID? get() = parent?.treeId

    companion object {
        fun create(folder: Folder): FileTree = FileTree(
            treeId = UUID.randomUUID(),
            folder = folder,
            files = emptyList(),
            children = emptyList(),
            parent = null
        )
    }

    fun addFile(file: File) {
        mutableFiles.add(file)
    }

    fun addChild(child: FileTree) {
        child.parent = this
        mutableChildren.add(child)
    }
}