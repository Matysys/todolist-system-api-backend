package com.api.todolistsystem.dto

import com.api.todolistsystem.entity.ToDoListEntity
import com.api.todolistsystem.entity.UserEntity
import org.apache.catalina.User
import java.time.LocalDate

data class ToDoDto(
    val name: String,
    val description: String,
    val finalDate: LocalDate,
    val priority: String,
    val userId: Long
){
    fun toEntity(): ToDoListEntity = ToDoListEntity(
        name = this.name,
        description = this.description,
        finalDate = this.finalDate,
        priority = this.priority,
        userEntity = UserEntity(id = this.userId)
    )
}
