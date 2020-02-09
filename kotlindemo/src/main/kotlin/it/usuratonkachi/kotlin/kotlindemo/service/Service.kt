package it.usuratonkachi.kotlin.kotlindemo.service

import it.usuratonkachi.kotlin.kotlindemo.dto.Log
import it.usuratonkachi.kotlin.kotlindemo.mongo.Login
import it.usuratonkachi.kotlin.kotlindemo.mongo.loginRepository
import it.usuratonkachi.kotlin.kotlindemo.r2dbc.User
import it.usuratonkachi.kotlin.kotlindemo.r2dbc.UserRepository
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.util.function.Tuples

@Service
class HandlerService(private var userRepository: UserRepository, private var loginRepository: loginRepository) {

    fun login(request: ServerRequest): Mono<ServerResponse> =
            Mono.just(request.pathVariable("username"))
                    .flatMap { username -> userRepository.findAll()
                            .collectList()
                            .flatMapMany{ Flux.fromIterable(it) }
                            .filter { it.username.contentEquals(username) }
                            .collectList()
                            .filter { it.isNotEmpty() }
                            .map { Tuples.of(it.first(), true) }
                            .switchIfEmpty(Mono.defer { userRepository.save(User(username)).zipWith(Mono.just(false)) })
                    }
                    .flatMap { userTAlreadyExists -> loginRepository.save(Login(userid = userTAlreadyExists.t1.id!!, name = userTAlreadyExists.t1.username)).zipWith(Mono.just(userTAlreadyExists.t2)) }

                    .map { loggedTAlreadyExists -> if (loggedTAlreadyExists.t2) String.format("Welcome back, %s!", loggedTAlreadyExists.t1.toString()) else String.format("Welcome for the first time, %s!", loggedTAlreadyExists.t1.toString()) }
                    .flatMap { res -> ServerResponse.ok().body(BodyInserters.fromValue(res)) }

    fun log(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok()
                .body(BodyInserters.fromPublisher(
                        userRepository.findAll()
                                .filter { it.id != null }
                                .map{ it.id!! }
                                .flatMap { loginRepository.findAllByUserid(it!!) }
                                .map { Log(it.userid, it.name, it.logindate) }
                        , Log::class.java)
                )
    }

}
