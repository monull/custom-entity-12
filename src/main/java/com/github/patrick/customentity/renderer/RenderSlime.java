package com.github.patrick.customentity.renderer;

import com.github.patrick.customentity.client.CustomEntity;
import com.github.patrick.customentity.client.CustomEntityManager;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerSlimeGel;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSlime extends RenderLiving<EntitySlime>
{
    private static final ResourceLocation SLIME_TEXTURES = new ResourceLocation("textures/entity/slime/slime.png");

    public RenderSlime(RenderManager p_i47193_1_)
    {
        super(p_i47193_1_, new ModelSlime(16), 0.25F);
    }

    public void doRender(EntitySlime entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        this.shadowSize = 0.25F * (float)entity.getSlimeSize();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    protected void preRenderCallback(EntitySlime entitylivingbaseIn, float partialTickTime)
    {
        float f = 0.999F;
        GlStateManager.scale(0.999F, 0.999F, 0.999F);
        float f1 = (float)entitylivingbaseIn.getSlimeSize();
        float f2 = (entitylivingbaseIn.prevSquishFactor + (entitylivingbaseIn.squishFactor - entitylivingbaseIn.prevSquishFactor) * partialTickTime) / (f1 * 0.5F + 1.0F);
        float f3 = 1.0F / (f2 + 1.0F);
        GlStateManager.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
        CustomEntity custom = CustomEntityManager.getOrCreateInstance().getCustomEntity(entitylivingbaseIn.getEntityId());
        if(custom != null) {
            this.shadowSize = custom.getShadowSize();
            custom.applyGraphic(entitylivingbaseIn);
        }
    }

    protected ResourceLocation getEntityTexture(EntitySlime entity)
    {
        return SLIME_TEXTURES;
    }
}