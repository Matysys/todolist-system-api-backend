package com.api.todolistsystem.repository

import com.api.todolistsystem.dto.ToDoDto
import com.api.todolistsystem.dto.ToDoListDetailsDto
import com.api.todolistsystem.entity.ToDoListEntity
import com.api.todolistsystem.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ToDoListRepository : JpaRepository<ToDoListEntity, Long> {

    @Query(value = "SELECT * FROM todolist WHERE user_id = :id", nativeQuery = true)
    fun findAllByUserId(@Param("id") userId: Long): List<ToDoListEntity>

    @Query(value = "SELECT " +
            "COUNT(*) as totaltasks," +
            "SUM(priority = 'BAIXA') as totalbaixa, " +
            "SUM(priority = 'MÉDIA') as totalmedia, " +
            "SUM(priority = 'ALTA') as totalalta, " +
            "SUM(registration_date > final_date) as totaloutoflimit " +
            "FROM todolist WHERE user_id = :id", nativeQuery = true)
    fun findDetailsByUser(@Param("id") userId: Long): Map<String, Long>

}