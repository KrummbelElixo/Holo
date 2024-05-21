package commands

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.entities.MessageEmbed
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.Commands
import net.dv8tion.jda.api.interactions.commands.build.CommandData
import net.dv8tion.jda.api.interactions.commands.build.OptionData
import java.awt.Color

class KickCommand : ListenerAdapter() {
    companion object {
        fun getCommandData(): CommandData {
            val usuario = OptionData(OptionType.USER, "kick", "Selecione um usuário para punir >:)", true)
            val motivo = OptionData(OptionType.STRING, "motivo", "Informe o motivo da punição", false)
            return Commands.slash("kick", "Puna um usuário")
                .addOptions(usuario, motivo)
                .setDefaultPermissions(
                    DefaultMemberPermissions
                    .enabledFor(Permission.KICK_MEMBERS))
        }
    }

    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        if(event.name == "ban") {
            val usuario = event.getOption("kick")
            val motivo = event.getOption("motivo")?.asString
            val embed = EmbedBuilder()

            embed.setTitle("EXPULSÃO REALIZADA!")
                .setDescription("Usuario Expulso: ${usuario?.asUser?.name}")
                .setThumbnail(usuario?.asUser?.avatarUrl)
                .addField(MessageEmbed.Field("Motivo", motivo, false))
                .setFooter("Comando enviado por ${event.user.name}", event.user.avatarUrl)
                .setColor(Color(255, 76, 0))

            usuario?.asMember?.kick()?.queue()
            event.replyEmbeds(embed.build()).queue()
        }
    }
}