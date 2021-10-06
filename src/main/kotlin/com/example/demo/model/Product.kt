package com.example.demo.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.io.Serializable
import java.math.BigDecimal

@Table("PRODUCT")
data class Product(
    @Id
    val id: Long,
    val name: String = "",
    var price: BigDecimal = BigDecimal.valueOf(0.00)
): Serializable