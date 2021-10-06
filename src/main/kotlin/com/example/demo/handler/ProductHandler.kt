package com.example.demo.handler

import com.example.demo.exception.ProductNotFoundException
import com.example.demo.mapper.ProductDTOMapper
import com.example.demo.message.ProductDto
import com.example.demo.message.ProductError
import com.example.demo.repository.ProductRepository
import kotlinx.coroutines.FlowPreview
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.*
import java.net.URI

@Component
class ProductHandler(
    private val productRepository: ProductRepository,
    private val productMapper: ProductDTOMapper
) {

    @FlowPreview
    suspend fun findAll(request: ServerRequest): ServerResponse =
        ok().json().bodyAndAwait(productRepository.findAll(Sort.by("name")))

    suspend fun findOne(request: ServerRequest): ServerResponse =
        try {
            val id = request.pathVariable("id").toLong()
            val found = productRepository.findById(id) ?: throw ProductNotFoundException(id)
            ok().bodyValueAndAwait(productMapper.toDTO(found))
        } catch (e: Exception) {
            badRequest().bodyValueAndAwait(ProductError(description = e.message!!))
        }

    suspend fun createProduct(request: ServerRequest): ServerResponse {
        val body = request.awaitBody<ProductDto>()
        var product = productRepository.save(productMapper.toModel(body))
        return created(URI.create("/product/${product.id}")).buildAndAwait()
    }

    suspend fun updateProduct(request: ServerRequest): ServerResponse {
        val body = request.awaitBody<ProductDto>()
        var product = productRepository.save(productMapper.toModel(body))
        return created(URI.create("/product/${product.id}")).buildAndAwait()
    }

}