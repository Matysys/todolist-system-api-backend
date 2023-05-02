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
    @Size(max = 10)
    val password: String = "",
    @NotBlank
    @Size(max = 30)
    @Email
    val email: String = ""
){
    fun toEntity(): UserEntity = UserEntity(
        name = this.name,
        password = this.password,
        email = this.email
    )
}
