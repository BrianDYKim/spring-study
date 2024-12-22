package team.me.kotlinTx.application.listener

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener
import team.me.kotlinTx.application.event.UserNameUpdated

/**
 * @author Doyeop Kim
 * @since 2024. 12. 22.
 */
@Component
class UserEventListener {
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    fun handleUserNameUpdatedEvent(event: UserNameUpdated) {
        println("handleUserNameUpdatedEvent called! (afterName: ${event.afterUserName})")

        throw RuntimeException("userName fail!")
    }
}