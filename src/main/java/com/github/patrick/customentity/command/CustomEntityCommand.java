package com.github.patrick.customentity.command;

import com.github.patrick.customentity.CustomEntityMod;
import com.github.patrick.customentity.client.CustomEntity;
import com.github.patrick.customentity.client.CustomEntityManager;
import org.jetbrains.annotations.NotNull;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

import java.util.Iterator;

public class CustomEntityCommand extends CommandBase {
    public CustomEntityCommand() {

    }
    @NotNull
    public String getName() { return "ce"; }
    @NotNull
    public String getUsage(@NotNull ICommandSender sender) { return "/ce"; }
    public void execute(@NotNull MinecraftServer server, @NotNull ICommandSender sender, @NotNull String[] args) {
        CustomEntityMod.logger.info("Command CustomEntity Executed");
        if(sender instanceof EntityPlayer) {
            CustomEntityMod.logger.info("Command CustomEntity Executed by player");
            EntityPlayer player = (EntityPlayer) sender;
            CustomEntityManager manager = CustomEntityManager.getOrCreateInstance();
            Iterator var6 = player.world.getEntitiesWithinAABBExcludingEntity((Entity) null, player.getEntityBoundingBox().expand(10.0D, 10.0D, 10.0D)).iterator();
            while(var6.hasNext()) {
                Entity entity = (Entity) var6.next();
                int id = entity.getEntityId();
                manager.createCustomEntity(id);
                CustomEntity custom = manager.getCustomEntity(id);
                if(custom != null) {
                    custom.setScale(5.0F, 5.0F, 5.0F, 10);
                }
            }
        }
    }
}
