package com.example.demo.message

import java.io.Serializable
import java.math.BigDecimal

data class ProductDto (
    val productId: Long? = null,
    val productName: String = "",
    var productPrice: BigDecimal = BigDecimal.valueOf(0.00)
): Serializable

data class ProductError (
    val code: String = "ERR-9999",
    val description: String = "",
): Serializable