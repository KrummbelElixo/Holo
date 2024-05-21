package com.holo.bot.app

import app.Application
import javax.security.auth.login.LoginException

fun main() {
    try {
        Application().main()
    } catch (e: LoginException) {
        println("Erro ao iniciar bot, provavelmente o token é inválido")
    }
}

