package com.api.todolistsystem.service.impl

import com.api.todolistsystem.dto.UserLoginResponseDto
import com.api.todolistsystem.entity.ToDoListEntity
import com.api.todolistsystem.entity.UserEntity
import com.api.todolistsystem.repository.UserRepository
import com.api.todolistsystem.service.IUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service

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

    override fun checkUserLogin(email: String, password: String): UserEntity {
        val user = this.userRepository.findByEmail(email) ?: throw IllegalArgumentException("Email ou senha inválidos!")
        if (BCrypt.checkpw(password, user.password)) {
            return user
        } else {
            throw IllegalArgumentException("Email ou senha inválidos!")
        }
    }

}