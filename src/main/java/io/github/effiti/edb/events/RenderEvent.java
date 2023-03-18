package io.github.effiti.edb.events;

import io.github.effiti.lib.Events.Event;

public class RenderEvent implements Event {
    public boolean isTick;
    public RenderEvent(boolean tick){
        this.isTick = tick;
    }
}
