package com.fardragi.gregoriette.discord.commands

import com.fardragi.gregoriette.Gregoriette
import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent

class TpsCommand : IBaseCommand {
  override fun name(): String {
    return "tps"
  }

  override fun description(): String {
    return "Check server tps"
  }

  override fun exec(event: GenericCommandInteractionEvent) {
    val tps = Gregoriette.proxy.server.tickTimeArray.average()

    event.reply("TPS: $tps").queue()
  }
}
