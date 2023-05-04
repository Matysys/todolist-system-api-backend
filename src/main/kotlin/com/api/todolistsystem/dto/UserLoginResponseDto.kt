package com.api.todolistsystem.dto

import com.api.todolistsystem.entity.ToDoListEntity
import com.api.todolistsystem.entity.UserEntity

data class UserLoginResponseDto(
    val user: UserEntity,
    val todoList: MutableList<ToDoListEntity>?
)

