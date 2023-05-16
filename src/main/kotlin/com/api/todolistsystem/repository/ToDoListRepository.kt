package com.api.todolistsystem.repository


import com.api.todolistsystem.entity.ToDoListEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate


@Repository
interface ToDoListRepository : JpaRepository<ToDoListEntity, Long> {



    @Query(value = "SELECT * FROM todolist t WHERE t.user_id = :id", nativeQuery = true)
    fun findAllByUserId(@Param("id") userId: Long): List<ToDoListEntity>

    @Query("SELECT " +
            "COUNT(*) as totaltasks," +
            "SUM(priority = 'BAIXA') as totalbaixa, " +
            "SUM(priority = 'MÉDIA') as totalmedia, " +
            "SUM(priority = 'ALTA') as totalalta," +
            "SUM(finished = 'SIM') as totalfinished, " +
            "SUM(NOW() > final_date) as totaloutoflimit " +
            "FROM todolist t WHERE t.user_id = :userId", nativeQuery = true)
    fun findDetailsByUser(@Param("userId") userId: Long): Map<String, Long>

    @Modifying
    @Query("UPDATE todolist t SET t.name = :name, t.description = :description, " +
            "t.final_date = :finalDate, t.priority = :priority " +
            "WHERE t.user_id = :userId AND t.id = :taskId", nativeQuery = true)
    fun update(@Param("taskId") id: Long,
               @Param("name") name: String,
               @Param("description") description: String,
               @Param("finalDate") finalDate: LocalDate,
               @Param("priority") priority: String,
               @Param("userId") userId: Long): Int



    @Modifying
    @Query("UPDATE todolist SET finished = 'SIM', priority = 'FINALIZADA' WHERE id = :taskId", nativeQuery = true)
    fun finish(@Param("taskId") id: Long): Int

}