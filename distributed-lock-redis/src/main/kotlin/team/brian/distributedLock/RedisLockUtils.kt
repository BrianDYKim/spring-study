package team.brian.distributedLock

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

/**
 * @author Doyeop Kim
 * @since 2024. 12. 22.
 */
@Component
class RedisLockUtils(
    private val _lockManager: LockManager
) {
    init {
        manager = _lockManager
    }

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)

        private lateinit var manager: LockManager

        private fun acquireLock(key: String, ttl: Int, unit: TimeUnit): Boolean {
            return try {
                manager.lock(key, ttl, unit)
            } catch (e: Exception) {
                logger.error("[RedisLock] failed to acquire lock. key: $key", e)
                false
            }
        }

        private fun <T> proceedWithLock(key: String, function: () -> T): T {
            return try {
                function.invoke()
            } catch (e: Exception) {
                throw e
            } finally {
                releaseLock(key)
            }
        }

        private fun releaseLock(key: String): Boolean {
            return try {
                manager.unlock(key)
            } catch (e: Exception) {
                logger.error("[RedisLock] failed to release lock. key: $key", e)
                false
            }
        }
    }
}