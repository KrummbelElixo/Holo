package com.holo.bot.commands

import net.dv8tion.jda.api.events.session.ReadyEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.jetbrains.annotations.NotNull

class CommandManager : ListenerAdapter() {
     override fun onReady(@NotNull event: ReadyEvent) {
         val commandData = listOf(
             SayCommand.getCommandData(),
             PingCommand.getCommandData(),
             AvatarCommand.getCommandData()
         )

         event.jda.updateCommands().addCommands(commandData).queue()
     }
}