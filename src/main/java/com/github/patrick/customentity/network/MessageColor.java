package com.github.patrick.customentity.network;

import com.github.patrick.customentity.client.CustomEntity;
import com.github.patrick.customentity.client.CustomEntityManager;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageColor implements IMessage {
    private int entityId;
    private byte colorR;
    private byte colorG;
    private byte colorB;
    private int duration;
    public MessageColor() {

    }
    public void fromBytes(ByteBuf buf) {
        this.entityId = buf.readInt();
        this.colorR = buf.readByte();
        this.colorG = buf.readByte();
        this.colorB = buf.readByte();
        this.duration = buf.readInt();
    }
    public void toBytes(ByteBuf buf) {

    }
    public static class Handler extends MessageHandler<MessageColor> {
        public Handler() {

        }
        public IMessage onMessage(MessageColor message, MessageContext context) {
            CustomEntityManager manager = CustomEntityManager.instance;
            if(manager != null) {
                CustomEntity custom = manager.getCustomEntity(message.entityId);
                if(custom != null) {
                    custom.setColor(message.colorR & 255, message.colorG & 255, message.colorB & 255, message.duration * 25000000);
                }
            }
            return null;
        }
    }
}
