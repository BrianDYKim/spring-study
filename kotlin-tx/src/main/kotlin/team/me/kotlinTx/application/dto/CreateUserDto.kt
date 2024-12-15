package team.me.kotlinTx.application.dto

import team.me.kotlinTx.external.jpa.mainDataSource.user.entity.UserJpaEntity
import java.time.LocalDateTime

sealed class CreateUserDto {
    data class Request(
        val name: String,
        val email: String
    )

    data class Response(
        val id: Long,
        val name: String,
        val email: String,
        val createdAt: LocalDateTime
    ) {
        companion object {
            fun fromEntity(user: UserJpaEntity): Response {
                return Response(
                    id = user.id!!,
                    name = user.name,
                    email = user.email,
                    createdAt = user.createdAt
                )
            }
        }
    }
}
