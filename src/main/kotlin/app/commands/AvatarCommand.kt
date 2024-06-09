package app.commands

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.User
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

            val usuario: User

            if(usuarioOption != null) {
                usuario = usuarioOption.asUser
            } else {
                usuario = event.user
            }

            embed.setTitle(":frame_photo: Avatar de ${usuario.name}")
                .setUrl(usuario.avatarUrl)
                .setImage("${usuario.avatarUrl}?size=2048")
                .setFooter("Comando enviado por ${event.user.name}", event.user.avatarUrl)

            event.replyEmbeds(embed.build()).queue()
        }
    }
}