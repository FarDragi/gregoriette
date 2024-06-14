package com.fardragi.gregoriette.discord.commands

import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent

interface IBaseCommand {
  fun name(): String
  fun description(): String
  fun exec(event: GenericCommandInteractionEvent)
}
