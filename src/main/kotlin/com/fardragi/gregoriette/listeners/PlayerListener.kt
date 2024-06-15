package com.fardragi.gregoriette.listeners

import com.fardragi.gregoriette.Gregoriette
import cpw.mods.fml.common.eventhandler.EventPriority
import cpw.mods.fml.common.eventhandler.SubscribeEvent
import cpw.mods.fml.common.gameevent.PlayerEvent

class PlayerListener(val gregoriette: Gregoriette) {
  @SubscribeEvent(priority = EventPriority.LOWEST)
  fun onPlayerJoin(event: PlayerEvent.PlayerLoggedInEvent) {
    gregoriette.logger.info("Call player join event")
    val player = event.player
    gregoriette.bot.playerJoin(player)
  }

  @SubscribeEvent(priority = EventPriority.LOWEST)
  fun onPlayerLeft(event: PlayerEvent.PlayerLoggedOutEvent) {
    gregoriette.logger.info("Call player left event")
    val player = event.player
    gregoriette.bot.playerLeft(player)
  }
}
