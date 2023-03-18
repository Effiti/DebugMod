package io.github.effiti.lib.Events.example;

import io.github.effiti.edb.events.PlayerDamageEvent;
import io.github.effiti.lib.Events.EventManager;
import io.github.effiti.edb.events.RenderEvent;

public class Main {

    public static void main(String[] args){
        EventManager manager =  new EventManager();
        TestListener l = new TestListener();
        manager.register(l);
        manager.dispatch(new RenderEvent(false));
        manager.dispatch(new PlayerDamageEvent(null, 1.0f));
    }

}
