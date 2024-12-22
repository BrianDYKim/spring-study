package team.me.kotlinTx.external.jpa.config.transaction

import org.springframework.stereotype.Component

/**
 * @author Doyeop Kim
 * @since 2024. 12. 22.
 */
@Component
class Tx(
    private val _txAdvice: TxAdvice
) {
    init {
        Tx.txAdvice = _txAdvice
    }

    companion object {
        private lateinit var txAdvice: TxAdvice

        fun <T> mainWritable(function: () -> T): T {
            return txAdvice.mainWritable(function)
        }

        fun <T> mainReadable(function: () -> T): T {
            return txAdvice.mainReadable(function)
        }
    }
}