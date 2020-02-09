package it.usuratonkachi.kotlin.kotlindemo.mongo

import org.springframework.data.annotation.Id
import java.io.Serializable
import java.util.*


class Login(@Id var id: String = UUID.randomUUID().toString(), var userid: Int, var name: String, var logindate: Date = Date() ) : Serializable {
    override fun toString(): String = String.format("user id %s with name %s logged @ %s", userid, name, logindate.toString())
}
