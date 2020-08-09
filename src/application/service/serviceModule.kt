package com.a_pags_server.application.service

import org.koin.dsl.module
import org.koin.experimental.builder.singleBy

val serviceModule = module() {
    singleBy<UserService, UserServiceImpl>()
}
