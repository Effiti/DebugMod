package io.github.effiti.edb.mixin;

import io.github.effiti.edb.ClientMod;
import io.github.effiti.edb.events.ServerJoinEvent;
import io.github.effiti.lib.Events.EventManager;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin {

    @Inject(at = @At("HEAD"), method = "handlePacket", cancellable = true)
    private static <T extends PacketListener> void handlePacket(Packet<T> packet, PacketListener listener, CallbackInfo ci) {
        if(ClientMod.shouldSendPacketSendMessages())
            ClientMod.sendMessage("SEND: " + packet.getClass().toString());
    }

    @Inject(at = @At("HEAD"), method = "send(Lnet/minecraft/network/Packet;)V", cancellable = true)
    public void send(Packet<?> packet, CallbackInfo ci) {
        if(ClientMod.shouldSendPacketSendMessages())
            ClientMod.sendMessage("SEND: " + packet.getClass().toString());
        if(packet instanceof GameJoinS2CPacket packet1) {
            EventManager.INSTANCE.dispatch(new ServerJoinEvent(packet1));

        }
    }
}
