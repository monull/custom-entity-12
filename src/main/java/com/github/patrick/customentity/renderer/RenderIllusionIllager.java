package com.github.patrick.customentity.renderer;

import com.github.patrick.customentity.client.CustomEntity;
import com.github.patrick.customentity.client.CustomEntityManager;
import net.minecraft.client.model.ModelIllager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityIllusionIllager;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderIllusionIllager extends RenderLiving<EntityMob>
{
    private static final ResourceLocation ILLUSIONIST = new ResourceLocation("textures/entity/illager/illusionist.png");

    public RenderIllusionIllager(RenderManager p_i47477_1_)
    {
        super(p_i47477_1_, new ModelIllager(0.0F, 0.0F, 64, 64), 0.5F);
        this.addLayer(new LayerHeldItem(this)
        {
            public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
            {
                if (((EntityIllusionIllager)entitylivingbaseIn).isSpellcasting() || ((EntityIllusionIllager)entitylivingbaseIn).isAggressive())
                {
                    super.doRenderLayer(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
                }
            }
            protected void translateToHand(EnumHandSide p_191361_1_)
            {
                ((ModelIllager)this.livingEntityRenderer.getMainModel()).getArm(p_191361_1_).postRender(0.0625F);
            }
        });
        ((ModelIllager)this.getMainModel()).hat.showModel = true;
    }

    protected ResourceLocation getEntityTexture(EntityMob entity)
    {
        return ILLUSIONIST;
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

    public void doRender(EntityMob entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        if (entity.isInvisible())
        {
            Vec3d[] avec3d = ((EntityIllusionIllager)entity).getRenderLocations(partialTicks);
            float f = this.handleRotationFloat(entity, partialTicks);

            for (int i = 0; i < avec3d.length; ++i)
            {
                super.doRender(entity, x + avec3d[i].x + (double) MathHelper.cos((float)i + f * 0.5F) * 0.025D, y + avec3d[i].y + (double)MathHelper.cos((float)i + f * 0.75F) * 0.0125D, z + avec3d[i].z + (double)MathHelper.cos((float)i + f * 0.7F) * 0.025D, entityYaw, partialTicks);
            }
        }
        else
        {
            super.doRender(entity, x, y, z, entityYaw, partialTicks);
        }
    }

    public void renderName(EntityMob entity, double x, double y, double z)
    {
        super.renderName(entity, x, y, z);
    }

    protected boolean isVisible(EntityMob p_193115_1_)
    {
        return true;
    }
}
