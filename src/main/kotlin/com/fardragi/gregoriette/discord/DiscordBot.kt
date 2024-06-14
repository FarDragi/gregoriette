package com.fardragi.gregoriette.discord

import com.fardragi.gregoriette.Config
import com.fardragi.gregoriette.Gregoriette
import dev.minn.jda.ktx.generics.getChannel
import dev.minn.jda.ktx.jdabuilder.light
import dev.minn.jda.ktx.messages.Embed
import dev.minn.jda.ktx.messages.MessageCreate
import net.dv8tion.jda.api.entities.MessageEmbed
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel
import net.minecraft.entity.player.EntityPlayer

class DiscordBot {
  private val client = light(Config.botToken, true)

  init {
    Gregoriette.LOG.info("Bot starting")
    HandlerCommands.register(client)
  }

  private fun sendMessageInMainChannel(embed: MessageEmbed) {
    val channel = client.getChannel<TextChannel>(Config.discordChannel)
    channel?.sendMessage(MessageCreate { embeds += embed })?.queue()
  }

  fun playerJoin(player: EntityPlayer) {
    sendMessageInMainChannel(
        Embed {
          title = "Jogador entrou ${player.displayName}"
          color = Colors.GREEN
        })
  }

  fun playerLeft(player: EntityPlayer) {
    sendMessageInMainChannel(
        Embed {
          title = "Jogador saiu ${player.displayName}"
          color = Colors.RED
        })
  }
}
