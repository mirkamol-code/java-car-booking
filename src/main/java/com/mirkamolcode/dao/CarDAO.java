package com.mirkamolcode.dao;

import com.mirkamolcode.model.Car;
import com.mirkamolcode.model.enums.Brand;

import java.util.UUID;

public class CarDAO {
    private static Car[] cars;
    static {
        cars = new Car[]{
                new Car("1111", 28_000.0, Brand.AUDI, false),
                new Car("2222", 25_000.0, Brand.MERCEDES, false),
                new Car("3333", 22_000.0, Brand.BMW, false),
                new Car("4444", 30_000.0, Brand.TESLA, true),
                new Car("5555", 32_000.0, Brand.BMW, true),

        };
    }

    public Car[] selectAllCars() {
        return cars;
    }

    public Car selectCarByRegNumber(String regNumber) {
        for (Car car : cars) {
            String carRegNumFromArray = car.getRegNumber();
            if (carRegNumFromArray.equals(regNumber)) {
                return car;
            }
        }
        return null;
    }

    public boolean removeCar(Car car) {
        int index = findCarIndex(car.getRegNumber());
        if (index == -1) {
            return false;
        }

        removeAt(index);
        return true;
    }

    private int findCarIndex(String carRegNumber) {
        int index = -1;

        for (int i = 0; i < cars.length; i++) {
            if (cars[i].getRegNumber().equals(carRegNumber)) {
                index = i;
                break;
            }
        }
        return index;
    }

    private void removeAt(int index) {
        for (int i = index; i < cars.length - 1; i++) {
            cars[i] = cars[i + 1];
        }

        cars[cars.length-1] = null;
    }
}
