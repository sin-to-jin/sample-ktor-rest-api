package com.a_pags_server

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.routing.get
import io.ktor.routing.route
import io.ktor.routing.routing
import java.time.LocalDateTime

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        jackson {
            configure(SerializationFeature.INDENT_OUTPUT, true)
        }
    }
    routing {
        route("/api/v1/users") {
            get {
                val users = createUsers()
                call.respond(users)
            }
            get("/{id}") {
                val id: Int = Integer.parseInt(call.parameters["id"])
                val user = createUser(id)
                call.respond(user)
            }
        }
        get("/") {
            call.respondText("Hello, World")
        }
   }
}

data class User(
    val id: Int,
    val fullName: String,
    val hometown: String,
    val residence: String,
    val education: String,
    val job: String,
    val created_at: LocalDateTime,
    val updated_at: LocalDateTime
)

fun sampleData(): User {
    return User(1, "ogasawaraShinnosuke", "SECRET", "東京", "情報システム修士", "エンジニア",
        LocalDateTime.of(2020, 8, 3, 16, 45, 51),
        LocalDateTime.of(2020, 8, 5, 16, 45, 51)
    )
}

fun createUser(id: Int): User {
    val user = sampleData()
    if (id == user.id) {
        return user
    }
    return User(0, "NO NAME", "NO TOWN", "NO TOWN", "NO", "NO JOB", LocalDateTime.now(), LocalDateTime.now())
}
fun createUsers(): List<User> {
    return listOf(sampleData())
}
