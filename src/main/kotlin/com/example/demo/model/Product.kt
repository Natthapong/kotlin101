package com.example.demo.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal

@Table("PRODUCT")
data class Product(
    @Id @Column("id") val id: Long? = null,
    @Column("name") val name: String = "",
    @Column("price") var price: BigDecimal = BigDecimal.valueOf(0.00)
)