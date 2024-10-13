package me.writt.server.domain.folder

import org.springframework.context.ApplicationEvent

data class FolderCreatedEvent(
    val folder: Folder
): ApplicationEvent(folder)