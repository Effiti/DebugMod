package io.github.effiti.edb;

import io.github.effiti.edb.events.SendTestPacketEvent;
import io.github.effiti.edb.events.ServerJoinEvent;
import io.github.effiti.edb.network.AnalyticsPacket;
import io.github.effiti.edb.network.PacketDispatcher;
import io.github.effiti.lib.Events.EventListener;
import io.github.effiti.lib.Events.OnEvent;

import java.util.Objects;

public class MainListener extends EventListener {
    @OnEvent(type = SendTestPacketEvent.class, priority = 10)
    public void onSendTestPacket(SendTestPacketEvent e) {

    }
    @OnEvent(type = ServerJoinEvent.class)
    public void onServerJoin(ServerJoinEvent e)  {
        return;
    }

}
