package com.a_pags_server

import com.a_pags_server.application.service.UserService
import com.a_pags_server.application.service.serviceModule
import com.a_pags_server.domain.model.modelModule
import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.features.CORS
import io.ktor.response.*
import io.ktor.features.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.jackson.jackson
import io.ktor.routing.get
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.util.KtorExperimentalAPI
import io.ktor.util.getOrFail
import org.jetbrains.exposed.sql.Database
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.inject

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@KtorExperimentalAPI
@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(CORS) {
        listOf(HttpMethod.Get, HttpMethod.Patch, HttpMethod.Post, HttpMethod.Delete).forEach { method(it) }
        header(HttpHeaders.AccessControlAllowOrigin)
        host("localhost:8080")
    }
    install(ContentNegotiation) {
        jackson {
            configure(SerializationFeature.INDENT_OUTPUT, true)
        }
    }
    install(Koin) {
        modules(serviceModule, modelModule)
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
                val id: Long = call.parameters.getOrFail<Long>("id")
                val user = userService.getUser(id)
                call.respond(user)
            }
        }
        get("/ping") {
            call.respondText("Pong")
        }
   }
}
