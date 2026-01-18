package com.mirkamolcode.dao;

import com.mirkamolcode.model.Car;
import com.mirkamolcode.model.enums.Brand;

public class CarDAO {
    private static Car[] cars;
    private static int nextAvailableSlot = 0;

    static {
        cars = new Car[]{
                new Car(1111, 28_000.0, Brand.AUDI,false),
                new Car(2222, 25_000.0, Brand.MERCEDES,false),
                new Car(3333, 22_000.0, Brand.BMW,false),
                new Car(4444, 30_000.0, Brand.TESLA,true),
                new Car(5555, 32_000.0, Brand.BMW,true),

        };
    }

    public Car[] selectAllCars(){
        return cars;
    }
}
