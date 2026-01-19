package com.mirkamolcode.dao;

import com.mirkamolcode.model.Car;
import com.mirkamolcode.model.CarBooking;
import com.mirkamolcode.model.enums.Brand;

import java.util.Arrays;

public class CarDAO {
    private static Car[] cars;
    private static int nextAvailableSlot = 0;
    private static Car[] electricCars;
    private static final int CAPACITY = 1;

    static {
        cars = new Car[]{
                new Car(1111, 28_000.0, Brand.AUDI, false),
                new Car(2222, 25_000.0, Brand.MERCEDES, false),
                new Car(3333, 22_000.0, Brand.BMW, false),
                new Car(4444, 30_000.0, Brand.TESLA, true),
                new Car(5555, 32_000.0, Brand.BMW, true),

        };

        electricCars = new Car[CAPACITY];
    }

    public Car[] selectAllCars() {
        return cars;
    }

    public Car[] selectElectricCars() {
        for (Car car : cars) {
            if (car.isElectric()) {
                resizeIfNeeded();
                electricCars[nextAvailableSlot] = car;
                ++nextAvailableSlot;
            }
        }
        return electricCars;
    }

    public void resizeIfNeeded() {
        if (nextAvailableSlot + 1 > CAPACITY) {
            Car[] newArray = new Car[CAPACITY + 1];
            System.arraycopy(electricCars, 0, newArray, 0, electricCars.length);
            electricCars = newArray;
        }
    }

    public Car selectCarByRegNumber(int regNumber) {
        for (Car car : cars) {
            if (car.getRegNumber() == regNumber) {
                return car;
            }
        }
        return null;
    }

    public void removeCarFromUnbookedCarArray(Car bookedCar) {
        var indexToRemove = 0;
        for (int i = 0; i < cars.length; i++) {
            if (cars[i].getRegNumber() == bookedCar.getRegNumber()) {
                indexToRemove = i;
                Car[] shrunkedCarArray = new Car[cars.length - 1];
                System.arraycopy(cars, 0, shrunkedCarArray, 0, cars.length);
                System.arraycopy(cars, indexToRemove + 1, shrunkedCarArray, indexToRemove, cars.length - indexToRemove - 1);
                cars = shrunkedCarArray;
                --nextAvailableSlot;
            }
        }
    }
}
