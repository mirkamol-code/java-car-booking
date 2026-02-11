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
        if (carDAO.selectAllCars() == null || carDAO.selectAllCars().isEmpty()) {
            throw new NoSuchElementException(NO_CARS.getMessage());
        }

        return carDAO.selectAllCars();
    }

    public List<Car> getElectricCars() {
        if (carDAO.selectAllCars() == null || carDAO.selectAllCars().isEmpty()) {
            throw new NoSuchElementException(NO_ELECTRIC_CARS.getMessage());

        }
       return   carDAO.selectAllCars().stream()
                .filter(Car::isElectric)
                .toList();
    }

    public Car getCarByRegNumber(String regNumber) {
        return carDAO.selectCarByRegNumber(regNumber)
                .orElseThrow(() -> new NoSuchElementException(CAR_NOT_FOUND.getMessage()));
    }

    public void deleteCar(String regNum) {
       carDAO.selectCarByRegNumber(regNum)
                .orElseThrow(() -> new NoSuchElementException(CAR_NOT_FOUND.getMessage()));
        carDAO.removeCarByRegNumber(regNum);
    }
}
