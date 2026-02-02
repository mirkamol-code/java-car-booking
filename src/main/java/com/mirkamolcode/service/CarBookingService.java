package com.mirkamolcode.service;

import com.mirkamolcode.dao.CarBookingDAO;
import com.mirkamolcode.model.Car;
import com.mirkamolcode.model.CarBooking;
import com.mirkamolcode.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import static com.mirkamolcode.model.enums.ResponseMessage.*;

public class CarBookingService {
    private final CarBookingDAO carBookingDAO;
    private final CarService carService;
    private final UserService userService;

    public CarBookingService(CarBookingDAO carBookingDAO, CarService carService, UserService userService) {
        this.carBookingDAO = carBookingDAO;
        this.carService = carService;
        this.userService = userService;
    }

    public List<CarBooking> getAllBookings() {
        return carBookingDAO.selectAllBookings();
    }

    public void bookCar(String carRegNumber, UUID userId) {
        Car car = carService.getCarByRegNumber(carRegNumber);
        User user = userService.getUserById(userId);

        CarBooking carBooking = new CarBooking(user, car);
        carBooking.setBookingId(UUID.randomUUID());
        UUID savedBookingId = carBookingDAO.saveCarBooking(carBooking);

        carService.deleteCar(carRegNumber);

        System.out.println(BOOKED_CAR.getMessage() + car.getRegNumber() + FOR_USER.getMessage() + user);
        System.out.println(BOOKING_REF.getMessage() + savedBookingId);
    }

    public List<CarBooking> getUserBookedCarsByUserId(UUID userId) {
        User user = userService.getUserById(userId);
        return carBookingDAO.selectAllBookings()
                .stream()
                .filter(carBooking ->
                        carBooking.getUser().equals(user))
                .toList();
    }


    public boolean deleteCarBooking(UUID carBookingId) {
        if (carBookingDAO.selectAllBookings().isEmpty()) {
            throw new NoSuchElementException(NO_BOOKINGS.getMessage());

        } else if (carBookingDAO.getCarBookingById(carBookingId).isEmpty()) {
            throw new NoSuchElementException(BOOKING_ID_NOT_FOUND.getMessage());

        }
        return carBookingDAO.deleteCarBooking(carBookingId);
    }
}
