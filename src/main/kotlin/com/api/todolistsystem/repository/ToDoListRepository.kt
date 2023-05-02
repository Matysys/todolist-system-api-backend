package com.api.todolistsystem.repository

import com.api.todolistsystem.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ToDoListRepository : JpaRepository<User, Long> {


}