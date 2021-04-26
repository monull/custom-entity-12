package com.github.patrick.customentity.renderer;

import com.github.patrick.customentity.client.CustomEntity;
import com.github.patrick.customentity.client.CustomEntityManager;
import net.minecraft.client.model.ModelEnderMite;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderEndermite extends RenderLiving<EntityEndermite>
{
    private static final ResourceLocation ENDERMITE_TEXTURES = new ResourceLocation("textures/entity/endermite.png");

    public RenderEndermite(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelEnderMite(), 0.3F);
    }

    protected float getDeathMaxRotation(EntityEndermite entityLivingBaseIn)
    {
        return 180.0F;
    }

    protected ResourceLocation getEntityTexture(EntityEndermite entity)
    {
        return ENDERMITE_TEXTURES;
    }

    @Override
    protected void preRenderCallback(EntityEndermite entitylivingbaseIn, float partialTickTime) {
        CustomEntity custom = CustomEntityManager.getOrCreateInstance().getCustomEntity(entitylivingbaseIn.getEntityId());
        if(custom != null) {
            custom.applyGraphic(entitylivingbaseIn);
        }
    }
}
