package team.me.kotlinTx.external.jpa.config.querydsl

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class QueryDslMainDataSourceConfiguration(
    @PersistenceContext(unitName = "main")
    @Qualifier("mainEntityManagerFactory")
    private val entityManager: EntityManager
) {
    @Primary
    @Bean("mainJpaQueryFactory")
    fun jpaQueryFactory(): JPAQueryFactory {
        return JPAQueryFactory(entityManager)
    }
}
