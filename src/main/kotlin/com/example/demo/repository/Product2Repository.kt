package com.example.demo.repository

import com.example.demo.model.Product
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux


interface Product2Repository : ReactiveCrudRepository<Product?, Long?> {

    @Query("SELECT * FROM PRODUCT WHERE name like $1")
    fun findByNameContains(name: String?): Flux<Product?>?

}