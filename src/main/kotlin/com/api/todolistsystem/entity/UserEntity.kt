package com.api.todolistsystem.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "user")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true) val id: Long? = null,
    @Column(nullable = false, length = 12) val name: String = "",
    @Column(nullable = false, length = 16) val password: String = "",
    @Column(nullable = false, unique = true, length = 30) val email: String = "",
    @JsonIgnore @OneToMany(mappedBy = "userEntity", cascade = [CascadeType.ALL], orphanRemoval = true)
    val todolist: MutableList<ToDoListEntity> = mutableListOf()
)
