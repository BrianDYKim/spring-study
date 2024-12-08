package team.brian.async.jpa.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import team.brian.async.jpa.entity.ProductJpaEntity

/**
 * @author Doyeop Kim
 * @since 2024. 12. 8.
 */
@Repository
interface ProductJpaRepository: JpaRepository<ProductJpaEntity, Long> {
}