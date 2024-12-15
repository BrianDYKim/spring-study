package team.me.kotlinTx.jpa.mainDataSource.user.repository

import org.springframework.data.jpa.repository.JpaRepository
import team.me.kotlinTx.jpa.mainDataSource.user.entity.UserJpaEntity

interface UserJpaRepository : JpaRepository<UserJpaEntity, Long>, UserJpaCustomRepository
