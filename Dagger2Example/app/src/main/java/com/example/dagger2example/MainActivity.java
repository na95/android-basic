package com.example.dagger2example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    /** For Constructor Injecttion **/
    // private Car car;

    /** For Field Injection **/
    @Inject Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CarComponent component = DaggerCarComponent.create();


        /** For Constructor Injection **/
        car = component.getCar();

        /** For Field Injection **/
        //component.inject(this);

        car.drive();
    }
}
