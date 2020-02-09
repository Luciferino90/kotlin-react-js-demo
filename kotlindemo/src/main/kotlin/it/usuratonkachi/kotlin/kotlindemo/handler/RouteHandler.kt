package it.usuratonkachi.kotlin.kotlindemo.handler

import it.usuratonkachi.kotlin.kotlindemo.service.HandlerService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RequestPredicates
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse


@Configuration
class RoutingConfig(private var handlerService: HandlerService) {

    @Bean
    fun createRouterFunction(): RouterFunction<ServerResponse?>? {
        val routes = RouterFunctions.route()
                .GET("/kotlin/{username}", handlerService::login)
                .GET("/kotlin", handlerService::log)
                .build()
        return RouterFunctions.nest(RequestPredicates.path("/kotlin"), routes)
    }

}
