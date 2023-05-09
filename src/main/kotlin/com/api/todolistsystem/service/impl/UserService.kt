package com.api.todolistsystem.service.impl

import com.api.todolistsystem.controller.UserController
import com.api.todolistsystem.dto.UserLoginResponseDto
import com.api.todolistsystem.entity.ToDoListEntity
import com.api.todolistsystem.entity.UserEntity
import com.api.todolistsystem.exception.LoginException
import com.api.todolistsystem.jwt.Jwt
import com.api.todolistsystem.repository.UserRepository
import com.api.todolistsystem.service.IUserService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service
import java.security.SecureRandom
import java.util.*

@Service
class UserService: IUserService {

    @Autowired lateinit var userRepository: UserRepository

    override fun save(userEntity: UserEntity): UserEntity {
        userEntity.encryptPassword()
        return this.userRepository.save(userEntity)
    }

    override fun findById(id: Long): UserEntity {
        return this.userRepository.findById(id).orElseThrow { IllegalArgumentException("User not found") }
    }

    override fun delete(id: Long) {
        val userEntity: UserEntity = this.findById(id)
        this.userRepository.delete(userEntity)
    }

    override fun checkUserLogin(email: String, password: String): String {
        val user: UserEntity? = this.userRepository.findByEmail(email)
        if (user != null && BCrypt.checkpw(password, user.password)) {

            val token: String = Jwt.criarToken(user.email, user.id!!, user.name)

            val responseDto = LoginResponseDto(token)
            val responseJson: String = ObjectMapper().writeValueAsString(responseDto)
            return responseJson
        } else {
            throw LoginException("E-mail ou senha inválidos")
        }

    }
    data class LoginResponseDto(val token: String)


}