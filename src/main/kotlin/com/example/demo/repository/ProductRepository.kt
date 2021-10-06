package com.example.demo.repository

import com.example.demo.message.ProductDto
import com.example.demo.model.Product
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import org.springframework.data.r2dbc.convert.MappingR2dbcConverter
import org.springframework.data.repository.kotlin.CoroutineSortingRepository
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.r2dbc.core.flow
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : CoroutineSortingRepository<Product, Long>, CustomProductRepository

interface CustomProductRepository {
    @FlowPreview
    fun findByProductName(): Flow<Product>
    @FlowPreview
    fun findByProductPrice(): Flow<Product>
    @FlowPreview
    fun findProducts(): Flow<ProductDto>
}

class CustomProductRepositoryImpl (private val client: DatabaseClient,
                        private val mapper: ProductRepositoryMapper,
                        private val converter: MappingR2dbcConverter
): CustomProductRepository {

    @FlowPreview
    override fun findProducts(): Flow<ProductDto> =
        client.sql("SELECT * FROM PRODUCT ORDER BY NAME")
            .map(mapper::apply)
            .flow()
            .catch { e-> println(e.message) }

    @FlowPreview
    override fun findByProductName(): Flow<Product> {
        TODO("Not yet implemented")
    }

    @FlowPreview
    override fun findByProductPrice(): Flow<Product> {
        TODO("Not yet implemented")
    }

}

