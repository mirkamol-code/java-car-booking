package com.mirkamolcode.service;

import com.mirkamolcode.dao.CarBookingDAO;
import com.mirkamolcode.dao.CarDAO;
import com.mirkamolcode.model.Car;
import com.mirkamolcode.model.CarBooking;
import com.mirkamolcode.model.User;

import java.util.UUID;

import static com.mirkamolcode.model.enums.ResponseMessage.*;

public class CarBookingService {
    private CarBookingDAO carBookingDAO;
    private CarService carService;
    private UserService userService;

    public CarBookingService() {
        this.carBookingDAO = new CarBookingDAO();
        this.carService = new CarService();
        this.userService = new UserService();
    }


    public void bookCar(User user, Car car) {
        CarBooking carBooking = new CarBooking(user, car);
        UUID savedBookingId = carBookingDAO.saveCarBooking(carBooking);
        carService.deleteCarFromUnbookedCarArray(car);
        System.out.println(BOOKED_CAR.getMessage() + car.getRegNumber() + FOR_USER.getMessage() + user);
        System.out.println(BOOKING_REF.getMessage() + savedBookingId);
    }


    public void getAllBookings() {
        CarBooking[] carBookings = carBookingDAO.selectAllCarBookings();
        if (carBookings[0] != null) {
            for (CarBooking carBooking : carBookings) {
                System.out.println(carBooking);
            }
        } else {
            System.out.println(NO_BOOKINGS.getMessage());
        }
    }

    public void getUserBookedCarsByUserId(String uuid) {
        User userById = userService.getUserById(uuid);
        CarBooking[] carBookings = carBookingDAO.selectAllCarBookings();
        if (carBookings[0] != null) {
            for (CarBooking carBooking : carBookings) {
                if (carBooking.getUser().equals(userById)) {
                    System.out.println(carBooking);
                }
            }
        } else if (userById == null) {
            System.out.println(X_USER.getMessage());
        } else {
            System.out.println(X_USER.getMessage() + userById + NOT_BOOKED.getMessage());
        }
    }
}
