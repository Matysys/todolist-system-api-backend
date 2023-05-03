package com.api.todolistsystem.dto

import com.api.todolistsystem.entity.UserEntity
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class UserLoginDto(
    @NotBlank
    @Size(max = 10)
    val password: String = "",
    @NotBlank
    @Size(max = 30)
    @Email
    val email: String = ""
){
    fun toEntity(): UserEntity = UserEntity(
        password = this.password,
        email = this.email
    )
}
