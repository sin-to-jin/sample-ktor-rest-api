package com.a_pags_server

import com.a_pags_server.application.service.UserService
import com.a_pags_server.application.service.serviceModule
import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.routing.get
import io.ktor.routing.route
import io.ktor.routing.routing
import org.jetbrains.exposed.sql.Database
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.inject

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        jackson {
            configure(SerializationFeature.INDENT_OUTPUT, true)
        }
    }
    install(Koin) {
        modules(serviceModule)
    }
    Database.connect(
        "jdbc:postgresql://localhost:5432/a_pags_backend_development",
        driver = "org.postgresql.Driver", user = "jin", password = "password")

    val userService by inject<UserService>()

    routing {
        route("/api/v1/users") {

            get {
                val users = userService.getUsers()
                call.respond(users)
            }
            get("/{id}") {
                val id: Int = Integer.parseInt(call.parameters["id"])
                val user = userService.getUser(id)
                call.respond(user)
            }
        }
        get("/") {
            call.respondText("Hello, World")
        }
        get("/ping") {
            call.respondText("Pong")
        }
   }
}
