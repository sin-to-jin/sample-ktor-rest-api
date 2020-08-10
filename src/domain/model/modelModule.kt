package com.a_pags_server.domain.model

import org.koin.dsl.module
import org.koin.experimental.builder.singleBy

var modelModule = module() {
    singleBy<Dao<UserDTO>, UserDao>()
}
