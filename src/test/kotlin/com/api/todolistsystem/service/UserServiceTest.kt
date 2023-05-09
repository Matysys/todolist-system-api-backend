package com.api.todolistsystem.service

import com.api.todolistsystem.entity.UserEntity
import com.api.todolistsystem.repository.UserRepository
import com.api.todolistsystem.service.impl.UserService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class UserServiceTest {

    @MockK
    lateinit var userRepository: UserRepository
    @InjectMockKs
    lateinit var userService: UserService

    @Test
    fun `should create a user`(){
        //given
        val fakeUser: UserEntity = buildUser();
        every { userService.save(fakeUser) } returns fakeUser

        //when
        val actual : UserEntity = userService.save(fakeUser)

        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isEqualTo(fakeUser)

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