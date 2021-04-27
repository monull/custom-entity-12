package com.github.patrick.customentity.renderer;

import com.github.patrick.customentity.client.CustomEntity;
import com.github.patrick.customentity.client.CustomEntityManager;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerSaddle;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPig extends RenderLiving<EntityPig>
{
    private static final ResourceLocation PIG_TEXTURES = new ResourceLocation("textures/entity/pig/pig.png");

    public RenderPig(RenderManager p_i47198_1_)
    {
        super(p_i47198_1_, new ModelPig(), 0.7F);
    }

    protected ResourceLocation getEntityTexture(EntityPig entity)
    {
        return PIG_TEXTURES;
    }

    @Override
    protected void preRenderCallback(EntityPig entitylivingbaseIn, float partialTickTime) {
        CustomEntity custom = CustomEntityManager.getOrCreateInstance().getCustomEntity(entitylivingbaseIn.getEntityId());
        if(custom != null) {
            this.shadowSize = custom.getShadowSize();
            custom.applyGraphic(entitylivingbaseIn);
        }
    }
}
