package com.github.patrick.customentity;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

public abstract class Proxy {
    public Proxy() {

    }
    public abstract void init(FMLInitializationEvent var1);
    public abstract void postInit(FMLPostInitializationEvent var1);
}
