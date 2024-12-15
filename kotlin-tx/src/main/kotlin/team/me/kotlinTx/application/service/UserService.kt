package team.me.kotlinTx.application.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import team.me.kotlinTx.application.dto.CreateUserDto
import team.me.kotlinTx.application.dto.FindUserDto
import team.me.kotlinTx.application.dto.UpdateUserNameDto
import team.me.kotlinTx.external.jpa.config.annotation.transactional.MainDataSourceTransactional
import team.me.kotlinTx.external.jpa.mainDataSource.user.entity.UserJpaEntity
import team.me.kotlinTx.external.jpa.mainDataSource.user.repository.UserJpaRepository

@Service
class UserService(
    private val userJpaRepository: UserJpaRepository
) {
    @MainDataSourceTransactional(readOnly = false)
    fun createUser(request: CreateUserDto.Request): CreateUserDto.Response {
        val (name, email) = request

        val userEntity = UserJpaEntity.of(name, email)

        val savedUserEntity = userJpaRepository.save(userEntity)

        return CreateUserDto.Response.fromEntity(savedUserEntity)
    }

    @MainDataSourceTransactional(readOnly = false)
    fun updateName(request: UpdateUserNameDto.Request): UpdateUserNameDto.Response? {
        val (id, name) = request

        val foundUser = userJpaRepository.findByIdOrNull(id);

        foundUser?.let {
            it.name = name
            val savedEntity = userJpaRepository.save(it)
            return UpdateUserNameDto.Response.fromEntity(savedEntity)
        } ?: return null
    }

    @MainDataSourceTransactional(readOnly = true)
    fun findById(request: FindUserDto.Request): FindUserDto.Response {
        val id = request.id

        val foundUser = userJpaRepository.findByIdOrNull(id) ?: throw IllegalArgumentException("User not found")

        return FindUserDto.Response.fromEntity(foundUser)
    }
}
