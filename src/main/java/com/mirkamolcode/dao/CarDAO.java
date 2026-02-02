package com.mirkamolcode.dao;

import com.mirkamolcode.model.Car;
import com.mirkamolcode.model.enums.Brand;

import java.util.*;

import static com.mirkamolcode.model.enums.ResponseMessage.CAR_NOT_FOUND;

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

    public Optional<Car> selectCarByRegNumber(String regNumber) {
        return carList.stream()
                .filter(car -> car.getRegNumber().equals(regNumber))
                .findFirst();
    }

    public boolean removeCarByRegNumber(String regNum) {
        Car car = selectCarByRegNumber(regNum)
                .orElseThrow(
                        () -> new NoSuchElementException(CAR_NOT_FOUND.getMessage()));
        return carList.remove(car);
    }
}
