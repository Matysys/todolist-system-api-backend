package com.api.todolistsystem.exception

data class LoginException(override val message: String?) : Exception(message)
