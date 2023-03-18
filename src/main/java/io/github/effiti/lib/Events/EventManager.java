package io.github.effiti.lib.Events;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
/*
DISCLAIMER:
DO NOT TOUCH!
this works.
 */
public class EventManager {
    public static EventManager INSTANCE;
    public static List<EventListener> listeners;

    public EventManager() {
        listeners = new ArrayList<>();
        INSTANCE = this;
    }

    public <T extends Event> void dispatch(T e) {
        List<EventMethod> methods = new ArrayList<>();
        listeners.forEach(eventListener -> {
            for (Method method : eventListener.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(OnEvent.class) && (method.getAnnotation(OnEvent.class).type()) == (e.getClass())) {
                    methods.add(new EventMethod(method, eventListener));
                }
            }
        });
        methods.sort(Comparator.comparingInt(method -> method.method.getAnnotation(OnEvent.class).priority()));
        methods.forEach(method -> {
            method.method.setAccessible(true);
            try {
                method.method.invoke(method.listener, e);
            } catch (IllegalAccessException | InvocationTargetException ex) {
                ex.printStackTrace();
            }
        });

    }

    public <T> void register(T listener) {
        listeners.add((EventListener) listener);
    }
}

