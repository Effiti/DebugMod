package io.github.effiti.lib.Events;

import java.lang.reflect.Method;

public class EventMethod<T> {
    public final Method method;
    public final T listener;

    public EventMethod(Method method, T listener) {
        this.method = method;
        this.listener = listener;
    }
}
