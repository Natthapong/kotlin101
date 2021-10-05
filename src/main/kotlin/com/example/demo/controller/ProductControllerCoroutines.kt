package com.example.demo.controller

import com.example.demo.mapper.ProductRepositoryMapper
import com.example.demo.model.Product
import com.example.demo.repository.Product2Repository
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductControllerCoroutines {
    @Autowired
    lateinit var productRepository: Product2Repository

    @GetMapping("/{id}")
    suspend fun findOne(@PathVariable id: Long): Product? {
        return productRepository.findById(id).awaitSingle()
    }

}