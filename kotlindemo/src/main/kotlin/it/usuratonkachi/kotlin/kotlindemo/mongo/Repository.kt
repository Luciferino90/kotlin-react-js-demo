package it.usuratonkachi.kotlin.kotlindemo.mongo

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface loginRepository : ReactiveMongoRepository<Login, String> {
    fun findAllByUserid(userid: Int) : Flux<Login>
}
