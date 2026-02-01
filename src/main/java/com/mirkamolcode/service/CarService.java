package com.mirkamolcode.service;

import com.mirkamolcode.dao.CarDAO;
import com.mirkamolcode.model.Car;

import java.util.ArrayList;
import java.util.List;

public class CarService {
    private final CarDAO carDAO;

    public CarService(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    public List<Car> getElectricCars() {
        return carDAO.selectAllCars().stream()
                .filter(Car::isElectric)
                .toList();
    }

    public Car getCarByRegNumber(String regNumber) {
        return carDAO.selectCarByRegNumber(regNumber);
    }

    public boolean isRegNumberExist(String regNumber) {
         return carDAO.selectCarByRegNumber(regNumber) !=null;
    }

    public void deleteCar(Car car) {
        System.out.println(carDAO.removeCar(car));
    }
}
