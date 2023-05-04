package com.api.todolistsystem.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Entity
@Table(name = "user")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true) val id: Long? = null,
    @Column(nullable = false, length = 12) val name: String = "",
    @Column(nullable = false, length = 60) var password: String = "",

    @Transient
    var plainPassword: String? = "", // Senha sem estar criptografada

    @Column(nullable = false, unique = true, length = 30) val email: String = "",
    @JsonIgnore @OneToMany(mappedBy = "userEntity", cascade = [CascadeType.ALL], orphanRemoval = true)
    var todolist: MutableList<ToDoListEntity>? = mutableListOf()
){
    //Método para criptografar a senha
    fun encryptPassword() {
        this.password = BCryptPasswordEncoder().encode(this.plainPassword)
    }
}
