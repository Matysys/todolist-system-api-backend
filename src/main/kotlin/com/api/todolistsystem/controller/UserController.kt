package com.api.todolistsystem.controller

import com.api.todolistsystem.dto.UserDto
import com.api.todolistsystem.entity.UserEntity
import com.api.todolistsystem.service.impl.ToDoListService
import com.api.todolistsystem.service.impl.UserService
import jakarta.validation.Valid
import org.apache.catalina.User
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(origins = ["*"], maxAge = 3600)
@RequestMapping("/api/user")
class UserController(private val userService: UserService) {

    @PostMapping
    fun saveUser(@RequestBody @Valid userDto: UserDto): ResponseEntity<UserEntity>{
        var userEntity: UserEntity = this.userService.save(userDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body(userEntity)
    }

}