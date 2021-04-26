package com.github.patrick.customentity.network;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import com.github.patrick.customentity.client.CustomEntityManager;

public class MessageRegister implements IMessage {
    private int entityId;
    public MessageRegister() {

    }
    public void fromBytes(ByteBuf buf) {
        this.entityId = buf.readInt();
    }
    public void toBytes(ByteBuf buf) {

    }
    public static class Handler extends MessageHandler<MessageRegister> {
        public Handler() {

        }
        public IMessage onMessage(MessageRegister message, MessageContext context) {
            CustomEntityManager.getOrCreateInstance().createCustomEntity(message.entityId);
            return null;
        }
    }
}
