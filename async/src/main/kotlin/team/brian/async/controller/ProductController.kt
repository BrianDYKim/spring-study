package team.brian.async.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.brian.async.jpa.entity.ProductJpaEntity
import team.brian.async.service.ProductService

/**
 * @author Doyeop Kim
 * @since 2024. 12. 8.
 */
@RestController
@RequestMapping("/api/products")
class ProductController(
    private val productService: ProductService
) {
    @PostMapping("/test")
    fun test(): ProductJpaEntity? {
        return productService.asyncTest()
    }
}
