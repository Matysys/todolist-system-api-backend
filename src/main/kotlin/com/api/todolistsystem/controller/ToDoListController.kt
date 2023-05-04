package com.api.todolistsystem.controller

import com.api.todolistsystem.dto.ToDoDto
import com.api.todolistsystem.dto.ToDoListDetailsDto
import com.api.todolistsystem.dto.UserDto
import com.api.todolistsystem.entity.ToDoListEntity
import com.api.todolistsystem.entity.UserEntity
import com.api.todolistsystem.service.impl.ToDoListService
import com.api.todolistsystem.service.impl.UserService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(origins = ["*"], maxAge = 3600)
@RequestMapping("/api/todolist")
class ToDoListController {

    //Injetando as dependências do ToDoListService quando necessário
    @Autowired lateinit var toDoListService: ToDoListService

    @PostMapping
    fun saveToDoList(@RequestBody @Valid todoListDto: ToDoDto): ResponseEntity<ToDoListEntity>{
        var toDoListDto: ToDoListEntity = this.toDoListService.save(todoListDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body(toDoListDto)
    }

    @GetMapping("/{userId}")
    fun getAllToDoListByUserId(@PathVariable(value = "userId") @Valid userId: Long): ResponseEntity<List<ToDoListEntity>>{
        val toDoListEntity: List<ToDoListEntity> = this.toDoListService.findAllByUser(userId)
        return ResponseEntity.status(HttpStatus.OK).body(toDoListEntity)
    }

    @GetMapping("/details/{userId}")
        fun getAllToDoListDetailsByUserId(@PathVariable(value = "userId") @Valid userId: Long)
        :ResponseEntity<ToDoListDetailsDto>{
        val toDoListDetails: ToDoListDetailsDto = toDoListService.findDetailsByUser(userId)
        val dto = ToDoListDetailsDto(
            toDoListDetails.totalTasks,
            toDoListDetails.totalBaixa,
            toDoListDetails.totalMedia,
            toDoListDetails.totalAlta,
            toDoListDetails.totalOutOfLimit
        )
        return ResponseEntity.status(HttpStatus.OK).body(dto)
        }



}