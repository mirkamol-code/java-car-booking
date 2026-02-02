package com.mirkamolcode.service;

import com.mirkamolcode.dao.CarDAO;
import com.mirkamolcode.model.Car;

import java.util.List;
import java.util.NoSuchElementException;

import static com.mirkamolcode.model.enums.ResponseMessage.*;

public class CarService {
    private final CarDAO carDAO;

    public CarService(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    public List<Car> getAllCars() {
        if (carDAO.selectAllCars().isEmpty()) {
            throw new NoSuchElementException(NO_CARS.getMessage());

        }
        return carDAO.selectAllCars();
    }

    public List<Car> getElectricCars() {
        return carDAO.selectAllCars().stream()
                .filter(Car::isElectric)
                .toList();
    }

    public Car getCarByRegNumber(String regNumber) {
        if (carDAO.selectCarByRegNumber(regNumber).isEmpty()) {
            throw new NoSuchElementException(CAR_NOT_FOUND.getMessage());

        }
        return carDAO.selectCarByRegNumber(regNumber).get();
    }

    public void deleteCar(Car car) {
        System.out.println(carDAO.removeCar(car));
    }
}
