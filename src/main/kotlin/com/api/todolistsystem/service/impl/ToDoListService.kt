package com.api.todolistsystem.service.impl

import com.api.todolistsystem.dto.ToDoDto
import com.api.todolistsystem.dto.ToDoListDetailsDto
import com.api.todolistsystem.dto.ToDoUpdateDto
import com.api.todolistsystem.entity.ToDoListEntity
import com.api.todolistsystem.entity.UserEntity
import com.api.todolistsystem.repository.ToDoListRepository
import com.api.todolistsystem.service.IToDoListService
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ToDoListService: IToDoListService {

    //Anotação para injetar as dependências quando necessário aqui
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

    override fun findDetailsByUser(id: Long): ToDoListDetailsDto {
        val result = toDoListRepository.findDetailsByUser(id)

        val dto = ToDoListDetailsDto(
            result["totaltasks"]!!,
            result["totalbaixa"]!!,
            result["totalmedia"]!!,
            result["totalalta"]!!,
            result["totaloutoflimit"]!!,
            result["totalfinished"]!!
        )

        return dto
    }

    override fun delete(id: Long) {
        this.toDoListRepository.deleteById(id)
    }

    @Transactional
    override fun update(toDoUpdateDto: ToDoUpdateDto): Int {
        return this.toDoListRepository.update(toDoUpdateDto.id, toDoUpdateDto.name, toDoUpdateDto.description,
            toDoUpdateDto.finalDate, toDoUpdateDto.priority, toDoUpdateDto.userId)
    }

    @Transactional
    override fun finish(id: Long): Int {
        return this.toDoListRepository.finish(id);
    }
}