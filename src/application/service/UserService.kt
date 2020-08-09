package com.a_pags_server.application.service

import com.a_pags_server.domain.model.UserDTO
import com.a_pags_server.domain.model.UserDao

interface UserService {

    fun getUsers(): List<UserDTO>

    fun getUser(id: Int): UserDTO
}

class UserServiceImpl: UserService {

    override fun getUsers(): List<UserDTO> {
        val userDao = UserDao()
        return userDao.findAll()
    }

    override fun getUser(id: Int): UserDTO {
        val userDao = UserDao()
        return userDao.findById(id)
    }
}
