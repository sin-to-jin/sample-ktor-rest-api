package com.a_pags_server.application.service

import com.a_pags_server.domain.model.Dao
import com.a_pags_server.domain.model.UserDTO
import org.koin.core.KoinComponent
import org.koin.core.inject

interface UserService {

    fun getUsers(): List<UserDTO>

    fun getUser(id: Long): UserDTO
}

class UserServiceImpl: UserService, KoinComponent {

    private val dao by inject<Dao<UserDTO>>()

    override fun getUsers(): List<UserDTO> {
        return dao.findAll()
    }

    override fun getUser(id: Long): UserDTO {
        return dao.findById(id)
    }
}
