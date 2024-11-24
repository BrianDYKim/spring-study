package team.brian.async.coroutine

import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

/**
 * @author Doyeop Kim
 * @since 2024. 11. 24.
 */
@ExtendWith(MockKExtension::class)
class CoroutineChannelTests {
    @Test
    fun `rendezvousChannel 테스트`(): Unit = runBlocking {
        val channel = Channel<String>()

        launch {
            channel.send("A1")
            channel.send("A2")
            log("A done")
        }

        launch {
            channel.send("B1")
            log("B done")
        }

        launch {
            repeat(3) {
                val x = channel.receive()
                log(x)
            }
        }
    }

    private fun log(message: String) {
        println("[${Thread.currentThread().name}] $message")
    }
}