package com.a_pags_server.domain.model

import java.time.LocalDateTime

data class User (
    val id: Int,
    val fullName: String,
    val hometown: String,
    val residence: String,
    val education: String,
    val job: String,
    val created_at: LocalDateTime,
    val updated_at: LocalDateTime
)
fun sampleUser(): User {
    return User(
        1,
        "ogasawaraShinnosuke",
        "SECRET",
        "東京",
        "情報システム修士",
        "エンジニア",
        LocalDateTime.of(2020, 8, 3, 16, 45, 51),
        LocalDateTime.of(2020, 8, 5, 16, 45, 51)
    )
}
fun defaultUser(): User {
    return User(
        0,
        "NO NAME",
        "NO HOMETOWN",
        "NO RESIDENCE",
        "NO EDUCATION",
        "NO JOB",
        LocalDateTime.now(),
        LocalDateTime.now()
    )
}

// Data is sample
class UserDao {

    fun findAll(): List<User> {
        return listOf(sampleUser())
    }

    fun findById(id: Int = 0): User {
        val user = sampleUser()
        return if (user.id == id) {
            user
        } else {
            defaultUser()
        }
    }
}
