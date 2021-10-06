package com.example.demo.repository

import com.example.demo.model.Product
import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.convert.MappingR2dbcConverter
import org.springframework.data.repository.kotlin.CoroutineSortingRepository
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : CoroutineSortingRepository<Product, Long>, CustomProductRepository

interface CustomProductRepository {
    suspend fun findByProductName(): Flow<Product>
    suspend fun findByProductPrice(): Flow<Product>
}

class CustomProductRepositoryImpl (private val client: DatabaseClient,
                        private val mapper: ProductRepositoryMapper,
                        private val converter: MappingR2dbcConverter
): CustomProductRepository {

    override suspend fun findByProductName(): Flow<Product> {
        TODO("Not yet implemented")
    }

    override suspend fun findByProductPrice(): Flow<Product> {
        TODO("Not yet implemented")
    }

}

