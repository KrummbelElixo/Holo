package commands

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.commands.build.CommandData
import net.dv8tion.jda.api.interactions.commands.build.Commands
import java.awt.Color

class PingCommand : ListenerAdapter() {
    companion object {
        fun getCommandData(): CommandData {
            return Commands.slash("ping", "Veja o ping do bot")
        }
    }

    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        if(event.name == "ping") {
            val embed = EmbedBuilder()
            embed.setTitle(":ping_pong: Ping da ${event.jda.selfUser.effectiveName}")
                .setDescription("O ping do bot é de **${event.jda.gatewayPing} ms**")
                .setFooter("Comando enviado por ${event.user.name}", event.user.avatarUrl)
                .setColor(Color(139, 255, 0))

            event.replyEmbeds(embed.build()).queue()
        }
    }
}