package com.github.patrick.customentity.network;

import com.github.patrick.customentity.client.CustomEntityManager;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageUnregister implements IMessage {
    private int entityId;
    public MessageUnregister() {

    }
    public void fromBytes(ByteBuf buf) {
        this.entityId = buf.readInt();
    }
    public void toBytes(ByteBuf buf) {

    }
    public static class Handler extends MessageHandler<MessageUnregister> {
        public Handler() {

        }
        public IMessage onMessage(MessageUnregister message, MessageContext context) {
            CustomEntityManager.getOrCreateInstance().removeCustomEntity(message.entityId);
            return null;
        }
    }
}
