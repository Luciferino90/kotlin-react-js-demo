package it.usuratonkachi.kotlin.kotlindemo.r2dbc

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface UserRepository : ReactiveCrudRepository<User, Int> {
    //fun findByUsername(username: String) : Mono<User>
}

