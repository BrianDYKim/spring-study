package team.brian.async.coroutine

import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

/**
 * @author Doyeop Kim
 * @since 2024. 11. 24.
 */
@ExtendWith(MockKExtension::class)
class CoroutineFlowTests {
    // Flow의 경우 collect 이전까지 실행되지 않는다
    @Test
    fun `flow는 차갑다`(): Unit = runBlocking {
        println("Calling simple function...")
        val flow = generateSimpleFlow()
        println("Calling collect...")
        flow.collect { value -> println(value) }
        println("Calling collect again...")
        flow.collect { value -> println(value) }
    }

    private fun generateSimpleFlow(): Flow<Int> = flow {
        IntRange(1, 3)
            .forEach {
                delay(100)
                emit(it)
            }
    }

    @Test
    fun `intermediate flow`(): Unit = runBlocking {
        (1..3).asFlow() // a flow of requests
            .map { request -> performRequest(request) }
            .collect { response -> println(response) }
    }

    private suspend fun performRequest(request: Int): String {
        delay(1000) // imitate long-running asynchronous work
        return "response $request"
    }
}