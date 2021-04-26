package com.github.patrick.customentity.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.common.MinecraftForge;
import org.jetbrains.annotations.NotNull;

public class CustomEntityManager {
    public static CustomEntityManager instance;
    private final CustomEntityEvent listener = new CustomEntityEvent(this);
    private final Map<Integer, CustomEntity> entities = new HashMap();
    private final List<CustomEntity> updateEntities = new ArrayList(32);
    private final Map<EntityLivingBase, CustomEntity> fakesByCustom = new IdentityHashMap();
    private CustomEntity[] updates;
    @NotNull
    public static CustomEntityManager getOrCreateInstance() {
        if(instance == null) {
            instance = new CustomEntityManager();
        }
        return instance;
    }
    private CustomEntityManager() { MinecraftForge.EVENT_BUS.register(this.listener); }
    public CustomEntity getCustomEntity(int id) { return (CustomEntity) this.entities.get(id); }
    public void createCustomEntity(int id) {
        CustomEntity custom = (CustomEntity) this.entities.get(id);
        if(custom == null) {
            custom = new CustomEntity(this, id);
            this.entities.put(id, custom);
            Entity entity = Minecraft.getMinecraft().world.getEntityByID(id);
            if(entity instanceof EntityLivingBase) {
                custom.setEntity((EntityLivingBase) entity);
            }
        }
    }
    public void removeCustomEntity(int id) {
        CustomEntity entity = (CustomEntity) this.entities.remove(id);
        if(entity != null) {
            this.removeUpdate(entity);
            EntityLivingBase fake = entity.fakeEntity;
            if(fake != null) {
                this.unregisterFake(fake);
            }
        }
    }
    public void unregisterFake(EntityLivingBase fake) { this.fakesByCustom.remove(fake); }
    public void addUpdate(CustomEntity entity) {
        this.updateEntities.add(entity);
        this.updates = null;
    }
    public void removeUpdate(CustomEntity entity) {
        this.updateEntities.remove(entity);
        this.updates = null;
    }
    void update() {
        if(this.updates == null) {
            this.updates = (CustomEntity[]) this.updateEntities.toArray(new CustomEntity[0]);
        }
        CustomEntity[] var1 = this.updates;
        int var2 = var1.length;
        for(int var3 = 0; var3 < var2; ++var3){
            CustomEntity entity = var1[var3];
            entity.update();
        }
    }
    public void unregister() {
        if(instance == this) {
            this.entities.clear();
            this.updateEntities.clear();
            this.fakesByCustom.clear();
            this.updates = null;
            MinecraftForge.EVENT_BUS.unregister(this.listener);
            instance = null;
        }
    }
}
