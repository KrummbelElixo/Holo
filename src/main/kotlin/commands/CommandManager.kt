package com.holo.bot.commands

import net.dv8tion.jda.api.events.session.ReadyEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.commands.build.CommandData
import net.dv8tion.jda.api.interactions.commands.build.Commands
import org.jetbrains.annotations.NotNull

class CommandManager : ListenerAdapter() {
     override fun onReady(@NotNull event: ReadyEvent) {
         val commandData = arrayListOf(
             SayCommand.getCommandData()
         )

         event.jda.updateCommands().addCommands(commandData).queue()
     }
}