package app.events

import net.dv8tion.jda.api.events.guild.GuildLeaveEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class GuildLeaveEvent : ListenerAdapter() {
    override fun onGuildLeave(event: GuildLeaveEvent) {
        val padraoDataHora = DateTimeFormatter.ofPattern("hh:mm:ss dd/MM/yyyy")
        println("[${LocalDateTime.now().format(padraoDataHora)}] ${event.jda.selfUser.effectiveName} saiu do servidor ${event.guild.name}")
    }
}