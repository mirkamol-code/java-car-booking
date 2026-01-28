package com.mirkamolcode.dao;

import com.mirkamolcode.model.Car;
import com.mirkamolcode.model.enums.Brand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CarDAO {
    private static List<Car> carList = new ArrayList<>(
            Arrays.asList(
                    new Car("1111", 28_000.0, Brand.AUDI, false),
                    new Car("2222", 25_000.0, Brand.MERCEDES, false),
                    new Car("3333", 22_000.0, Brand.BMW, false),
                    new Car("4444", 30_000.0, Brand.TESLA, true),
                    new Car("5555", 32_000.0, Brand.BMW, true)
            )
    );

    public List<Car> selectAllCars() {
        return carList;
    }

    public Car selectCarByRegNumber(String regNumber) {
        for (Car car : carList) {
            String carRegNumFromArray = car.getRegNumber();
            if (carRegNumFromArray.equals(regNumber)) {
                return car;
            }
        }
        return null;
    }

    public boolean removeCar(Car car) {
        return carList.remove(car);
    }
}
