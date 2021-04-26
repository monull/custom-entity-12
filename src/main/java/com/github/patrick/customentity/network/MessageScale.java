package com.github.patrick.customentity.network;

import com.github.patrick.customentity.client.CustomEntity;
import com.github.patrick.customentity.client.CustomEntityManager;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageScale implements IMessage {
    private int entityId;
    private float scaleX;
    private float scaleY;
    private float scaleZ;
    private int duration;
    public MessageScale() {

    }
    public void fromBytes(ByteBuf buf) {
        this.entityId = buf.readInt();
        this.scaleX = buf.readFloat();
        this.scaleY = buf.readFloat();
        this.scaleZ = buf.readFloat();
        this.duration = buf.readInt();
    }
    public void toBytes(ByteBuf buf) {

    }
    public static class Handler extends MessageHandler<MessageScale> {
        public Handler() {

        }
        public IMessage onMessage(MessageScale message, MessageContext context) {
            CustomEntityManager manager = CustomEntityManager.instance;
            if(manager != null) {
                CustomEntity custom = manager.getCustomEntity(message.entityId);
                if(custom != null) {
                    custom.setScale(message.scaleX, message.scaleY, message.scaleZ, message.duration * 500000000);
                }
            }
            return null;
        }
    }
}
