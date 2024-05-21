package events

import net.dv8tion.jda.api.events.session.ReadyEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class ReadyEvent : ListenerAdapter() {
    override fun onReady(event: ReadyEvent) {
        println("Logado como [${event.jda.selfUser.effectiveName}]")
        println("PRONTA PARA OPERAR!")
    }
}