package com.api.todolistsystem.repository

import com.api.todolistsystem.entity.ToDoListEntity
import com.api.todolistsystem.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ToDoListRepository : JpaRepository<ToDoListEntity, Long> {

    @Query(value = "SELECT * FROM todolist WHERE user_id = ?1", nativeQuery = true)
    fun findAllByUserId(userId: Long): List<ToDoListEntity>

}