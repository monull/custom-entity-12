package com.github.patrick.customentity.renderer;

import com.github.patrick.customentity.client.CustomEntity;
import com.github.patrick.customentity.client.CustomEntityManager;
import net.minecraft.client.model.ModelLlama;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerLlamaDecor;
import net.minecraft.entity.passive.EntityLlama;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderLlama extends RenderLiving<EntityLlama>
{
    private static final ResourceLocation[] LLAMA_TEXTURES = new ResourceLocation[] {new ResourceLocation("textures/entity/llama/llama_creamy.png"), new ResourceLocation("textures/entity/llama/llama_white.png"), new ResourceLocation("textures/entity/llama/llama_brown.png"), new ResourceLocation("textures/entity/llama/llama_gray.png")};

    public RenderLlama(RenderManager p_i47203_1_)
    {
        super(p_i47203_1_, new ModelLlama(0.0F), 0.7F);
    }

    protected ResourceLocation getEntityTexture(EntityLlama entity)
    {
        return LLAMA_TEXTURES[entity.getVariant()];
    }

    @Override
    protected void preRenderCallback(EntityLlama entitylivingbaseIn, float partialTickTime) {
        CustomEntity custom = CustomEntityManager.getOrCreateInstance().getCustomEntity(entitylivingbaseIn.getEntityId());
        if(custom != null) {
            custom.applyGraphic(entitylivingbaseIn);
        }
    }
}