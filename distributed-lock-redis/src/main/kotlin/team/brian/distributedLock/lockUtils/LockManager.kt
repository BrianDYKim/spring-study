package team.brian.distributedLock.lockUtils

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.time.Duration
import java.util.concurrent.TimeUnit

/**
 * @author Doyeop Kim
 * @since 2024. 12. 22.
 */
@Component
class LockManager(
    private val redisTemplate: RedisTemplate<String, String>
) {
    /**
     * Acquires a distributed lock with a specified TTL.
     *
     * @param key The lock key.
     * @param ttl The time-to-live (TTL) for the lock.
     * @param unit The time unit for the TTL (default: SECONDS).
     * @return True if the lock was successfully acquired, false otherwise.
     */
    fun lock(key: String, ttl: Int, unit: TimeUnit = TimeUnit.SECONDS): Boolean {
        val duration = Duration.ofMillis(unit.toMillis(ttl.toLong()))
        return redisTemplate.opsForValue().setIfAbsent(key, "lock", duration) ?: false
    }

    /**
     * Releases a distributed lock.
     *
     * @param key The lock key to release.
     * @return True if the lock was successfully released or did not exist, false otherwise.
     *         - Returns `true` if the key is successfully deleted or does not exist.
     *         - Returns `false` if the key could not be deleted due to an error.
     *
     * Note: This method does not verify the owner of the lock. If strict ownership checks
     *       are required, additional logic should be implemented to ensure only the
     *       creator of the lock can release it.
     */
    fun unlock(key: String): Boolean {
        return redisTemplate.delete(key)
    }
}