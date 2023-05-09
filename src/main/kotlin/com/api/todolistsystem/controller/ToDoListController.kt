package com.api.todolistsystem.controller

import com.api.todolistsystem.dto.ToDoDto
import com.api.todolistsystem.dto.ToDoListDetailsDto
import com.api.todolistsystem.dto.ToDoUpdateDto
import com.api.todolistsystem.entity.ToDoListEntity
import com.api.todolistsystem.exception.DateException
import com.api.todolistsystem.jwt.Jwt
import com.api.todolistsystem.service.impl.ToDoListService
import com.auth0.jwt.interfaces.DecodedJWT
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@CrossOrigin(origins = ["*"], maxAge = 3600)
@RequestMapping("/api/todolist")
class ToDoListController {

    //Injetando as dependências do ToDoListService quando necessário
    @Autowired lateinit var toDoListService: ToDoListService

    @PostMapping
    fun saveToDoList(@RequestBody @Valid todoListDto: ToDoDto, @RequestHeader("Authorization") token: String): ResponseEntity<String>{
        try {
            val checkToken: DecodedJWT? = Jwt.validarToken(token.replace("Bearer ", ""));
            if (checkToken != null) {
                this.toDoListService.save(todoListDto.toEntity())
                return ResponseEntity.status(HttpStatus.CREATED).body("Tarefa criada com sucesso!")
            }else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido.")
            }
        }catch(ex: DateException){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.message)
        }
    }

    @GetMapping("/{userId}")
    fun getAllToDoListByUserId(@PathVariable(value = "userId") @Valid userId: Long): ResponseEntity<List<ToDoListEntity>>{
        val toDoListEntity: List<ToDoListEntity> = this.toDoListService.findAllByUser(userId)
        if (toDoListEntity.isEmpty()) {
            return ResponseEntity.notFound().build()
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(toDoListEntity)
        }
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
            toDoListDetails.totalOutOfLimit,
            toDoListDetails.totalfinished
        )
        return ResponseEntity.status(HttpStatus.OK).body(dto)
        }

    @DeleteMapping("/delete/{taskId}")
    fun deleteToDoListById(@PathVariable(value = "taskId") @Valid taskId: Long, @RequestHeader("Authorization") token: String): ResponseEntity<String>{
        val checkToken: DecodedJWT? = Jwt.validarToken(token.replace("Bearer ", ""));
        if (checkToken != null) {
            this.toDoListService.delete(taskId)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Tarefa deletada com sucesso!")
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido.")
    }

    @PatchMapping("/update")
    fun updateToDoListById(@RequestBody @Valid toDoUpdateDto: ToDoUpdateDto, @RequestHeader("Authorization") token: String): ResponseEntity<String>{
        val checkToken: DecodedJWT? = Jwt.validarToken(token.replace("Bearer ", ""));
        if (checkToken != null) {
            this.toDoListService.update(toDoUpdateDto)
            return ResponseEntity.status(HttpStatus.OK).body("Tarefa alterada com sucesso!")
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido.")
    }

    @PatchMapping("/finish/{taskId}")
    fun finishToDoListById(@PathVariable @Valid taskId: Long, @RequestHeader("Authorization") token: String): ResponseEntity<String>{
        val checkToken: DecodedJWT? = Jwt.validarToken(token.replace("Bearer ", ""));
        if (checkToken != null) {
            this.toDoListService.finish(taskId)
            return ResponseEntity.status(HttpStatus.OK).body("Tarefa concluída com sucesso!")
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido.")
    }


}