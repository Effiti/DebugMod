package io.github.effiti.edb.events;

import com.mojang.brigadier.context.CommandContext;

public class SendTestPacketEvent {
    public final CommandContext context;

    public SendTestPacketEvent(CommandContext context) {
        this.context = context;
    }
}
