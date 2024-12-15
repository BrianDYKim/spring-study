package team.me.kotlinTx.external.jpa.mainDataSource.user.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@Entity
@Table(name = "users")
open class UserJpaEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open val id: Long? = null

    @Column(name = "name", nullable = false)
    open var name: String = ""

    @Column(name = "email", nullable = false)
    open var email: String = ""

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    open var createdAt: LocalDateTime = LocalDateTime.now()

    @LastModifiedDate
    @Column(name = "updated_at", nullable = true)
    open var updatedAt: LocalDateTime? = null

    @Column(name = "deleted_at", nullable = true)
    open var deletedAt: LocalDateTime? = null

    companion object {
        fun of(name: String, email: String): UserJpaEntity {
            return UserJpaEntity().apply {
                this.name = name
                this.email = email
            }
        }
    }
}
