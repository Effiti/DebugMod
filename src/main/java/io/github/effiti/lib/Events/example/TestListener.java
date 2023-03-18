package io.github.effiti.lib.Events.example;

import io.github.effiti.edb.events.PlayerDamageEvent;
import io.github.effiti.lib.Events.EventListener;
import io.github.effiti.lib.Events.OnEvent;
import io.github.effiti.edb.events.RenderEvent;

public class TestListener extends EventListener {
    @OnEvent(type = RenderEvent.class)
    public void sayHi(RenderEvent e){
        System.out.println("Hi, "+ e.isTick);

    }
    @OnEvent(type= PlayerDamageEvent.class)
    public void sayHello(PlayerDamageEvent e){
        System.out.println("Helllauasd");
    }

}
