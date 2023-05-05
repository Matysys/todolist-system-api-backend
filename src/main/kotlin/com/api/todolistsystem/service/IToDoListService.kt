package com.api.todolistsystem.service

import com.api.todolistsystem.dto.ToDoDto
import com.api.todolistsystem.dto.ToDoListDetailsDto
import com.api.todolistsystem.dto.ToDoUpdateDto
import com.api.todolistsystem.entity.ToDoListEntity
import com.api.todolistsystem.entity.UserEntity

interface IToDoListService {
    fun save(toDoListEntity: ToDoListEntity): ToDoListEntity

    fun findById(id: Long): ToDoListEntity

    fun findAllByUser(userId: Long): List<ToDoListEntity>

    fun findDetailsByUser(userId: Long): ToDoListDetailsDto

    fun delete(id: Long)

    fun update(toDoUpdateDto: ToDoUpdateDto): Int
}