package io.github.effiti.edb.events;

import io.github.effiti.lib.Events.Event;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;

public class ServerJoinEvent implements Event {
    public final GameJoinS2CPacket packet;

    public ServerJoinEvent(GameJoinS2CPacket packet) {
        this.packet = packet;
    }
}
