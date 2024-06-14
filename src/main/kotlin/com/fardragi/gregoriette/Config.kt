package com.fardragi.gregoriette

import java.io.File
import net.minecraftforge.common.config.Configuration

object Config {
  var botToken: String = ""

  @JvmStatic
  fun synchronizeConfiguration(configFile: File?) {
    val configuration = Configuration(configFile)

    botToken =
        configuration.getString(
            "bot_token", Configuration.CATEGORY_GENERAL, botToken, "Discord bot token")

    if (configuration.hasChanged()) {
      configuration.save()
    }
  }
}
