package com.holo.bot.commands

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.CommandData
import net.dv8tion.jda.api.interactions.commands.build.Commands
import net.dv8tion.jda.api.interactions.commands.build.OptionData

class AvatarCommand : ListenerAdapter() {
    companion object {
        fun getCommandData(): CommandData {
            val usuario = OptionData(OptionType.USER, "usuario", "Veja o avatar de outro usuário.", false)
            return Commands.slash("avatar", "Veja o seu avatar ou de outro usuário").addOptions(usuario)
        }
    }

    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        if(event.name == "avatar") {
            val usuarioOption = event.getOption("usuario")
            val embed = EmbedBuilder()

            if(usuarioOption != null) {
                embed.setTitle(":frame_photo: Avatar de ${usuarioOption.asUser.name}")
                    .setUrl(usuarioOption.asUser.avatarUrl)
                    .setImage(usuarioOption.asUser.avatarUrl)
                    .setFooter("Comando enviado por ${event.user.name}", event.user.avatarUrl)
            } else {
                embed.setTitle(":frame_photo: Avatar de ${event.user.name}")
                    .setUrl(event.user.avatarUrl)
                    .setImage(event.user.avatarUrl)
                    .setFooter("Comando enviado por ${event.user.name}", event.user.avatarUrl)
            }

            event.replyEmbeds(embed.build()).queue()
        }
    }
}