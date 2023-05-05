package com.api.todolistsystem.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "todolist")
data class ToDoListEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(nullable = false, length = 20) val name: String,
    @Column(nullable = false, length = 100) val description: String,
    @Column(nullable = false) val registrationDate: LocalDate = LocalDate.now(),
    @Column(nullable = false) val finalDate: LocalDate,
    @Column(nullable = false) val priority: String,
    @Column(nullable = false) val finished: String = "NÃO",
    @ManyToOne @JoinColumn(name = "user_id")val userEntity: UserEntity
)

