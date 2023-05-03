package com.api.todolistsystem.service

import com.api.todolistsystem.entity.UserEntity

interface IUserService {

    fun save(userEntity: UserEntity): UserEntity

    fun findById(id: Long): UserEntity

    fun delete(id: Long)

    fun checkUserLogin(email: String, password: String): UserEntity?

}