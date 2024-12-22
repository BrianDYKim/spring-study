package team.me.kotlinTx.external.jpa.config.entity

import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.jpa.domain.support.AuditingEntityListener

/**
 * @author Doyeop Kim
 * @since 2024. 12. 22.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseJpaEntity {
}