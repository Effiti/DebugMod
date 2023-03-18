package io.github.effiti.edb.mixin;


import io.github.effiti.lib.Events.EventManager;
import io.github.effiti.edb.events.RenderEvent;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(method = "render", at = @At("HEAD"))
    private void render(boolean tick, CallbackInfo ci) {
        EventManager.INSTANCE.dispatch(new RenderEvent(tick));
    }

}