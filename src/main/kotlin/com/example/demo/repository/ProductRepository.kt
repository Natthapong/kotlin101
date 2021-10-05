package com.example.demo.repository

import com.example.demo.mapper.ProductRepositoryMapper
import com.example.demo.model.Product
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import org.springframework.data.r2dbc.convert.MappingR2dbcConverter
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.r2dbc.core.awaitOneOrNull
import org.springframework.r2dbc.core.flow
import org.springframework.stereotype.Repository

@Repository
class ProductRepository(private val client: DatabaseClient,
                        private val mapper: ProductRepositoryMapper,
                        private val converter: MappingR2dbcConverter
) {

    @FlowPreview
    fun findProducts(): Flow<Product> =
        client.sql("SELECT * FROM PRODUCT")
            .map { row, metadata -> converter.read(Product::class.java, row, metadata) }
            .flow()
            .catch { e-> println(e.message) }

    suspend fun getProduct(id: Long): Product? =
        client.sql("SELECT * FROM PRODUCT WHERE ID = :productId")
            .bind("productId", id)
            .map(mapper::apply)
            .awaitOneOrNull()

}