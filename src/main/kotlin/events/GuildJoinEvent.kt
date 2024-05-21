package events

import net.dv8tion.jda.api.events.guild.GuildJoinEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class GuildJoinEvent : ListenerAdapter() {
    override fun onGuildJoin(event: GuildJoinEvent) {
        val padraoDataHora = DateTimeFormatter.ofPattern("hh:mm:ss dd/MM/yyyy")
        println("[${LocalDateTime.now().format(padraoDataHora)}] ${event.jda.selfUser.effectiveName} entrou no servidor ${event.guild.name}")
    }
}