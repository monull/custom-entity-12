package com.github.patrick.customentity.renderer;

import com.github.patrick.customentity.client.CustomEntity;
import com.github.patrick.customentity.client.CustomEntityManager;
import net.minecraft.client.model.ModelIllager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityVindicator;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderVindicator extends RenderLiving<EntityMob>
{
    private static final ResourceLocation VINDICATOR_TEXTURE = new ResourceLocation("textures/entity/illager/vindicator.png");

    public RenderVindicator(RenderManager p_i47189_1_)
    {
        super(p_i47189_1_, new ModelIllager(0.0F, 0.0F, 64, 64), 0.5F);
        this.addLayer(new LayerHeldItem(this)
        {
            public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
            {
                if (((EntityVindicator)entitylivingbaseIn).isAggressive())
                {
                    super.doRenderLayer(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
                }
            }
            protected void translateToHand(EnumHandSide p_191361_1_)
            {
                ((ModelIllager)this.livingEntityRenderer.getMainModel()).getArm(p_191361_1_).postRender(0.0625F);
            }
        });
    }

    public void doRender(EntityMob entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    protected ResourceLocation getEntityTexture(EntityMob entity)
    {
        return VINDICATOR_TEXTURE;
    }

    protected void preRenderCallback(EntityMob entitylivingbaseIn, float partialTickTime)
    {
        float f = 0.9375F;
        GlStateManager.scale(0.9375F, 0.9375F, 0.9375F);
        CustomEntity custom = CustomEntityManager.getOrCreateInstance().getCustomEntity(entitylivingbaseIn.getEntityId());
        if(custom != null) {
            this.shadowSize = custom.getShadowSize();
            custom.applyGraphic(entitylivingbaseIn);
        }
    }
}