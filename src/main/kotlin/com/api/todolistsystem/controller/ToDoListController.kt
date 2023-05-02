package com.api.todolistsystem.controller

import com.api.todolistsystem.dto.UserDto
import com.api.todolistsystem.entity.UserEntity
import com.api.todolistsystem.service.impl.ToDoListService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(origins = ["*"], maxAge = 3600)
@RequestMapping("/api/user")
class ToDoListController {

    //Injetando as dependências do ToDoListService quando necessário
    @Autowired lateinit var toDoListService: ToDoListService

    /*@PostMapping
    fun saveUser(@RequestBody @Valid userDto: UserDto): ResponseEntity<Object>{
        var savedUserEntity: UserEntity = this.toDoListService.save()


    }

     */

}