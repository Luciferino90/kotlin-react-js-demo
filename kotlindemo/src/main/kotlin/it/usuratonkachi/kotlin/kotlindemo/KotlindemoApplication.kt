package it.usuratonkachi.kotlin.kotlindemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories


@SpringBootApplication
@EnableR2dbcRepositories
@EnableReactiveMongoRepositories
class KotlindemoApplication

fun main(args: Array<String>) {
	runApplication<KotlindemoApplication>(*args)
}
