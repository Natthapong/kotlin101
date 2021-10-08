package com.example.demo.handler

import com.example.demo.exception.BadRequestException
import com.example.demo.exception.ProductNotFoundException
import com.example.demo.mapper.ProductDTOMapper
import com.example.demo.message.ProductDto
import com.example.demo.message.ProductError
import com.example.demo.repository.ProductRepository
import kotlinx.coroutines.FlowPreview
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
        try {
            ok().json().bodyAndAwait(productRepository.findProducts())
        } catch (e: Throwable) {
            badRequest().bodyValueAndAwait(ProductError(description = e.message!!))
        }

    @FlowPreview
    suspend fun findByName(request: ServerRequest): ServerResponse =
        try {
            val name = request.pathVariable("name")
            ok().json().bodyAndAwait(productRepository.findByProductName(name))
        } catch (e: Throwable) {
            badRequest().bodyValueAndAwait(ProductError(description = e.message!!))
        }

    @FlowPreview
    suspend fun findByPrice(request: ServerRequest): ServerResponse =
        try {
            val price = request.pathVariable("price").toBigDecimal()
            ok().json().bodyAndAwait(productRepository.findByProductPrice(price))
        } catch (e: Throwable) {
            badRequest().bodyValueAndAwait(ProductError(description = e.message!!))
        }

    suspend fun findOne(request: ServerRequest): ServerResponse =
        try {
            val id = request.pathVariable("id").toLong()
            val found = productRepository.findById(id) ?: throw ProductNotFoundException(id)
            ok().bodyValueAndAwait(productMapper.toDTO(found))
        } catch (e: Throwable) {
            badRequest().bodyValueAndAwait(ProductError(description = e.message!!))
        }

    suspend fun createProduct(request: ServerRequest): ServerResponse =
        try {
            val body = request.awaitBody<ProductDto>()
            when (body.productId) {
                null -> {
                    val product = productRepository.save(productMapper.toModel(body))
                    created(URI.create("/product/${product.id}")).buildAndAwait()
                }
                else -> throw BadRequestException("ProductID should be null.")
            }
        } catch (e: Throwable) {
            badRequest().bodyValueAndAwait(ProductError(description = e.message!!))
        }

    suspend fun updateProduct(request: ServerRequest): ServerResponse =
        try {
            val body = request.awaitBody<ProductDto>()
            when (body.productId) {
                null -> throw BadRequestException("ProductID is null or empty.")
                else -> {
                    val product = productRepository.save(productMapper.toModel(body))
                    ok().bodyValueAndAwait(productMapper.toDTO(product))
                }
            }
        } catch (e: Throwable) {
            badRequest().bodyValueAndAwait(ProductError(description = e.message!!))
        }

    suspend fun deleteProduct(request: ServerRequest): ServerResponse =
        try {
            val id = request.pathVariable("id").toLong()
            ok().json().bodyValueAndAwait(productRepository.deleteById(id))
        } catch (e: Throwable) {
            badRequest().bodyValueAndAwait(ProductError(description = e.message!!))
        }

    suspend fun deleteProducts(request: ServerRequest): ServerResponse =
        try {
            ok().json().bodyValueAndAwait(productRepository.deleteAll())
        } catch (e: Throwable) {
            badRequest().bodyValueAndAwait(ProductError(description = e.message!!))
        }

}