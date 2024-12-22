package team.brian.distributedLock

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DistributedLockRedisApplication

fun main(args: Array<String>) {
    runApplication<DistributedLockRedisApplication>(*args)
}
