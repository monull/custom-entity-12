package com.github.patrick.customentity;

import com.github.patrick.customentity.command.CustomEntityCommand;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = "customentity", name = "CustomEntity", version = "0.1.2", acceptedMinecraftVersions = "[1.12.2]")
public class CustomEntityMod {
    public static final String MODID = "customentity";
    public static final String NAME = "CustomEntity";
    public static final String VERSION = "0.1.2";
    @SidedProxy(
            clientSide = "com.github.patrick.customentity.client.ProxyClient"
    )
    private static Proxy proxy;
    public static Logger logger;
    public CustomEntityMod() {

    }
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        if(proxy != null) {
            proxy.init(event);
        }
        logger = LogManager.getLogger("customentity");
    }
    @Mod.EventHandler
    public void initPost(FMLPostInitializationEvent event) {
        if(proxy != null) {
            proxy.postInit(event);
        }
    }
    @Mod.EventHandler
    public void serverInit(FMLServerStartingEvent event) { event.registerServerCommand(new CustomEntityCommand()); }
}
