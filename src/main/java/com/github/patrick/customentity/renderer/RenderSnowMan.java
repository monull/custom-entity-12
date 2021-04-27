package com.github.patrick.customentity.renderer;

import com.github.patrick.customentity.client.CustomEntity;
import com.github.patrick.customentity.client.CustomEntityManager;
import net.minecraft.client.model.ModelSnowMan;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerSnowmanHead;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSnowMan extends RenderLiving<EntitySnowman>
{
    private static final ResourceLocation SNOW_MAN_TEXTURES = new ResourceLocation("textures/entity/snowman.png");

    public RenderSnowMan(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelSnowMan(), 0.5F);
    }

    protected ResourceLocation getEntityTexture(EntitySnowman entity)
    {
        return SNOW_MAN_TEXTURES;
    }

    public ModelSnowMan getMainModel()
    {
        return (ModelSnowMan)super.getMainModel();
    }

    @Override
    protected void preRenderCallback(EntitySnowman entitylivingbaseIn, float partialTickTime) {
        CustomEntity custom = CustomEntityManager.getOrCreateInstance().getCustomEntity(entitylivingbaseIn.getEntityId());
        if(custom != null) {
            this.shadowSize = custom.getShadowSize();
            custom.applyGraphic(entitylivingbaseIn);
        }
    }
}
