package com.github.patrick.customentity.client;

import java.util.LinkedList;
import java.util.Queue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.RenderLivingEvent.Post;
import net.minecraftforge.client.event.RenderLivingEvent.Pre;
import net.minecraftforge.client.model.animation.Animation;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientDisconnectionFromServerEvent;

public class CustomEntityEvent {
    public static CustomEntity rendering;
    private final CustomEntityManager manager;
    private final Queue<EntityLivingBase> entities = new LinkedList();
    CustomEntityEvent(CustomEntityManager manager) { this.manager = manager; }
    @SubscribeEvent
    public void entityConstruct(EntityConstructing event) {
        Entity entity = event.getEntity();
        if(entity instanceof EntityLivingBase) {
            this.entities.add((EntityLivingBase) entity);
        }
    }
    @SubscribeEvent
    public void clientTick(ClientTickEvent event) {
        CustomEntityManager manager = this.manager;
        Queue entities = this.entities;
        EntityLivingBase entity;
        while((entity = (EntityLivingBase) entities.poll()) != null) {
            if(!entity.isDead) {
                CustomEntity custom = manager.getCustomEntity(entity.getEntityId());
                if(custom != null) {
                    custom.setEntity(entity);
                }
            }
        }
        manager.update();
    }
    @SubscribeEvent
    public void disconnect(ClientDisconnectionFromServerEvent event) { this.manager.unregister(); }
}
