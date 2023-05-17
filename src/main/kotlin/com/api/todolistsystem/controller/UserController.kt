package com.api.todolistsystem.controller

import com.api.todolistsystem.dto.UserDto
import com.api.todolistsystem.dto.UserLoginDto
import com.api.todolistsystem.dto.UserLoginResponseDto
import com.api.todolistsystem.entity.UserEntity
import com.api.todolistsystem.exception.ExistsException
import com.api.todolistsystem.exception.LoginException
import com.api.todolistsystem.jwt.Jwt
import com.api.todolistsystem.service.impl.UserService
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.validation.Valid
import org.apache.catalina.User
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.SecureRandom
import java.util.*

@RestController
@CrossOrigin(origins = ["*"], maxAge = 3600)
@RequestMapping("/api/user")
class UserController(private val userService: UserService) {

    @PostMapping
    fun saveUser(@RequestBody @Valid userDto: UserDto): ResponseEntity<String>{
        try {
            this.userService.save(userDto.toEntity())
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso!")
        } catch (ex: ExistsException) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.message)
        }

    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<Optional<UserEntity>>{
        val userEntity: Optional<UserEntity> = this.userService.findById(id)
        return ResponseEntity.status(HttpStatus.OK).body(userEntity)
    }

    @PostMapping("/login")
    fun login(@RequestBody @Valid userLoginDto: UserLoginDto): ResponseEntity<*>{
        try {
            val response: String = this.userService.checkUserLogin(userLoginDto.email, userLoginDto.password)
            return ResponseEntity.status(HttpStatus.OK).body(response)

        }catch(ex: LoginException){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.message)
        }

    }

    @DeleteMapping("/{id}")
    fun deleteUserById(@PathVariable id: Long): ResponseEntity<String>{
        this.userService.delete(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Usuário deletado com sucesso!");
    }



}