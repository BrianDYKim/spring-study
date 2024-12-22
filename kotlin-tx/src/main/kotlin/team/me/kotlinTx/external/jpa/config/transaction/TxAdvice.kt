package team.me.kotlinTx.external.jpa.config.transaction

import org.springframework.stereotype.Component
import team.me.kotlinTx.external.jpa.config.annotation.transactional.MainDataSourceTransactional

/**
 * @author Doyeop Kim
 * @since 2024. 12. 22.
 */
@Component
class TxAdvice {
    @MainDataSourceTransactional(readOnly = false)
    fun <T> mainWritable(function: () -> T): T {
        return execute(function)
    }

    @MainDataSourceTransactional(readOnly = true)
    fun <T> mainReadable(function: () -> T): T {
        return execute(function)
    }

    private fun <T> execute(function: () -> T): T {
        return function.invoke()
    }
}