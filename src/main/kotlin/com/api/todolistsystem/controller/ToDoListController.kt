package com.api.todolistsystem.controller

import com.api.todolistsystem.service.ToDoListService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class ToDoListController {

    @Autowired lateinit var toDoListService: ToDoListService

}