package com.example.demo.exception

import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.server.ServerWebExchange

class ProductNotFoundException(id: Long) : RuntimeException("Product:$id is not found...")

@RestControllerAdvice
class RestWebExceptionHandler {

    @ExceptionHandler(ProductNotFoundException::class)
    suspend fun handle(ex: ProductNotFoundException, exchange: ServerWebExchange) {
        exchange.response.statusCode = HttpStatus.NOT_FOUND
        exchange.response.setComplete().awaitFirstOrNull()
    }

}