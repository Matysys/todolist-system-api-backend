package com.api.todolistsystem.controller

import com.api.todolistsystem.dto.UserDto
import com.api.todolistsystem.entity.UserEntity
import com.api.todolistsystem.service.impl.UserService
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
//@CrossOrigin(origins = ["*"], maxAge = 3600)
@RequestMapping("/api/user")
class UserController(private val userService: UserService) {

    @PostMapping
    fun saveUser(@RequestBody @Valid userDto: UserDto): ResponseEntity<UserEntity>{
        var userEntity: UserEntity = this.userService.save(userDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body(userEntity)
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<UserEntity>{
        val userEntity: UserEntity = this.userService.findById(id)
        return ResponseEntity.status(HttpStatus.OK).body(userEntity)
    }

}