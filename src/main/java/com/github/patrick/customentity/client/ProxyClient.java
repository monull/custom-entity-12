package com.github.patrick.customentity.client;

import com.github.patrick.customentity.CustomEntityMod;
import com.github.patrick.customentity.Proxy;
import com.github.patrick.customentity.network.PacketDispatcher;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import com.github.patrick.customentity.renderer.RenderArmorStand;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ProxyClient extends Proxy {
    public ProxyClient() {

    }
    public void init(FMLInitializationEvent event) { PacketDispatcher.registerPackets(); }
    public void postInit(FMLPostInitializationEvent event) {
        Minecraft.getMinecraft().getRenderManager().entityRenderMap.put(EntityArmorStand.class, new RenderArmorStand(Minecraft.getMinecraft().getRenderManager()));
    }
}
