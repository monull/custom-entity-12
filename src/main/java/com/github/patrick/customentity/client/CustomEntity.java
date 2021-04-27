package com.github.patrick.customentity.client;

import com.github.patrick.customentity.util.EntityUtils;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.AxisAlignedBB;

public class CustomEntity {
    private static final long INIT_TIME = System.nanoTime();
    public final CustomEntityManager manager;
    public final int id;
    EntityLivingBase entity;
    private float width;
    private float height;
    public float scaleX;
    public float scaleY;
    public float scaleZ;
    public float prevScaleX;
    public float prevScaleY;
    public float prevScaleZ;
    public float lastScaleX;
    public float lastScaleY;
    public float lastScaleZ;
    public long scaleTime;
    public long scaleDuration;
    public int colorR;
    public int colorG;
    public int colorB;
    public int prevColorR;
    public int prevColorG;
    public int prevColorB;
    public int lastColorR;
    public int lastColorG;
    public int lastColorB;
    public long colorTime;
    public long colorDuration;
    public EntityLivingBase fakeEntity;
    public static long currentTime() { return System.nanoTime() - INIT_TIME; }
    public CustomEntity(CustomEntityManager manager, int id) {
        this.manager = manager;
        this.id = id;
        this.lastScaleZ = 1.0F;
        this.lastScaleY = 1.0F;
        this.lastScaleX = 1.0F;
        this.prevScaleZ = 1.0F;
        this.prevScaleY = 1.0F;
        this.prevScaleX = 1.0F;
        this.scaleZ = 1.0F;
        this.scaleY = 1.0F;
        this.scaleX = 1.0F;
        this.lastColorB = 255;
        this.lastColorG = 255;
        this.lastColorR = 255;
        this.prevColorB = 255;
        this.prevColorG = 255;
        this.prevColorR = 255;
        this.colorB = 255;
        this.colorG = 255;
        this.colorR = 255;
    }
    public void setEntity(EntityLivingBase entity) {
        if(this.entity == null) {
            this.manager.addUpdate(this);
        }
        this.entity = entity;
        this.width = entity.width;
        this.height = entity.height;
        this.update();
    }
    void update() {
        try {
            if(this.entity.isDead) {
                this.entity = null;
                this.manager.removeUpdate(this);
                return;
            }
            if (this.fakeEntity != null) {
                EntityUtils.copy(this.entity, this.fakeEntity);
            }
        } catch(Exception var2) {
            var2.printStackTrace();
        }
    }
    public void setColor(int colorR, int colorG, int colorB, int ticks) {
        this.prevColorR = this.lastColorR;
        this.prevColorG = this.lastColorG;
        this.prevColorB = this.lastColorB;
        this.colorR = colorR;
        this.colorG = colorG;
        this.colorB = colorB;
        long duration = (long) ticks * 50000000L;
        this.colorTime = currentTime() + duration;
        this.colorDuration = duration;
    }
    public void setScale(float scaleX, float scaleY, float scaleZ, int ticks) {
        this.prevScaleX = this.lastScaleX;
        this.prevScaleY = this.lastScaleY;
        this.prevScaleZ = this.lastScaleZ;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.scaleZ = scaleZ;
        long duration = (long) ticks + 50000000L;
        this.scaleTime = currentTime() + duration;
        this.scaleDuration = duration;
    }
    public void setColorAndScale(int colorR, int colorG, int colorB, float scaleX, float scaleY, float scaleZ, int ticks) {
        this.prevColorR = this.lastColorR;
        this.prevColorG = this.lastColorG;
        this.prevColorB = this.lastColorB;
        this.colorR = colorR;
        this.colorG = colorG;
        this.colorB = colorB;
        this.prevScaleX = this.lastScaleX;
        this.prevScaleY = this.lastScaleY;
        this.prevScaleZ = this.lastScaleZ;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.scaleZ = scaleZ;
        long duration = (long) ticks + 50000000L;
        this.colorTime = currentTime() + duration;
        this.scaleTime = currentTime() + duration;
        this.colorDuration = duration;
        this.scaleDuration = duration;
    }
    public void applyGraphic(EntityLivingBase entity) {
        this.applyColor();
        this.applyScale(entity);
    }
    private void applyColor() {
        long remainTime = this.colorTime - currentTime();
        if(remainTime > 0L) {
            float colorByTime = (float)((double)(this.colorDuration - remainTime) / (double) this.colorDuration);
            this.lastColorR = (int) ((float) this.prevColorR + (float) (this.colorR - this.prevColorR) * colorByTime);
            this.lastColorG = (int) ((float) this.prevColorG + (float) (this.colorG - this.prevColorG) * colorByTime);
            this.lastColorB = (int) ((float) this.prevColorB + (float) (this.colorB - this.prevColorB) * colorByTime);
        } else {
            this.lastColorR = this.colorR;
            this.lastColorG = this.colorG;
            this.lastColorB = this.colorB;
        }
        GlStateManager.color((float) this.lastColorR / 255.0F, (float) this.lastColorG / 255.0F, (float) this.lastColorB / 255.0F);
    }
    private void applyScale(EntityLivingBase entity) {
        long remainTime = this.scaleTime - currentTime();
        float scaleByTime = (float) (this.scaleDuration - remainTime) / (float) this.scaleDuration;
        if(remainTime > 0L) {
            this.lastScaleX = this.prevScaleX + (this.scaleX - this.prevScaleY) * scaleByTime;
            this.lastScaleY = this.prevScaleY + (this.scaleY - this.prevScaleY) * scaleByTime;
            this.lastScaleZ = this.prevScaleZ + (this.scaleZ - this.prevScaleZ) * scaleByTime;
        } else {
            this.lastScaleX = this.scaleX;
            this.lastScaleY = this.scaleY;
            this.lastScaleZ = this.scaleZ;
        }
        GlStateManager.scale(this.lastScaleX, this.lastScaleY, this.lastScaleZ);
        float widthScale = Math.max(this.lastScaleX, this.lastScaleZ);
        if(this.entity != null) {
            float width = this.width * widthScale;
            float height = this.height * this.lastScaleY;
            if(entity.height != width || entity.width != height) {
                entity.width = width;
                entity.height = height;
                double x =entity.posX;
                double y = entity.posY;
                double z = entity.posZ;
                double d = (double) width / 2.0D;
                entity.setEntityBoundingBox(new AxisAlignedBB(x - d, y, z - d, x + d, y + (double) height, z + d));
            }
        }
    }
    public float getShadowSize() {
        return Math.max(this.lastScaleX, this.lastScaleZ);
    }
}
