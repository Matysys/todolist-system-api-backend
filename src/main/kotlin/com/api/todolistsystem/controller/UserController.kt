package com.api.todolistsystem.controller

import com.api.todolistsystem.dto.UserDto
import com.api.todolistsystem.dto.UserLoginDto
import com.api.todolistsystem.dto.UserLoginResponseDto
import com.api.todolistsystem.entity.UserEntity
import com.api.todolistsystem.jwt.Jwt
import com.api.todolistsystem.service.impl.UserService
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
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
        this.userService.save(userDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário salvo com sucesso!")
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<UserEntity>{
        val userEntity: UserEntity = this.userService.findById(id)
        return ResponseEntity.status(HttpStatus.OK).body(userEntity)
    }

    @PostMapping("/login")
    fun login(@RequestBody @Valid userLoginDto: UserLoginDto): ResponseEntity<*>{
        val userEntity: UserEntity = this.userService.checkUserLogin(userLoginDto.email, userLoginDto.password)
            ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou senha inválidos!")

        val random = SecureRandom()
        val key = ByteArray(256)
        random.nextBytes(key)
        val secret = Base64.getEncoder().encodeToString(key)

        val token = Jwt.criarToken(userLoginDto.email, userEntity.id!!, userEntity.name)

        val responseDto = LoginResponseDto(token)
        val responseJson: String = ObjectMapper().writeValueAsString(responseDto)

        println("TOKEN É $token")
        println("ResponseDTO É $responseDto")
        println("responseJson é $responseJson")

        return ResponseEntity.status(HttpStatus.OK).body(token)
    }

    data class LoginResponseDto(val token: String)

}