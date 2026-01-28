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


    public void printAllCars() {
        carDAO.selectAllCars().forEach(System.out::println);
    }

    public void printElectricCars() {
        for (Car electricCar : getElectricCars()) {
            System.out.println(electricCar);
        }
    }

    private List<Car> getElectricCars() {
        List<Car> electricCars = new ArrayList<>();
        for (Car car : carDAO.selectAllCars()) {
            if (car.isElectric()) {
                electricCars.add(car);
            }
        }
        return electricCars;

    }

    public Car getCarByRegNumber(String regNumber) {
        return carDAO.selectCarByRegNumber(regNumber);
    }

    public boolean isRegNumberExist(String regNumber) {
        Car car = carDAO.selectCarByRegNumber(regNumber);
        return car != null;

    }

    public void deleteCar(Car car) {
        System.out.println(carDAO.removeCar(car));
    }
}
