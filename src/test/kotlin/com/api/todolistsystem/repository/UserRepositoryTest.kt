package com.api.todolistsystem.repository

import com.api.todolistsystem.entity.UserEntity
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired lateinit var userRepository: UserRepository
    @Autowired lateinit var testEntityManager: TestEntityManager

    private lateinit var user: UserEntity

    @BeforeEach
    fun setup () {
        user = testEntityManager.merge(buildUser())
    }

    @Test
    fun `should find user by email`(){
        //given
        val email: String = "mateus@hotmail.com"

        //when
        val foundUser: UserEntity? = userRepository.findByEmail(email)

        //then
        Assertions.assertThat(foundUser).isNotNull
        Assertions.assertThat(foundUser?.email).isEqualTo(email)
        Assertions.assertThat(foundUser?.name).isEqualTo("Mateus")
    }

    private fun buildUser(
        name: String = "Mateus",
        email: String = "mateus@hotmail.com",
        plainPassword: String = "mateus123",
        id: Long = 1L
    ) = UserEntity(
        name = name,
        email = email,
        plainPassword = plainPassword,
        todolist = mutableListOf(),
        id = id
    )

}