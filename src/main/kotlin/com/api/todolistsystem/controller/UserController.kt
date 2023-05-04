package com.api.todolistsystem.controller

import com.api.todolistsystem.dto.UserDto
import com.api.todolistsystem.dto.UserLoginDto
import com.api.todolistsystem.dto.UserLoginResponseDto
import com.api.todolistsystem.entity.UserEntity
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

        val algorithm = Algorithm.HMAC256("secretpassword") // chave secreta para assinatura do token
        val token = JWT.create()
            .withIssuer("auth0") // emissor do token
            .withSubject(userLoginDto.email) // assunto do token
            .withClaim("name", userEntity.name)
            .withClaim("userId", userEntity.id)
            .sign(algorithm)

        val responseDto = LoginResponseDto(token)
        val responseJson = ObjectMapper().writeValueAsString(responseDto)

        return ResponseEntity.status(HttpStatus.OK).body(responseJson)
    }

    data class LoginResponseDto(val token: String)

}