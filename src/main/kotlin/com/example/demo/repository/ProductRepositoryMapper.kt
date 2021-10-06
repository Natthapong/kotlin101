package com.example.demo.repository

import com.example.demo.message.ProductDto
import io.r2dbc.spi.Row
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.util.function.BiFunction

@Component
class ProductRepositoryMapper : BiFunction<Row, Any, ProductDto> {

    override fun apply(row: Row, o: Any): ProductDto {
        return ProductDto(
            row.get("id", Number::class.java)?.toLong() ?: 0,
            row.get("name", String::class.java) ?: "",
            row.get("price", BigDecimal::class.java) ?: BigDecimal.valueOf(0.00),
        )
    }

}