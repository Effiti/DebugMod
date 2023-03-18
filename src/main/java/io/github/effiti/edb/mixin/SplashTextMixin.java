package io.github.effiti.edb.mixin;
import net.minecraft.client.resource.SplashTextResourceSupplier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Mixin(SplashTextResourceSupplier.class)
public class SplashTextMixin {
    @Shadow @Final private List<String> splashTexts;
    private final Random random = new Random();

    @Inject(method = "get", at = @At("HEAD"))
    private void onApply(CallbackInfoReturnable<String> cir) {
        splashTexts.addAll(getSplashes());
    }

    private static List<String> getSplashes() {
        return Arrays.asList(
            "Powered by Effiti's Debug Mod!"
        );
    }

}