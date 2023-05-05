package com.api.todolistsystem.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.DecodedJWT
import java.util.*

class Jwt {
    companion object {
        // Chave secreta para assinar e verificar o token
        private const val SECRET = "minha_chave_secreta"

        @JvmStatic
        fun criarToken(email: String, userId: Long, userName: String): String {
            val algorithm = Algorithm.HMAC256(SECRET)

            val dataExpiracao = Date(System.currentTimeMillis() + 3600_000) // Token expira em 1 hora

            return JWT.create()
                .withIssuer("auth0")
                .withSubject(email)
                .withClaim("userId", userId)
                .withClaim("name", userName)
                .withExpiresAt(dataExpiracao)
                .sign(algorithm)
        }

        @JvmStatic
        fun validarToken(token: String): DecodedJWT? {
            return try {
                val algorithm: Algorithm = Algorithm.HMAC256(SECRET)

                val verifier: JWTVerifier = JWT.require(algorithm).withIssuer("auth0").build()

                verifier.verify(token)
            } catch (e: JWTVerificationException) {
                null
            }
        }
    }
}


