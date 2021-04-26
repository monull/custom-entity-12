package com.github.patrick.customentity.renderer;

import com.github.patrick.customentity.client.CustomEntity;
import com.github.patrick.customentity.client.CustomEntityManager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderGuardian;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderElderGuardian extends RenderGuardian
{
    private static final ResourceLocation GUARDIAN_ELDER_TEXTURE = new ResourceLocation("textures/entity/guardian_elder.png");

    public RenderElderGuardian(RenderManager p_i47209_1_)
    {
        super(p_i47209_1_);
    }

    protected void preRenderCallback(EntityGuardian entitylivingbaseIn, float partialTickTime)
    {
        GlStateManager.scale(2.35F, 2.35F, 2.35F);
        CustomEntity custom = CustomEntityManager.getOrCreateInstance().getCustomEntity(entitylivingbaseIn.getEntityId());
        if(custom != null) {
            custom.applyGraphic(entitylivingbaseIn);
        }
    }

    protected ResourceLocation getEntityTexture(EntityGuardian entity)
    {
        return GUARDIAN_ELDER_TEXTURE;
    }
}
