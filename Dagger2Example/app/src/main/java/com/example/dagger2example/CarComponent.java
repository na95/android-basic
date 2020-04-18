package com.example.dagger2example;

import dagger.Component;

@Component
public interface CarComponent {

    // Constructor - to get a car from the component
    Car getCar();

    // For field in the class - to pass object (eg. a car) into a field of a class
    void inject(MainActivity mainActivity);
}
