package com.api.todolistsystem.service

import com.api.todolistsystem.entity.UserEntity
import com.api.todolistsystem.repository.UserRepository
import com.api.todolistsystem.service.impl.UserService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import java.util.*

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class UserServiceTest {

    @MockK
    lateinit var userRepository: UserRepository
    @InjectMockKs
    lateinit var userService: UserService

    @Test
    fun `should create a user`(){
        // given
        val fakeUser: UserEntity = buildUser()
        every { userRepository.save(fakeUser) } returns fakeUser

        // when
        val createdUser: UserEntity = userService.save(fakeUser)

        // then
        verify(exactly = 1) { userRepository.save(fakeUser) }
        Assertions.assertThat(createdUser).isNotNull
        Assertions.assertThat(createdUser).isEqualTo(fakeUser)
    }

    @Test
    fun `should find user by id`(){
        //given
        val fakeId: Long = Random().nextLong()
        val fakeUser: UserEntity = buildUser(id = fakeId)
        every { userRepository.findById(fakeId) } returns Optional.of(fakeUser)

        //when
        val actual: Optional<UserEntity> = userService.findById(fakeId)

        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual.get()).isExactlyInstanceOf(UserEntity::class.java)
        Assertions.assertThat(actual.get()).isSameAs(fakeUser)
        verify(exactly = 1) { userRepository.findById(fakeId) }
    }

    @Test
    fun `should delete user by id`() {
        //given
        val fakeId: Long = Random().nextLong()
        val fakeUser: UserEntity = buildUser(id = fakeId)
        every { userRepository.findById(fakeId) } returns Optional.of(fakeUser)
        every { userRepository.deleteById(fakeId) } just runs

        //when
        userService.delete(fakeId)

        //then
        //verify(exactly = 1) { userRepository.findById(fakeId) }
        verify(exactly = 1) { userRepository.deleteById(fakeId) }
    }



    private fun buildUser(
        name: String = "Mateus",
        email: String = "mateus123@hotmail.com",
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