package com.fardragi.gregoriette

import java.io.File
import net.minecraftforge.common.config.Configuration

object Config {
  var botToken: String = ""
  var discordChannel: String = ""

  @JvmStatic
  fun synchronizeConfiguration(configFile: File?) {
    val configuration = Configuration(configFile)

    botToken =
        configuration.getString(
            "bot_token", Configuration.CATEGORY_GENERAL, botToken, "Discord bot token")
    discordChannel =
        configuration.getString(
            "discord_channel", Configuration.CATEGORY_GENERAL, discordChannel, "Discord channel")

    if (configuration.hasChanged()) {
      configuration.save()
    }
  }
}
