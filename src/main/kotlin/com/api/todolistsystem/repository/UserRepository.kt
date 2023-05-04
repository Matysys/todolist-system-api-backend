package com.api.todolistsystem.repository

import com.api.todolistsystem.entity.ToDoListEntity
import com.api.todolistsystem.entity.UserEntity
import org.apache.catalina.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT * FROM user WHERE email = :email", nativeQuery = true)
    fun findByEmail(@Param("email") email: String): UserEntity?

}