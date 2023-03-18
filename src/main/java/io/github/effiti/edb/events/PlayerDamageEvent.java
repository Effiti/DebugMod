package io.github.effiti.edb.events;

import io.github.effiti.lib.Events.Event;
import net.minecraft.entity.damage.DamageSource;

public class PlayerDamageEvent implements Event {
    public final DamageSource source;
    public final float amount;

    public PlayerDamageEvent(DamageSource source, float amount) {
        this.source = source;
        this.amount = amount;
    }
}
