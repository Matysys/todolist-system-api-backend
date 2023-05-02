package com.api.todolistsystem.service

import com.api.todolistsystem.entity.ToDoListEntity
import com.api.todolistsystem.entity.UserEntity

interface IToDoListService {
    fun save(toDoListEntity: ToDoListEntity): ToDoListEntity

    fun findById(id: Long): ToDoListEntity

    fun findAllByUser(userId: Long): List<ToDoListEntity>

    fun delete(id: Long)
}