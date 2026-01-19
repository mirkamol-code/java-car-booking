package com.mirkamolcode.service;

import com.mirkamolcode.dao.CarBookingDAO;
import com.mirkamolcode.model.Car;
import com.mirkamolcode.model.CarBooking;
import com.mirkamolcode.model.User;

import java.util.UUID;

public class CarBookingService {
    private CarBookingDAO carBookingDAO;
    private CarService carService;

    public CarBookingService() {
        this.carBookingDAO = new CarBookingDAO();
        this.carService = new CarService();
    }


    public UUID bookCar(User user, Car car) {
        CarBooking carBooking = new CarBooking(user, car);
        UUID savedBookingId = carBookingDAO.saveCarBooking(carBooking);
        carService.deleteCarFromUnbookedCarArray(car);
        return savedBookingId;
    }


    public void getAllBookings() {
        for (CarBooking carBooking : carBookingDAO.selectAllCarBookings()) {
            System.out.println(carBooking);
        }
    }

    public void getUserBookedCarsByUserId(String uuid) {
        for (CarBooking carBooking : carBookingDAO.selectUserBookedCarByUserId(uuid)) {
            System.out.println(carBooking);
        }
    }
}
