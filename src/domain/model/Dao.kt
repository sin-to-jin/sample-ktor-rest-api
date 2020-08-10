package com.a_pags_server.domain.model

interface Dao<T> {

    fun findAll(): List<T>

    fun findById(id: Long = 0L): T
}
