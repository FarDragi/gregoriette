package com.fardragi.gregoriette.discord

import com.fardragi.gregoriette.Config
import com.fardragi.gregoriette.Gregoriette
import com.fardragi.gregoriette.discord.listeners.MessageListener
import dev.minn.jda.ktx.generics.getChannel
import dev.minn.jda.ktx.jdabuilder.intents
import dev.minn.jda.ktx.jdabuilder.light
import dev.minn.jda.ktx.messages.Embed
import dev.minn.jda.ktx.messages.MessageCreate
import net.dv8tion.jda.api.entities.MessageEmbed
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel
import net.dv8tion.jda.api.requests.GatewayIntent
import net.minecraft.entity.player.EntityPlayer

class DiscordBot(val gregoriette: Gregoriette) {
  private val client =
      light(Config.botToken, true) {
        intents += listOf(GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT)
      }

  init {
    gregoriette.logger.info("Bot starting")
    HandlerCommands.register(client)
    client.addEventListener(MessageListener())
  }

  private fun sendMessageInMainChannel(embed: MessageEmbed) {
    val channel = client.getChannel<TextChannel>(Config.discordChannel)
    channel?.sendMessage(MessageCreate { embeds += embed })?.queue()
  }

  fun playerJoin(player: EntityPlayer) {
    sendMessageInMainChannel(
        Embed {
          title = "${player.displayName} entrou"
          color = Colors.GREEN
          thumbnail = "https://mc-heads.net/avatar/${player.displayName}/200"
        })
  }

  fun playerLeft(player: EntityPlayer) {
    sendMessageInMainChannel(
        Embed {
          title = "${player.displayName} saiu"
          color = Colors.RED
          thumbnail = "https://mc-heads.net/avatar/${player.displayName}/200"
        })
  }

  fun serverStarting() {
    sendMessageInMainChannel(
        Embed {
          title = "Servidor iniciando"
          color = Colors.YELLOW
        })
  }

  fun serverStart() {
    sendMessageInMainChannel(
        Embed {
          title = "Servidor iniciado"
          color = Colors.GREEN
        })
  }

  fun serverStop() {
    sendMessageInMainChannel(
        Embed {
          title = "Servidor fechado"
          color = Colors.RED
        })
  }

  fun chat(player: EntityPlayer, message: String) {
    sendMessageInMainChannel(
        Embed {
          description = message
          author {
            name = player.displayName
            iconUrl = "https://mc-heads.net/avatar/${player.displayName}/200"
          }
          color = Colors.BLUE
        })
  }
}
