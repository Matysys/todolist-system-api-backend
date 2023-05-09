package com.api.todolistsystem.service

import com.api.todolistsystem.dto.ToDoDto
import com.api.todolistsystem.dto.UserLoginResponseDto
import com.api.todolistsystem.entity.UserEntity
import java.util.*

interface IUserService {

    fun save(userEntity: UserEntity): UserEntity

    fun findById(id: Long): Optional<UserEntity>

    fun delete(id: Long)

    fun checkUserLogin(email: String, password: String): String

}