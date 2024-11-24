package team.brian.async.coroutine

import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDateTime

/**
 * @author Doyeop Kim
 * @since 2024. 11. 24.
 */
@ExtendWith(MockKExtension::class)
class CoroutineBasicTests {
    @Test
    fun `delay로 인해 Hello 1초 뒤에 world가 나와야함`(): Unit = runBlocking {
        launch {
            println("Current time: ${LocalDateTime.now()}")
            println("Hello, ")
            delay(1000)
            println("world!")
            println("Current time: ${LocalDateTime.now()}")
        }
    }

    // Scope Builder and Concurrency
    // doWorld 라는 suspend function 실행 이후에 done이 실행된다
    @Test
    fun `doworld 이내의 모든 Job들이 병렬로 모두 실행된 이후에 done이 출력된다`(): Unit = runBlocking {
        doWorld()
        println("done!")
    }

    // suspend function은 리턴이 될 때 까지 본문의 흐름을 정지시킴
    private suspend fun doWorld() = coroutineScope {
        launch {
            delay(2000L)
            println("World 2")
        }

        launch {
            delay(1000L)
            println("World 1")
        }

        println("Hello")
    }

    // 5초를 대기하였지만 병렬로 처리하여 5.248초 소요
    @Test
    fun `50000개의 작업을 병렬로 실행해도 coroutine은 경량으로 빠르게 작업들을 처리한다`(): Unit = runBlocking {
        repeat(50_500) {
            launch {
                delay(5000L)
                print(".")
            }
        }
    }
}