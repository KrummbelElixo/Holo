package app

import app.commands.*
import app.events.*
import io.github.cdimascio.dotenv.Dotenv
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.utils.ChunkingFilter
import net.dv8tion.jda.api.utils.MemberCachePolicy
import net.dv8tion.jda.api.utils.cache.CacheFlag
import java.util.EnumSet

class Application {
    private val config: Dotenv = Dotenv.configure().load()

    fun main() {
        val token = config.get("TOKEN")
        val builder = JDABuilder.createLight(token, EnumSet.allOf(GatewayIntent::class.java))
        builder.setStatus(OnlineStatus.DO_NOT_DISTURB)
        builder.setActivity(Activity.watching("Em desenvolvimento"))
        builder.setMemberCachePolicy(MemberCachePolicy.ALL)
        builder.setChunkingFilter(ChunkingFilter.ALL)
        builder.enableCache(EnumSet.allOf(CacheFlag::class.java))
        val shardManager = builder.build()

        //Events
        shardManager.addEventListener(
            MentionEvent(),
            ReadyEvent(),
            GuildJoinEvent(),
            GuildLeaveEvent()
        )

        //Commands
        shardManager.addEventListener(
            CommandManager(),
            SayCommand(),
            PingCommand(),
            AvatarCommand(),
            BanCommand(),
            KickCommand(),
            UserInfoCommand()
        )
    }
}
