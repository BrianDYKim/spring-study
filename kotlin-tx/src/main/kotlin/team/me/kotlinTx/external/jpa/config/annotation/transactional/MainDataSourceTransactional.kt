package team.me.kotlinTx.external.jpa.config.annotation.transactional

import org.springframework.core.annotation.AliasFor
import org.springframework.transaction.annotation.Transactional

/**
 * MainDataSourceTransactional
 * 메인 DB에 대한 트랜잭션을 선언할 때 사용하는 어노테이션입니다.
 * @param readOnly 읽기 전용 여부. 기본값은 false 이며, true로 설정 시 read endpoint로 트랜잭션을 전송합니다.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Transactional(transactionManager = "mainTransactionManager")
annotation class MainDataSourceTransactional(
    @get:AliasFor(annotation = Transactional::class, attribute = "readOnly")
    val readOnly: Boolean = false,
)
