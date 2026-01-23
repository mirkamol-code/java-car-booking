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

    public Car[] getElectricCars() {
        int count = 0;
        Car[] cars = carDAO.selectAllCars();
        for (Car car : cars) {
            if (car != null && car.isElectric()) {
                count++;
            }
        }
        Car[] electricCars = new Car[count];
        int index = 0;
        for (int i = 0; i < cars.length; i++) {
            Car car = cars[i];
            if (car != null && car.isElectric()) {
                electricCars[index] = car;
                index++;
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
        carDAO.removeCar(car);
    }
}
