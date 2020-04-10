package com.example.dagger2example;

import dagger.Component;

@Component
public interface CarComponent {

    // Constructor
    Car getCar();

    // For field in the class
    void inject(MainActivity mainActivity);
}
