package com.example.projectAPI.events;

import lombok.Getter;

public abstract class BaseEvent<T> {
    @Getter public T id;

    public BaseEvent(T id) {
        this.id = id;
    }

}
