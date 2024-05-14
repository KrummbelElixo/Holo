package commands

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.MessageEmbed
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.CommandData
import net.dv8tion.jda.api.interactions.commands.build.Commands
import net.dv8tion.jda.api.interactions.commands.build.OptionData
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

class UserInfoCommand : ListenerAdapter() {
    companion object {
        fun getCommandData(): CommandData {
            val usuario = OptionData(OptionType.USER, "usuario", "Veja as informações de outro usuário", false)
            return Commands.slash("userinfo", "Veja as informações da sua conta ou de outro usuário xD")
                .addOptions(usuario)
        }
    }

    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        if(event.name == "userinfo") {
            val usuario = event.getOption("usuario")
            val embed = EmbedBuilder()
            val padraoDataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")

            if(usuario != null) {
                embed.setTitle(usuario.asUser.effectiveName)
                    .addField("Nome Global ", usuario.asUser.name, true)
                    .addField("ID", usuario.asUser.id, true)
                    .setThumbnail(usuario.asUser.avatarUrl)
                    .addField(MessageEmbed.Field("Conta criada em:", usuario.asUser.timeCreated.format(padraoDataHora), false))
                    .addField(MessageEmbed.Field("Entrou em:", usuario.asMember?.timeJoined?.format(padraoDataHora), true))
            } else {
                embed.setTitle(event.user.effectiveName)
                    .setThumbnail(event.user.avatarUrl)
                    .addField(MessageEmbed.Field("Nome Global", event.user.name, true))
                    .addField(MessageEmbed.Field("ID", event.user.id, true))
                    .addField(MessageEmbed.Field("Conta criada em:", event.user.timeCreated.format(padraoDataHora), false))
                    .addField(MessageEmbed.Field("Entrou em:", event.member?.timeJoined?.format(padraoDataHora), true))
            }

            event.replyEmbeds(embed.build()).queue()
        }
    }
}