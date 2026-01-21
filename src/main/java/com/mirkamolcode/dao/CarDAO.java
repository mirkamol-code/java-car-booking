package com.mirkamolcode.dao;

import com.mirkamolcode.model.Car;
import com.mirkamolcode.model.enums.Brand;

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

    public void removeCar(Car bookedCar) {
        var indexToRemove = 0;
        for (int i = 0; i < cars.length; i++) {
            if (cars[i].getRegNumber().equals(bookedCar.getRegNumber())) {
                indexToRemove = i;
                Car[] shrunkedCarArray = new Car[cars.length - 1];

                System.arraycopy(cars, 0, shrunkedCarArray, 0, cars.length-1);
                System.arraycopy(cars, indexToRemove + 1, shrunkedCarArray, indexToRemove, cars.length - indexToRemove - 1);

                cars = shrunkedCarArray;
            }
        }
    }
}
