package com.api.todolistsystem.entity

import jakarta.persistence.*

@Entity
data class User(
    @Id
    @GeneratedValue val id: Long,
    @Column(nullable = false, length = 12) val name: String = "",
    @Column(nullable = false, length = 10) val password: String = "",
    @Column(nullable = false, unique = true) val email: String = "",
    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val todolist: MutableList<ToDo> = mutableListOf()
)
