package com.a_pags_server.domain.model

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.transactions.transaction

fun defaultUser(): UserDTO {
    return UserDTO(
        0,
        "NO NAME",
        "NO HOMETOWN",
        "NO RESIDENCE",
        "NO EDUCATION",
        "NO JOB"
    )
}

data class UserDTO(
    val id: Int,
    val fullName: String,
    val hometown: String,
    val residence: String,
    val education: String,
    val job: String
)
object Users: IntIdTable() {
    val fullName = varchar("full_name", 256)
    val hometown = varchar("hometown", 256)
    val residence = varchar("residence", 256)
    val education = varchar("education", 256)
    val job = varchar("job", 256)
}
class UserEntity(id: EntityID<Int>): IntEntity(id) {
    companion object: IntEntityClass<UserEntity>(Users)
    var fullName by Users.fullName
    var hometown by Users.hometown
    var residence by Users.residence
    var education by Users.education
    var job by Users.job

    fun entity2dto(): UserDTO {
        val dto = UserDTO(
            id = id.value,
            fullName = fullName,
            hometown = hometown,
            residence = residence,
            education = education,
            job = job
        )
        return dto
    }
}

class UserDao {

    fun findAll(): List<UserDTO> {
        val users: List<UserDTO> = transaction {
            val users = UserEntity.all().map { it.entity2dto() }
            return@transaction users
        }
        return users
    }

    fun findById(id: Int = 0): UserDTO {
        val user = transaction {
            val user = UserEntity.findById(id)?.entity2dto()
            return@transaction user
        }
        return user ?: defaultUser()
    }
}
