package com.fardragi.gregoriette.discord

import com.fardragi.gregoriette.discord.commands.IBaseCommand
import com.fardragi.gregoriette.discord.commands.TpsCommand
import dev.minn.jda.ktx.events.onCommand
import dev.minn.jda.ktx.interactions.commands.restrict
import dev.minn.jda.ktx.interactions.commands.slash
import dev.minn.jda.ktx.interactions.commands.updateCommands
import net.dv8tion.jda.api.JDA

class HandlerCommands(client: JDA) {
  companion object {
    fun register(client: JDA) {
      val commands = listOf<IBaseCommand>(TpsCommand())

      commands.forEach { command ->
        client
            .updateCommands { slash(command.name(), command.description()) { restrict(true) } }
            .queue()
        client.onCommand(command.name()) { event -> command.exec(event) }
      }
    }
  }
}
