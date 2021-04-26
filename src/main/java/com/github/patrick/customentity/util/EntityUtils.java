package com.github.patrick.customentity.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import org.jetbrains.annotations.NotNull;

public class EntityUtils {
    private EntityUtils() {

    }
    public static void copy(@NotNull EntityLivingBase copied, @NotNull EntityLivingBase entity) {
        entity.world = copied.world;
        entity.hurtTime = copied.hurtTime;
        entity.deathTime = copied.deathTime;
        entity.distanceWalkedModified = copied.distanceWalkedModified;
        entity.prevDistanceWalkedModified = copied.prevDistanceWalkedModified;
        entity.distanceWalkedOnStepModified = copied.distanceWalkedOnStepModified;
        entity.moveForward = copied.moveForward;
        entity.moveStrafing = copied.moveStrafing;
        entity.onGround = copied.onGround;
        entity.fallDistance = copied.fallDistance;
        entity.setSneaking(copied.isSneaking());
        entity.posX = copied.posX;
        entity.posY = copied.posY;
        entity.posZ = copied.posZ;
        entity.prevPosX = copied.prevPosX;
        entity.prevPosY = copied.prevPosY;
        entity.prevPosZ = copied.prevPosZ;
        entity.lastTickPosX = copied.lastTickPosX;
        entity.lastTickPosY = copied.lastTickPosY;
        entity.lastTickPosZ = copied.lastTickPosZ;
        entity.motionX = copied.motionX;
        entity.motionY = copied.motionY;
        entity.motionZ = copied.motionZ;
        entity.rotationYaw = copied.rotationYaw;
        entity.rotationPitch = copied.rotationPitch;
        entity.prevRotationYaw = copied.prevRotationYaw;
        entity.prevRotationPitch = copied.prevRotationPitch;
        entity.rotationYawHead = copied.rotationYawHead;
        entity.prevRotationYawHead = copied.prevRotationYawHead;
        entity.renderYawOffset = copied.renderYawOffset;
        entity.prevRenderYawOffset = copied.prevRenderYawOffset;
        entity.cameraPitch = copied.cameraPitch;
        entity.prevCameraPitch = copied.prevCameraPitch;
        entity.limbSwingAmount = copied.limbSwingAmount;
        entity.prevLimbSwingAmount = copied.prevLimbSwingAmount;
        entity.limbSwing = copied.limbSwing;
        entity.swingProgress = copied.swingProgress;
        entity.prevSwingProgress = copied.prevSwingProgress;
        entity.isSwingInProgress = copied.isSwingInProgress;
        entity.swingProgressInt = copied.swingProgressInt;
        entity.ticksExisted = copied.ticksExisted;
        if(entity instanceof EntityDragon) {
            entity.rotationYaw += 180.0F;
        }
    }
}
