package com.api.todolistsystem.dto

import com.api.todolistsystem.entity.UserEntity
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class UserDto(
    @NotBlank
    @Size(max = 12)
    val name: String = "",
    @NotBlank
    @Size(max = 16)
    val password: String = "",
    @NotBlank
    @Size(max = 30)
    @field:Email(message = "E-mail inválido!")
    val email: String = ""
){
    fun toEntity(): UserEntity = UserEntity(
        name = this.name,
        plainPassword = this.password,
        email = this.email
    )
}
