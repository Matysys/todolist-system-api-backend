package com.api.todolistsystem.dto

import java.time.LocalDate

data class ToDoDto(
    val name: String,
    val description: String,
    val finalDate: LocalDate,
    val priority: String
)
