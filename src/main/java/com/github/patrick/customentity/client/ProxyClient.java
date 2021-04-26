package com.github.patrick.customentity.client;

import com.github.patrick.customentity.CustomEntityMod;
import com.github.patrick.customentity.Proxy;
import com.github.patrick.customentity.network.PacketDispatcher;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import com.github.patrick.customentity.renderer.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
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
        Minecraft.getMinecraft().getRenderManager().entityRenderMap.put(EntityBat.class, new RenderBat(Minecraft.getMinecraft().getRenderManager()));
        Minecraft.getMinecraft().getRenderManager().entityRenderMap.put(EntityBlaze.class, new RenderBlaze(Minecraft.getMinecraft().getRenderManager()));
        Minecraft.getMinecraft().getRenderManager().entityRenderMap.put(EntityCaveSpider.class, new RenderCaveSpider(Minecraft.getMinecraft().getRenderManager()));
        Minecraft.getMinecraft().getRenderManager().entityRenderMap.put(EntityChicken.class, new RenderChicken(Minecraft.getMinecraft().getRenderManager()));
        Minecraft.getMinecraft().getRenderManager().entityRenderMap.put(EntityCow.class, new RenderCow(Minecraft.getMinecraft().getRenderManager()));
        Minecraft.getMinecraft().getRenderManager().entityRenderMap.put(EntityCreeper.class, new RenderCreeper(Minecraft.getMinecraft().getRenderManager()));
        Minecraft.getMinecraft().getRenderManager().entityRenderMap.put(EntityDragon.class, new RenderDragon(Minecraft.getMinecraft().getRenderManager()));
        Minecraft.getMinecraft().getRenderManager().entityRenderMap.put(EntityElderGuardian.class, new RenderElderGuardian(Minecraft.getMinecraft().getRenderManager()));
        Minecraft.getMinecraft().getRenderManager().entityRenderMap.put(EntityEnderman.class, new RenderEnderman(Minecraft.getMinecraft().getRenderManager()));
        Minecraft.getMinecraft().getRenderManager().entityRenderMap.put(EntityEndermite.class, new RenderEndermite(Minecraft.getMinecraft().getRenderManager()));
        Minecraft.getMinecraft().getRenderManager().entityRenderMap.put(EntityEvoker.class, new RenderEvoker(Minecraft.getMinecraft().getRenderManager()));
        Minecraft.getMinecraft().getRenderManager().entityRenderMap.put(EntityGhast.class, new RenderGhast(Minecraft.getMinecraft().getRenderManager()));
        Minecraft.getMinecraft().getRenderManager().entityRenderMap.put(EntityGiantZombie.class, new RenderGiantZombie(Minecraft.getMinecraft().getRenderManager(), 6.0F));
        Minecraft.getMinecraft().getRenderManager().entityRenderMap.put(EntityGuardian.class, new RenderGuardian(Minecraft.getMinecraft().getRenderManager()));
        Minecraft.getMinecraft().getRenderManager().entityRenderMap.put(EntityHorse.class, new RenderHorse(Minecraft.getMinecraft().getRenderManager()));
        Minecraft.getMinecraft().getRenderManager().entityRenderMap.put(EntityHusk.class, new RenderHusk(Minecraft.getMinecraft().getRenderManager()));
        Minecraft.getMinecraft().getRenderManager().entityRenderMap.put(EntityIllusionIllager.class, new RenderIllusionIllager(Minecraft.getMinecraft().getRenderManager()));
        Minecraft.getMinecraft().getRenderManager().entityRenderMap.put(EntityIronGolem.class, new RenderIronGolem(Minecraft.getMinecraft().getRenderManager()));
        Minecraft.getMinecraft().getRenderManager().entityRenderMap.put(EntityLlama.class, new RenderLlama(Minecraft.getMinecraft().getRenderManager()));
        Minecraft.getMinecraft().getRenderManager().entityRenderMap.put(EntityMagmaCube.class, new RenderMagmaCube(Minecraft.getMinecraft().getRenderManager()));
        Minecraft.getMinecraft().getRenderManager().entityRenderMap.put(EntityMooshroom.class, new RenderMooshroom(Minecraft.getMinecraft().getRenderManager()));
        Minecraft.getMinecraft().getRenderManager().entityRenderMap.put(EntityOcelot.class, new RenderOcelot(Minecraft.getMinecraft().getRenderManager()));
    }
}
