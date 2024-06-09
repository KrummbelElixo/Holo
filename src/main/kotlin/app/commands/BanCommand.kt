package app.commands

import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.entities.MessageEmbed
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions
import net.dv8tion.jda.api.interactions.commands.build.OptionData
import net.dv8tion.jda.api.interactions.commands.build.Commands
import net.dv8tion.jda.api.interactions.commands.build.CommandData
import java.awt.Color
import java.util.concurrent.TimeUnit

class BanCommand : ListenerAdapter() {
    companion object {
        fun getCommandData(): CommandData {
            val usuario = OptionData(OptionType.USER, "ban", "Selecione um usuário para punir >:)", true)
            val motivo = OptionData(OptionType.STRING, "motivo", "Informe o motivo da punição", false)
            return Commands.slash("ban", "Puna um usuário")
                .addOptions(usuario, motivo)
                .setDefaultPermissions(DefaultMemberPermissions
                    .enabledFor(Permission.BAN_MEMBERS))
        }
    }

    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        if(event.name == "ban") {
            val usuario = event.getOption("ban")
            val motivo = event.getOption("motivo")?.asString
            val embed = EmbedBuilder()

            embed.setTitle("BANIMENTO REALIZADO!")
                .setDescription("Usuario Banido: ${usuario?.asUser?.name}")
                .setThumbnail(usuario?.asUser?.avatarUrl)
                .addField(MessageEmbed.Field("Motivo", motivo, false))
                .setFooter("Comando enviado por ${event.user.name}", event.user.avatarUrl)
                .setColor(Color(255, 0, 0))

            usuario?.asMember?.ban(10, TimeUnit.SECONDS)?.queue()
            event.replyEmbeds(embed.build()).queue()
        }
    }
}