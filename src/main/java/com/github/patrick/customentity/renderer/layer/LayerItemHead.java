package com.github.patrick.customentity.renderer.layer;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntityZombieVillager;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

@SideOnly(Side.CLIENT)
public class LayerItemHead implements LayerRenderer<EntityLivingBase> {
    private final LayerCustomHead defaultRenderer;
    private final ModelRenderer modelRenderer;

    public LayerItemHead(ModelRenderer renderer) {
        this.modelRenderer = renderer;
        this.defaultRenderer = new LayerCustomHead(renderer);
    }

    @Override
    public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if (!entitylivingbaseIn.isInvisible()) {
            this.defaultRenderer.doRenderLayer(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
        } else {
            ItemStack itemstack = entitylivingbaseIn.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
            if (!itemstack.isEmpty()) {
                Item item = itemstack.getItem();
                Minecraft minecraft = Minecraft.getMinecraft();
                GlStateManager.pushMatrix();

                if (entitylivingbaseIn.isSneaking()) {
                    GlStateManager.translate(0.0F, 0.2F, 0.0F);
                }

                boolean flag = entitylivingbaseIn instanceof EntityVillager || entitylivingbaseIn instanceof EntityZombieVillager;

                if (entitylivingbaseIn.isChild() && !(entitylivingbaseIn instanceof EntityVillager)) {
                    GlStateManager.translate(0.0F, 0.5F * scale, 0.0F);
                    GlStateManager.scale(0.7F, 0.7F, 0.7F);
                    GlStateManager.translate(0.0F, 16.0F * scale, 0.0F);
                }

                this.modelRenderer.postRender(0.0625F);
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

                if (item == Items.SKULL) {
                    GlStateManager.translate(0.0F, 1.4375F, 0.0F);
                    GlStateManager.scale(2.0F, -2.0F, -2.0F);
                    if (flag) {
                        GlStateManager.translate(0.0F, 0.1875F, 0.0F);
                    }

                    GameProfile gameprofile = null;

                    if (itemstack.hasTagCompound()) {
                        NBTTagCompound nbttagcompound = itemstack.getTagCompound();

                        if (nbttagcompound.hasKey("SkullOwner", 10)) {
                            gameprofile = NBTUtil.readGameProfileFromNBT(nbttagcompound.getCompoundTag("SkullOwner"));
                        } else if (nbttagcompound.hasKey("SkullOwner", 8)) {
                            String s = nbttagcompound.getString("SkullOwner");

                            if (!StringUtils.isBlank(s)) {
                                gameprofile = TileEntitySkull.updateGameprofile(new GameProfile((UUID)null, s));
                                nbttagcompound.setTag("SkullOwner", NBTUtil.writeGameProfile(new NBTTagCompound(), gameprofile));
                            }
                        }
                    }

                    TileEntitySkullRenderer.instance.renderSkull(-0.5F, 0.0F, -0.5F, EnumFacing.UP, 180.0F, itemstack.getMetadata(), gameprofile, -1, limbSwing);
                } else if (!(item instanceof ItemArmor) || ((ItemArmor)item).getEquipmentSlot() != EntityEquipmentSlot.HEAD) {
                    if (item instanceof ItemBlock) {
                        GlStateManager.translate(0.0F, 1.75F, 0.0F);
                    } else {
                        GlStateManager.translate(0.4375F, 1.75F, -0.4375F);
                    }
                        if(((EntityArmorStand) entitylivingbaseIn).getHeadRotation().getX() == 180.0f) {
                            GlStateManager.translate(0.0F, -1.75F, 0.0F);
                        }

                    GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
                    GlStateManager.scale(1.0F, -1.0F, -1.0F);

                    if (flag) {
                        GlStateManager.translate(0.0F, 0.1875F, 0.0F);
                    }

                    minecraft.getItemRenderer().renderItem(entitylivingbaseIn, itemstack, ItemCameraTransforms.TransformType.HEAD);
                }
                GlStateManager.popMatrix();
            }

        }
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}