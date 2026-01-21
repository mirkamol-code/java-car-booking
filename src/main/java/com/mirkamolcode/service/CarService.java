package com.mirkamolcode.service;

import com.mirkamolcode.dao.CarDAO;
import com.mirkamolcode.model.Car;

public class CarService {
    private final CarDAO carDAO;

    public CarService() {
        this.carDAO = new CarDAO();
    }

    public void getAllCars() {
        for (Car car : carDAO.selectAllCars()) {
            if (car != null) {
                System.out.println(car);
            }
        }
    }

    public void getElectricCars() {
        for (Car car : carDAO.selectAllCars()) {
            if (car != null && car.isElectric()) {
                System.out.println(car);
            }
        }
    }

    public Car getCarByRegNumber(String regNumber) {
        return carDAO.selectCarByRegNumber(regNumber);
    }

    public boolean isRegNumberExist(String regNumber) {
        Car car = carDAO.selectCarByRegNumber(regNumber);
        return car != null;

    }

    public void deleteCar(Car car) {
        carDAO.removeCar(car);
    }
}
