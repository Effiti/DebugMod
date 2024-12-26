package io.github.effiti.edb.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public class PacketDispatcher {
    public static void send(Identifier channel, CustomPacket packet) {
        //ClientPlayNetworking.send(CustomPayload.CodecFactorychannel, packet.serialize());
    }
}
