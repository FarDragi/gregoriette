package com.fardragi.gregoriette

import com.fardragi.gregoriette.discord.DiscordBot
import com.fardragi.gregoriette.exceptions.GregorietteException
import com.fardragi.gregoriette.listeners.PlayerListener
import cpw.mods.fml.common.FMLCommonHandler
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.SidedProxy
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.event.FMLPostInitializationEvent
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.event.FMLServerStartingEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

@Mod(
    modid = Gregoriette.MODID,
    version = Tags.VERSION,
    name = "Gregoriette",
    acceptedMinecraftVersions = "[1.7.10]",
    acceptableRemoteVersions = "*")
class Gregoriette {
  private var _bot: DiscordBot? = null
  val bot: DiscordBot
    get() = _bot ?: throw GregorietteException("Fail get bot")
  val logger
    get() = LOG

  @Mod.EventHandler
  fun preInit(event: FMLPreInitializationEvent) {
    proxy.preInit(event)
    CoroutineScope(Dispatchers.IO).launch { _bot = DiscordBot() }
  }

  @Mod.EventHandler
  fun init(event: FMLInitializationEvent?) {
    proxy.init(event)
  }

  @Mod.EventHandler // postInit "Handle interaction with other mods, complete your setup based on
  // this." (Remove if not needed)
  fun postInit(event: FMLPostInitializationEvent?) {
    proxy.postInit(event)
  }

  @Mod.EventHandler
  fun serverStarting(event: FMLServerStartingEvent?) {
    proxy.serverStarting(event)
    FMLCommonHandler.instance().bus().register(PlayerListener(this))
  }

  companion object {
    const val MODID: String = "gregoriette"
    @JvmField val LOG: Logger = LogManager.getLogger(MODID)

    @SidedProxy(serverSide = "com.fardragi.gregoriette.CommonProxy")
    private var _proxy: CommonProxy? = null
    val proxy: CommonProxy
      get() = _proxy ?: throw GregorietteException("Fail get proxy")
  }
}
