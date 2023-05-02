package com.api.todolistsystem.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "todolist")
data class ToDo(
    @Id
    @GeneratedValue
    val id: Long,
    @Column(nullable = false, length = 20) val name: String,
    @Column(nullable = false, length = 50) val description: String,
    @Column(nullable = false) val registrationDate: LocalDate = LocalDate.now(),
    @Column(nullable = false) val finalDate: LocalDate,
    @Column(nullable = false) val priority: String,
    @ManyToOne @JoinColumn(name = "user_id")val user: User
)

