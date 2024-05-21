package commands

import net.dv8tion.jda.api.events.session.ReadyEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class CommandManager : ListenerAdapter() {
     override fun onReady(event: ReadyEvent) {
         val commandData = listOf(
             SayCommand.getCommandData(),
             PingCommand.getCommandData(),
             AvatarCommand.getCommandData(),
             BanCommand.getCommandData(),
             KickCommand.getCommandData(),
             UserInfoCommand.getCommandData()
         )

         event.jda.updateCommands().addCommands(commandData).queue()
     }
}