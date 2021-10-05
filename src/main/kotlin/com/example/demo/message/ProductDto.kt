package com.example.demo.message

import java.math.BigDecimal

data class ProductResult (
    val productId: Long? = null,
    val productName: String = "",
    var productPrice: BigDecimal = BigDecimal.valueOf(0.00)
)

data class ProductError (
    val code: String = "ERR-9999",
    val description: String = "",
)