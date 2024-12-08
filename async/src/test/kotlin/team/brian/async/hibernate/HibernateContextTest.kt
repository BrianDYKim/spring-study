package team.brian.async.hibernate

import jakarta.transaction.Transactional
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import team.brian.async.jpa.entity.ProductJpaEntity
import team.brian.async.jpa.repository.ProductJpaRepository

/**
 * @author Doyeop Kim
 * @since 2024. 12. 8.
 */
@SpringBootTest
class HibernateContextTest @Autowired constructor(
    private val productJpaRepository: ProductJpaRepository
) {
    @Test
    fun `코루틴 스코프 전후의 스레드 컨텍스트는 동일하다`() {
        val beforeThreadContext = Thread.currentThread().name
        println(beforeThreadContext)

        val asyncResult = runBlocking {
            performAsync()
        }

        val afterThreadContext = Thread.currentThread().name
        println(afterThreadContext)

        assertEquals(beforeThreadContext, afterThreadContext)
    }

    @Test
    @Transactional
    fun `코루틴 스코프 전후로 트랜잭션 컨텍스트는 유지된다`() {
        val product = ProductJpaEntity.of("productTest")
        val savedProduct = productJpaRepository.save(product)

        val firstCount = savedProduct.count

        val beforeThreadContext = Thread.currentThread().name
        println(beforeThreadContext)
        println(firstCount)

        val asyncResult1 = runBlocking {
            performAsync()
        }

        val foundProduct = productJpaRepository.findByIdOrNull(savedProduct.id!!)
        val repairedProduct = foundProduct?.apply { count += 1 }

        val secondCount = repairedProduct?.count ?: 0

        val middleThreadContext = Thread.currentThread().name
        println(middleThreadContext)
        println(secondCount)

        val asyncResult2 = runBlocking {
            performAsync()
        }

        val foundProduct2 = productJpaRepository.findByIdOrNull(savedProduct.id!!)
        val repairedProduct2 = foundProduct2?.apply { count += 1 }

        val thirdCount = repairedProduct2?.count ?: 0

        val lastThreadContext = Thread.currentThread().name
        println(lastThreadContext)
        println(thirdCount)

        assertEquals(firstCount, 0)
        assertEquals(secondCount, 1)
        assertEquals(thirdCount, 2)
        assertEquals(beforeThreadContext, middleThreadContext)
        assertEquals(middleThreadContext, lastThreadContext)
    }

    private fun performAsync(): Deferred<String> = CoroutineScope(Dispatchers.IO).async {
        println("before delay!")
        delay(3000L)
        println("after delay!")
        "done!"
    }
}