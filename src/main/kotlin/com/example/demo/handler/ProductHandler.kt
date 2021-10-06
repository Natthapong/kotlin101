package com.example.demo.handler

import com.example.demo.exception.ProductNotFoundException
import com.example.demo.message.ProductError
import com.example.demo.message.ProductResult
import com.example.demo.model.Product
import com.example.demo.repository.ProductRepository
import kotlinx.coroutines.FlowPreview
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.badRequest
import org.springframework.web.reactive.function.server.ServerResponse.ok

@Component
class ProductHandler(
    private val productRepository: ProductRepository
) {

    @FlowPreview
    suspend fun findAll(request: ServerRequest): ServerResponse {
        return ok().json().bodyAndAwait(productRepository.findProducts())
    }

    suspend fun findOne(request: ServerRequest): ServerResponse {
        return try {
            val id = request.pathVariable("id").toLong()
            val found = productRepository.getProduct(id) ?: throw ProductNotFoundException(id)
            ok().bodyValueAndAwait(buildResponse(found))
        } catch  (e: Exception) {
            badRequest().bodyValueAndAwait(ProductError(description = e.message!!))
        }
    }

    private fun buildResponse(product: Product): Any {
        return ProductResult(product.id, product.name, product.price)
    }

}