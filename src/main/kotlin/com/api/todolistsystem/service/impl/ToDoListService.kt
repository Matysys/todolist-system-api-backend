package com.api.todolistsystem.service.impl

import com.api.todolistsystem.entity.ToDoListEntity
import com.api.todolistsystem.entity.UserEntity
import com.api.todolistsystem.repository.ToDoListRepository
import com.api.todolistsystem.service.IToDoListService
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ToDoListService: IToDoListService {

    //Anotação para injetar as dependências quando necessário aqui no ToDoListService
    @Autowired lateinit var toDoListRepository: ToDoListRepository

    override fun save(toDoListEntity: ToDoListEntity): ToDoListEntity {
        return toDoListRepository.save(toDoListEntity)
    }

    override fun findById(id: Long): ToDoListEntity {
        return this.toDoListRepository.findById(id).orElseThrow { IllegalArgumentException("User not found") }
    }

    override fun findAllByUser(id: Long): List<ToDoListEntity> {
        return this.toDoListRepository.findAllByUserId(id)
    }

    override fun delete(id: Long) {
        val toDoListEntity: ToDoListEntity = this.findById(id)
        this.toDoListRepository.delete(toDoListEntity)
    }
}