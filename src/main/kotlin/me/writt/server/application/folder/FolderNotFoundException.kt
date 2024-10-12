package me.writt.server.application.folder

import java.util.UUID

class FolderNotFoundException(folderId: UUID): RuntimeException("Folder with ID '$folderId' not found")