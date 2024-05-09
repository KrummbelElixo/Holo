package com.holo.bot

import com.holo.bot.commands.CommandManager
import com.holo.bot.commands.SayCommand
import io.github.cdimascio.dotenv.Dotenv
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.sharding.ShardManager
import net.dv8tion.jda.api.utils.ChunkingFilter
import net.dv8tion.jda.api.utils.MemberCachePolicy
import net.dv8tion.jda.api.utils.cache.CacheFlag
import java.util.*
import javax.security.auth.login.LoginException
//Bot desenvolvido em Kotlin
class Main {
    private val config: Dotenv = Dotenv.configure().load()

    fun main() {
        val token = config.get("TOKEN")
        val builder = JDABuilder.createLight(token, EnumSet.allOf(GatewayIntent::class.java))
        builder.setStatus(OnlineStatus.DO_NOT_DISTURB)
        builder.setActivity(Activity.watching("Em desenvolvimento"))
        builder.setMemberCachePolicy(MemberCachePolicy.ALL)
        (MemberCachePolicy.ALL)
        builder.setChunkingFilter(ChunkingFilter.ALL)
        builder.enableCache(EnumSet.allOf(CacheFlag::class.java))
        val shardManager: JDA = builder.build()

        shardManager.addEventListener(
            CommandManager(),
            SayCommand()
        )
    }
}

fun main() {
    try {
        Main().main()
    } catch (e: LoginException) {
        println("Erro ao iniciar bot, provavelmente o token é inválido")
    }
}

