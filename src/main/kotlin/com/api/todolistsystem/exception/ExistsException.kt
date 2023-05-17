package com.api.todolistsystem.exception

data class ExistsException(override val message: String?) : Exception(message)
