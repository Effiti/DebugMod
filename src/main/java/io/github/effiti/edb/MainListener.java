package io.github.effiti.edb;

import io.github.effiti.edb.events.SendTestPacketEvent;
import io.github.effiti.edb.events.ServerJoinEvent;
import io.github.effiti.edb.network.AnalyticsPacket;
import io.github.effiti.edb.network.PacketDispatcher;
import io.github.effiti.lib.Events.EventListener;
import io.github.effiti.lib.Events.OnEvent;

public class MainListener extends EventListener {
    @OnEvent(type = SendTestPacketEvent.class, priority = 10)
    public void onSendTestPacket(SendTestPacketEvent e) {

    }
    @OnEvent(type = ServerJoinEvent.class)
    public void onServerJoin(ServerJoinEvent e)  {
        // only send analytics packet to certain servers!
        if(!ClientMod.mc.player.getServerBrand().contains("§3§4§r"))
            return;
        // TODO: send some sort of "password" from a config-file in order to allow servers to
        //  authenticate team members i.e. kick the player when their client does not send the packet or sends a wrong password
        AnalyticsPacket packet = new AnalyticsPacket("1.19.3", System.getenv("os.name"), ClientMod.mc.player.getUuid());
        PacketDispatcher.send(AnalyticsPacket.channelId, packet);


    }

}
