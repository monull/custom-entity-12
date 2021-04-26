package com.github.patrick.customentity.network;

import com.github.patrick.customentity.network.MessageRegister.Handler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketDispatcher {
    private static byte packetId;
    private static final SimpleNetworkWrapper dispatcher;
    public PacketDispatcher() {

    }
    public static void registerPackets() {
        registerMessage(Handler.class, MessageRegister.class);
        registerMessage(com.github.patrick.customentity.network.MessageUnregister.Handler.class, MessageUnregister.class);
        registerMessage(com.github.patrick.customentity.network.MessageColor.Handler.class, MessageColor.class);
        registerMessage(com.github.patrick.customentity.network.MessageScale.Handler.class, MessageScale.class);
        registerMessage(com.github.patrick.customentity.network.MessageColorAndScale.Handler.class, MessageColorAndScale.class);
    }
    private static void registerMessage(Class handlerClass, Class messageClass) {
        SimpleNetworkWrapper dispatcher = PacketDispatcher.dispatcher;
        byte packetId = PacketDispatcher.packetId;
        PacketDispatcher.packetId = (byte) (packetId + 1);
        dispatcher.registerMessage(handlerClass, messageClass, packetId, Side.CLIENT);
    }
    static {
        dispatcher = NetworkRegistry.INSTANCE.newSimpleChannel("CustomEntity");
    }
}
