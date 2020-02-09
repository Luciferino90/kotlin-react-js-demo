package it.usuratonkachi.kotlin.kotlindemo.handler

import it.usuratonkachi.kotlin.kotlindemo.dto.Log
import it.usuratonkachi.kotlin.kotlindemo.mongo.loginRepository
import it.usuratonkachi.kotlin.kotlindemo.r2dbc.UserRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux


@RestController
class RestController(private var userRepository: UserRepository, private var loginRepository: loginRepository) {
    @GetMapping(path = ["/kotlin/kotlinrest"])
    fun log(): Flux<Log> {
        return userRepository.findAll()
                .filter { it.id != null }
                .map{ it.id!! }
                .flatMap { loginRepository.findAllByUserid(it!!) }
                .map { Log(it.userid, it.name, it.logindate) }
                .map { e -> e }
    }
}
