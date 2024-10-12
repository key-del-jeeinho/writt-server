package me.writt.server.application.filetree

import java.util.UUID

class FileTreeNotFoundException(treeId: UUID): RuntimeException("File tree with id: $treeId not found")