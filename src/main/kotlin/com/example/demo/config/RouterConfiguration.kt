package com.example.demo.config

import com.example.demo.handler.ProductHandler
import kotlinx.coroutines.FlowPreview
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class RouterConfiguration {

    @FlowPreview
    @Bean
    fun routers(productHandler: ProductHandler) = coRouter {
        ("/v1/product").nest {
            GET("", productHandler::findAll)
            GET("/{id}", productHandler::findOne)
            //POST("", productHandler::create)
        }
    }

}