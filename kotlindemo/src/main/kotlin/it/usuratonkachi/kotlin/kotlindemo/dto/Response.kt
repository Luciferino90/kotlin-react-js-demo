package it.usuratonkachi.kotlin.kotlindemo.dto

import java.io.Serializable
import java.util.*

class Logs(var logs: List<Log>) : Serializable

class Log(var userid: Int, var username: String, var lastLoginDate: Date) : Serializable {
    override fun toString(): String = String.format("user id %s with name %s logged @ %s", userid, username, lastLoginDate.toString())
}
