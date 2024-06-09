package app.events

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.MessageEmbed
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.components.buttons.Button
import java.awt.Color

class MentionEvent : ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent) {
        if(event.message.contentRaw == event.jda.selfUser.asMention) {
            val embed = EmbedBuilder()
            val button = Button.link("https://discord.com/api/oauth2/authorize?client_id=${event.jda.selfUser.id}&permissions=2086&scope=bot%20applications.app.commands",
                "✉\uFE0F Convide-me")

            embed.setTitle("Oi, eu sou a ${event.jda.selfUser.effectiveName}!!")
                .addField(MessageEmbed.Field("Pretendo ser uma aplicação de proposito geral", "espero ser util xD", false))
                .setThumbnail(event.jda.selfUser.avatarUrl)
                .setDescription("Sou desenvolvida em Kotlin usando JDA")
                .setColor(Color(255, 76, 0))


            event.channel.sendMessageEmbeds(embed.build()).addActionRow(button).queue()
        }
    }
}