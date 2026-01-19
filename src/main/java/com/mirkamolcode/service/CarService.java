package com.mirkamolcode.service;

import com.mirkamolcode.dao.CarDAO;
import com.mirkamolcode.model.Car;

public class CarService {
    private CarDAO carDAO;

    public CarService() {
        this.carDAO = new CarDAO();
    }

    public void getAllCars() {
        for (Car car : carDAO.selectAllCars()) {
            if(car != null){
                System.out.println(car);
            }
        }
    }

    public void getElectricCars() {
        for (Car car : carDAO.selectElectricCars()) {
            if(car != null) {
                System.out.println(car);
            }
        }
    }

    public Car getCarByRegNumber(int regNumber) {
        return carDAO.selectCarByRegNumber(regNumber);
    }

    public void deleteCarFromUnbookedCarArray(Car car){
        carDAO.removeCarFromUnbookedCarArray(car);
    }
}
