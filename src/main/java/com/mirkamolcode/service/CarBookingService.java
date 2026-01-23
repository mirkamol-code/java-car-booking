package com.mirkamolcode.service;

import com.mirkamolcode.dao.CarBookingDAO;
import com.mirkamolcode.model.Car;
import com.mirkamolcode.model.CarBooking;
import com.mirkamolcode.model.User;

import java.util.UUID;

import static com.mirkamolcode.model.enums.ResponseMessage.*;

public class CarBookingService {
    private final CarBookingDAO carBookingDAO;
    private final CarService carService;
    private final UserService userService;

    public CarBookingService() {
        this.carBookingDAO = new CarBookingDAO();
        this.carService = new CarService();
        this.userService = new UserService();
    }


    public void bookCar(User user, Car car) {
        CarBooking carBooking = new CarBooking(user, car);
        carBooking.setBookingId(UUID.randomUUID());
        UUID savedBookingId = carBookingDAO.saveCarBooking(carBooking);

        carService.deleteCar(car);

        System.out.println(BOOKED_CAR.getMessage() + car.getRegNumber() + FOR_USER.getMessage() + user);
        System.out.println(BOOKING_REF.getMessage() + savedBookingId);
    }


    public void getAllBookings() {
        CarBooking[] carBookings = carBookingDAO.selectAllBookings();
        if (carBookings[0] != null) {
            for (CarBooking carBooking : carBookings) {
                if (carBooking != null) {
                    System.out.println(carBooking);
                }
            }
        } else {
            System.out.println(NO_BOOKINGS.getMessage());
        }
    }

    public void getUserBookedCarsByUserId(UUID id) {
        User inputUserId = userService.getUserById(id);
        CarBooking[] carBookings = carBookingDAO.selectAllBookings();
        int countUserBookings = 0;
        if (userService.isUserExist(id)) {
            for (CarBooking carBooking : carBookings) {
                if (carBooking == null) {
                    if (countUserBookings == 0) {
                        System.out.println(X_USER.getMessage() + inputUserId + NOT_BOOKED.getMessage());
                    }
                    break;
                } else {
                    if (carBooking.getUser().equals(inputUserId)) {
                        System.out.println(carBooking);
                        countUserBookings++;
                    }
                }
            }
        } else {
            System.out.println(NOT_FOUND.getMessage());
        }
    }

    public boolean deleteCarBooking(UUID carBookingId) {
        return carBookingDAO.deleteCarBooking(carBookingId);
    }

    public boolean isCarBookingExist(UUID id) {
        try {
            for (CarBooking carBooking : carBookingDAO.selectAllBookings()) {
                if (carBooking.getBookingId().equals(id)) {
                    return true;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.print(INVALID_OPTION.getMessage() + " ");
        }

        return false;
    }

    public boolean isCarBookingArrayEmpty() {
        CarBooking[] carBookings = carBookingDAO.selectAllBookings();
        return carBookings[0] != null;

    }
}
