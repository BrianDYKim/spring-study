package team.brian.async.service

import jakarta.transaction.Transactional
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import team.brian.async.jpa.entity.ProductJpaEntity
import team.brian.async.jpa.repository.ProductJpaRepository

/**
 * @author Doyeop Kim
 * @since 2024. 12. 8.
 */
@Service
class ProductService(
    private val productJpaRepository: ProductJpaRepository
) {
    @Transactional
    fun asyncTest(): ProductJpaEntity? {
        val product = ProductJpaEntity.of("productTest")
        val savedProduct = productJpaRepository.save(product)

        val firstCount = savedProduct.count

        val beforeThreadContext = Thread.currentThread().name

        val asyncResult1 = runBlocking {
            async { performAsync() }.await()
        }

        println(asyncResult1)

        val foundProduct = productJpaRepository.findByIdOrNull(savedProduct.id!!)
        val repairedProduct = foundProduct?.apply { count += 1 }

        val secondCount = repairedProduct?.count ?: 0

        val middleThreadContext = Thread.currentThread().name

        // 비동기는 이렇게 처리하는게 옳은듯함
        val asyncResult2 = runBlocking {
            IntRange(1, 10)
                .map { async { performAsync() } }
                .awaitAll()
        }

        asyncResult2.forEach { println(it) }

        val foundProduct2 = productJpaRepository.findByIdOrNull(savedProduct.id!!)
        val repairedProduct2 = foundProduct2?.apply { count += 1 }

        val thirdCount = repairedProduct2?.count ?: 0

        val lastThreadContext = Thread.currentThread().name

        return foundProduct2
    }

    private suspend fun performAsync(): String {
        delay(5000L)
        return "done!"
    }
}