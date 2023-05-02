package com.api.todolistsystem.service.impl

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


}