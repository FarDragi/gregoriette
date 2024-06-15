package com.fardragi.gregoriette.discord.listeners

import com.fardragi.gregoriette.Config
import com.fardragi.gregoriette.Gregoriette
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.minecraft.util.ChatComponentText

class MessageListener : ListenerAdapter() {
  override fun onMessageReceived(event: MessageReceivedEvent) {
    if (event.author.isBot) return
    if (event.channel.id != Config.discordChannel) return

    Gregoriette.proxy.server.configurationManager.playerEntityList.forEach { player ->
      val message = ChatComponentText("[${event.author.globalName}] ${event.message.contentRaw}")

      player.addChatMessage(message)
    }
  }
}
