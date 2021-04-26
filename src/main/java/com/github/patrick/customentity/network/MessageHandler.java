package com.github.patrick.customentity.network;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;

public abstract class MessageHandler<T extends IMessage> implements IMessageHandler<T, IMessage> {
    public MessageHandler() {

    }
}
