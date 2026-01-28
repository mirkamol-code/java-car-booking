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
        List<CarBooking> carBookings = carBookingDAO.selectAllBookings();
        if (carBookings.isEmpty()) {
            System.out.println(NO_BOOKINGS.getMessage());
        } else {
            carBookings.forEach(System.out::println);
        }
    }

    public void printUserBookedCars(UUID id) {
        List<CarBooking> bookedCars = getUserBookedCarsByUserId(id);
        for (CarBooking bookedCar : bookedCars) {
            System.out.println(bookedCar);
        }
    }

    private List<CarBooking> getUserBookedCarsByUserId(UUID id) {
        if (!userService.isUserPresent(id)) {
            return new ArrayList<>();
        }
        boolean isFound = false;

        User user = userService.getUserById(id);
        List<CarBooking> userBookings = new ArrayList<>();
        for (CarBooking carBooking : carBookingDAO.selectAllBookings()) {
            if (carBooking.getUser().equals(user)) {
                userBookings.add(carBooking);
                isFound = true;
            }
        }

        if (!isFound) {
            System.out.println(X_USER.getMessage() + user + NOT_BOOKED.getMessage());
        }

        return userBookings;
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

    public boolean isCarBookingListEmpty() {
        return carBookingDAO.selectAllBookings().isEmpty();
    }
}
