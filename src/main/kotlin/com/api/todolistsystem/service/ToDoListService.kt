package com.api.todolistsystem.service

import com.api.todolistsystem.repository.ToDoListRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ToDoListService {

    //Anotação para injetar as dependências quando necessário aqui no ToDoListService
    @Autowired lateinit var toDoListRepository: ToDoListRepository
}