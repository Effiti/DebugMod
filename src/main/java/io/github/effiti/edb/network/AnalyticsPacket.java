package io.github.effiti.edb.network;

import io.github.effiti.edb.ClientMod;
import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

import java.util.UUID;

public class AnalyticsPacket implements CustomPacket{

    public final String mcVersion;
    public final String operatingSystem;
    public final UUID uuid;
    public static final Identifier channelId = Identifier.of(ClientMod.MOD_NAME, "analytics");

    public AnalyticsPacket(String mcVersion, String operatingSystem, UUID uuid) {
        this.mcVersion = mcVersion;
        this.operatingSystem = operatingSystem;
        this.uuid = uuid;
    }


    public PacketByteBuf serialize()  {
        PacketByteBuf packet = new PacketByteBuf(Unpooled.buffer());
        packet.writeString(mcVersion);
        packet.writeString(operatingSystem);
        packet.writeUuid(uuid);
        return packet;
    }

}
