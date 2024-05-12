package com.holo.bot.commands

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.CommandData
import net.dv8tion.jda.api.interactions.commands.build.Commands
import net.dv8tion.jda.api.interactions.commands.build.OptionData
import org.jetbrains.annotations.NotNull

class SayCommand : ListenerAdapter() {
    companion object {
        fun getCommandData(): CommandData {
            val mensagem = OptionData(OptionType.STRING, "mensagem", "Diga algo para o bot falar por você xD", true)
            return Commands.slash("say", "Faça o bot falar por você").addOptions(mensagem)
        }
    }

    override fun onSlashCommandInteraction(@NotNull event: SlashCommandInteractionEvent) {
        if(event.name == "say") {
            val messageOption = event.getOption("mensagem")
            val mensagem = messageOption?.asString
            if (mensagem != null) {
                event.channel.sendMessage(mensagem).queue()
                event.reply("Sua mensagem foi enviada!").setEphemeral(true).queue()
            }
        }
    }
}