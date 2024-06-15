package com.fardragi.gregoriette.listeners

import com.fardragi.gregoriette.Gregoriette
import cpw.mods.fml.common.eventhandler.EventPriority
import cpw.mods.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.event.ServerChatEvent

class ChatListener(val gregoriette: Gregoriette) {
  @SubscribeEvent(priority = EventPriority.LOWEST)
  fun onPlayerChat(event: ServerChatEvent) {
    gregoriette.logger.info("Call server chat event")
    val player = event.player
    val message = event.message
    gregoriette.bot.chat(player, message)
  }
}
