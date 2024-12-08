package team.brian.async.jpa.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import team.brian.async.jpa.repository.ProductJpaRepository

/**
 * @author Doyeop Kim
 * @since 2024. 12. 8.
 */
@Entity
@Table(name = "productS")
open class ProductJpaEntity protected constructor() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open val id: Long? = null

    @Column(name = "name", nullable = false)
    open var name: String = ""

    @Column(name = "count", nullable = false)
    open var count: Int = 0

    companion object {
        fun of(name: String): ProductJpaEntity = ProductJpaEntity()
            .apply {
                this.name = name
            }
    }
}
