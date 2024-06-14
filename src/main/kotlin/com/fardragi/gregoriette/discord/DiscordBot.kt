package com.fardragi.gregoriette.discord

import com.fardragi.gregoriette.Config
import com.fardragi.gregoriette.Gregoriette
import dev.minn.jda.ktx.events.onCommand
import dev.minn.jda.ktx.interactions.commands.restrict
import dev.minn.jda.ktx.interactions.commands.upsertCommand
import dev.minn.jda.ktx.jdabuilder.light

class DiscordBot {
  private val client = light(Config.botToken, true)

  init {
    Gregoriette.LOG.info("Bot starting")

    client.upsertCommand("test", "Test command") { restrict(true) }.queue()
    client.onCommand("test") { event -> event.reply("${event.user.asMention} ola").queue() }
  }
}
