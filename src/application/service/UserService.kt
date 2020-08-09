package com.a_pags_server.application.service

import com.a_pags_server.domain.model.User
import com.a_pags_server.domain.model.UserDao

interface UserService {

    fun getUsers(): List<User>

    fun getUser(id: Int): User
}

class UserServiceImpl: UserService {

    override fun getUsers(): List<User> {
        val userDao = UserDao()
        return userDao.findAll()
    }

    override fun getUser(id: Int): User {
        val userDao = UserDao()
        return userDao.findById(id)
    }
}
