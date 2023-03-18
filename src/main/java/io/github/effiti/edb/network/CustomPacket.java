package io.github.effiti.edb.network;

import net.minecraft.network.PacketByteBuf;

public interface CustomPacket {
    PacketByteBuf serialize();
}
