package com.example.demo.config

import com.example.demo.handler.ProductHandler
import kotlinx.coroutines.FlowPreview
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class RouterConfiguration {

    @FlowPreview
    @Bean
    fun routers(productHandler: ProductHandler) = coRouter {
        (accept(APPLICATION_JSON) and ("/v1/product")).nest {
            GET("", productHandler::findAll)
            GET("/{id}", productHandler::findOne)
            POST("", productHandler::createProduct)
            PUT("", productHandler::updateProduct)
            DELETE("/{id}", productHandler::deleteProduct)
            DELETE("", productHandler::deleteProducts)
        }
    }

}