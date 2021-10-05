package com.example.demo.mapper

import com.example.demo.model.Product
import io.r2dbc.spi.Row
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.util.function.BiFunction

@Component
class ProductRepositoryMapper : BiFunction<Row, Any, Product> {

    override fun apply(row: Row, o: Any): Product {
        return Product(
            row.get("id", Number::class.java)?.toLong() ?: 0,
            row.get("name", String::class.java) ?: "",
            row.get("price", BigDecimal::class.java) ?: BigDecimal.valueOf(0.00),
        )
    }

}