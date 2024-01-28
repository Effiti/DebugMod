package io.github.effiti.edb.mixin;

import io.github.effiti.edb.ClientMod;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin {

    @Inject(at = @At("HEAD"), method = "handlePacket", cancellable = true)
    private static <T extends PacketListener> void handlePacket(Packet<T> packet, PacketListener listener, CallbackInfo ci) {
        /*if(ClientMod.shouldSendPacketSendMessages())
            ClientMod.sendMessage("SEND: " + packet.getClass().toString());*/
        if(packet instanceof GameMessageS2CPacket g &&
                g.content().getString().startsWith("{{")
        ) {
            long t_1 = System.currentTimeMillis();
            long t_0 = Long.parseLong(g.content().getString().substring(2, 12));
            ClientMod.sendMessage("delta t : "  + (t_1) );
        }
    }

}
