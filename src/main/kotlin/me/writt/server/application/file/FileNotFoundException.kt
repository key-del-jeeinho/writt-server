package me.writt.server.application.file

import java.util.UUID

class FileNotFoundException(fileId: UUID): RuntimeException("File with ID '$fileId' not found")