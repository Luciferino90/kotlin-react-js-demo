package it.usuratonkachi.kotlin.kotlindemo.r2dbc

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.io.Serializable

@Table(value = "user")
class User(var username: String, @Id var id: Int? = null): Serializable {
    override fun toString(): String = String.format("user id %s with name %s", id, username)
}
