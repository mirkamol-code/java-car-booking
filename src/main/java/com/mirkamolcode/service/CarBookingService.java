package com.mirkamolcode.service;

import com.mirkamolcode.dao.CarBookingDAO;
import com.mirkamolcode.model.Car;
import com.mirkamolcode.model.CarBooking;
import com.mirkamolcode.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.mirkamolcode.model.enums.ResponseMessage.*;

public class CarBookingService {
    private final CarBookingDAO carBookingDAO;
    private final CarService carService;

    public CarBookingService(CarBookingDAO carBookingDAO, CarService carService) {
        this.carBookingDAO = carBookingDAO;
        this.carService = carService;
    }


    public void bookCar(User user, Car car) {
        CarBooking carBooking = new CarBooking(user, car);
        carBooking.setBookingId(UUID.randomUUID());
        UUID savedBookingId = carBookingDAO.saveCarBooking(carBooking);

        carService.deleteCar(car);

        System.out.println(BOOKED_CAR.getMessage() + car.getRegNumber() + FOR_USER.getMessage() + user);
        System.out.println(BOOKING_REF.getMessage() + savedBookingId);
    }

    public List<CarBooking> getUserBookedCarsByUserId(UUID id) {
        return carBookingDAO.selectAllBookings().stream()
                .filter(carBooking ->
                        carBooking.getUser().getId().equals(id))
                .toList();
    }


    public boolean deleteCarBooking(UUID carBookingId) {
        return carBookingDAO.deleteCarBooking(carBookingId);
    }


    public boolean isCarBookingExist(UUID id) {
        return carBookingDAO.getCarBookingById(id) != null;
    }

    public boolean isCarBookingListEmpty() {
        return carBookingDAO.selectAllBookings().isEmpty();
    }
}
