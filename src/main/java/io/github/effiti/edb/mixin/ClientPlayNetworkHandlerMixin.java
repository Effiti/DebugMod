package io.github.effiti.edb.mixin;

import com.mojang.authlib.GameProfile;
import io.github.effiti.edb.ClientMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerListS2CPacket;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class ClientPlayNetworkHandlerMixin {

    @Shadow @Final private GameProfile profile;

    @Inject(method = "onEntitySpawn", at = @At(value =  "TAIL"))
    public void onEntitySpawn(EntitySpawnS2CPacket packet, CallbackInfo ci) {
        if(!ClientMod.shouldSendEntityMessages())
            return;
        ClientMod.sendMessage("received EntitySpawn! Entity of ID " + packet.getEntityId() + " has Type: "  + packet.getEntityType().getName().getString()
                + " @ X"  + (int)packet.getX()
                +   " Y" + (int)packet.getY()
                +   " Z" + (int)packet.getZ());

    }

    @Inject(method = "onPlayerList", at = @At(value = "TAIL"))
    public void onPlayerList(PlayerListS2CPacket packet, CallbackInfo ci) {
        if(!ClientMod.shouldSendJoinMessages())
            return;

        for (PlayerListS2CPacket.Entry entry : packet.getEntries().stream().distinct().toList()) {
            for (var x : packet.getActions()) {
                switch (x) {
                    case ADD_PLAYER -> {
                        ClientMod.sendMessage("received PlayerListS2CPacket: + " + entry.profile().getName());
                    }
                    case UPDATE_LISTED-> {
                        ClientMod.sendMessage("received PlayerListS2CPacket: - " + (entry.profileId().toString().substring(0,5)) + "...");
                    }
                    case UPDATE_LATENCY, UPDATE_DISPLAY_NAME -> {
                        ClientMod.sendMessage(entry.profile().getName() + " now has displayname " + entry.displayName().getString());
                    }
                    case UPDATE_GAME_MODE -> {
                        ClientMod.sendMessage("received PlayerListS2CPacket: GAMEMODE " + (entry.profileId().toString().substring(0,5)) + " -> " + entry.gameMode().getName());
                    }
                }
            }
        }
    }
}
