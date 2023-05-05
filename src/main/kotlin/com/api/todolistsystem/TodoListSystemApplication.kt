package com.api.todolistsystem

import com.api.todolistsystem.jwt.Jwt
import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.DecodedJWT
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TodoListSystemApplication

fun main(args: Array<String>) {
	runApplication<TodoListSystemApplication>(*args)

}
