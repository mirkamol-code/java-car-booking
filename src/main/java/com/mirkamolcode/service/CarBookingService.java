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

    public CarBookingService(CarBookingDAO carBookingDAO, CarService carService, UserService userService) {
        this.carBookingDAO = carBookingDAO;
        this.carService = carService;
        this.userService = userService;
    }


    public void bookCar(User user, Car car) {
        CarBooking carBooking = new CarBooking(user, car);
        carBooking.setBookingId(UUID.randomUUID());
        UUID savedBookingId = carBookingDAO.saveCarBooking(carBooking);

        carService.deleteCar(car);

        System.out.println(BOOKED_CAR.getMessage() + car.getRegNumber() + FOR_USER.getMessage() + user);
        System.out.println(BOOKING_REF.getMessage() + savedBookingId);
    }


    public void printAllBookings() {
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
    public void printUserBookedCars(UUID id){
        CarBooking[] bookedCars = getUserBookedCarsByUserId(id);
        for (CarBooking bookedCar : bookedCars) {
            System.out.println(bookedCar);
        }
    }

    private CarBooking[] getUserBookedCarsByUserId(UUID id) {
        if (!userService.isUserPresent(id)) {
            return new CarBooking[0];
        }

        User user = userService.getUserById(id);
        CarBooking[] carBookings = carBookingDAO.selectAllBookings();
        var countUserBookings = 0;

        boolean isFound = false;
        for (int i = 0; i < carBookings.length && carBookings[i] != null; i++) {
            if (carBookings[i].getUser().equals(user)) {
                isFound = true;
                countUserBookings++;
            }
        }

        if (!isFound) {
            System.out.println(X_USER.getMessage() + user + NOT_BOOKED.getMessage());
        }

        CarBooking[] userBookedCar = new CarBooking[countUserBookings];
        var index = 0;
        for (int i = 0; i < carBookings.length && carBookings[i] != null; i++) {
            if (carBookings[i].getUser().equals(user)) {
                userBookedCar[index] = carBookings[i];
                index++;
            }
        }

        return userBookedCar;
    }


    public boolean deleteCarBooking(UUID carBookingId) {
        return carBookingDAO.deleteCarBooking(carBookingId);
    }


    public boolean isCarBookingExist(UUID id) {
        for (CarBooking carBooking : carBookingDAO.selectAllBookings()) {
            if (carBooking.getBookingId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean isCarBookingArrayEmpty() {
        CarBooking[] carBookings = carBookingDAO.selectAllBookings();
        return carBookings[0] != null;

    }
}
