package com.example.demo.mapper

import com.example.demo.message.ProductDto
import com.example.demo.model.Product
import org.springframework.stereotype.Component

@Component
class ProductDTOMapper: DTOMapper<ProductDto, Product> {

    override fun toDTO(model: Product): ProductDto {
        return ProductDto(
            productId = model.id,
            productName = model.name,
            productPrice = model.price,
        )
    }

    override fun toModel(dto: ProductDto): Product {
        return Product(
            id = dto.productId ?: 0,
            name = dto.productName,
            price = dto.productPrice,
        )
    }

}