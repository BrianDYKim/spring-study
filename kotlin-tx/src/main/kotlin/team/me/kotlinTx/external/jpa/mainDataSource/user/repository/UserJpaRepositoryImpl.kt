package team.me.kotlinTx.external.jpa.mainDataSource.user.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
class UserJpaRepositoryImpl(
    @Qualifier("mainJpaQueryFactory")
    private val queryFactory: JPAQueryFactory
): UserJpaCustomRepository {

}