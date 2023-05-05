package com.api.todolistsystem.dto

data class ToDoListDetailsDto(
    val totalTasks: Long,
    val totalBaixa: Long,
    val totalMedia: Long,
    val totalAlta: Long,
    val totalOutOfLimit: Long,
    val totalfinished: Long,
)
