package com.example.dagger2example;

import android.util.Log;

import javax.inject.Inject;

public class Car {

    private static final String TAG = "Car";

    @Inject Engine engine; // Field injection
    private Wheels wheels;

    @Inject // Constructor injection
    public Car(Engine engine, Wheels wheels) {
        this.engine = engine;
        this.wheels = wheels;
    }

    public void drive() {
        Log.i(TAG, "driving...");
    }

    @Inject // Method injection
    public void enableRemote(Remote remote){
        remote.setListener(this);
    }
}
