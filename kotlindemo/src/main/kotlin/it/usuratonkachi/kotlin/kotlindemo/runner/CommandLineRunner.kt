package it.usuratonkachi.kotlin.kotlindemo.runner

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.util.*


@Component
class KotlindemoRunner : CommandLineRunner {
    override fun run(vararg args: String?) {
        println("Application Started @ " + Date().toString())
    }
}
