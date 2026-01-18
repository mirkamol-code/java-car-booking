package com.mirkamolcode.dao;

import com.mirkamolcode.model.CarBooking;
import com.mirkamolcode.model.User;

import java.util.IllegalFormatCodePointException;
import java.util.UUID;

public class CarBookingDAO {
    private static CarBooking[] carBookings;
    private static int nextAvailableSlot = 0;
    private static CarBooking[] carBookingsByUser;
    private static int nextAvailableSlotForUser = 0;
    private static final int CAPACITY = 1;

    static {
        carBookings = new CarBooking[CAPACITY];
        carBookingsByUser = new CarBooking[CAPACITY];
    }

    public UUID bookCar(CarBooking newBooking) {
        resizeIfNeeded();
        carBookings[nextAvailableSlot] = newBooking;
        ++nextAvailableSlot;
        return newBooking.getBookingId();
    }

    private void resizeIfNeeded() {
        if (nextAvailableSlot + 1 >= CAPACITY) {
            CarBooking[] newArray = new CarBooking[CAPACITY + 1];
            System.arraycopy(carBookings, 0, newArray, 0, carBookings.length);
            carBookings = newArray;
        }
    }

    public CarBooking[] selectAllCarBookings() {
        return carBookings;
    }

    public CarBooking[] selectUserBookedCarByUserId(String uuid) {
        UUID userUuid = UUID.fromString(uuid);
        for (CarBooking carBooking : carBookings) {
            User user = carBooking.getUser();
            if (user.getUuid() == userUuid) {
                resizeIfNeededForUserBookedCarArray();
                carBookingsByUser[nextAvailableSlotForUser] = carBooking;
                ++nextAvailableSlotForUser;
            }
        }
        return carBookingsByUser;
    }

    private void resizeIfNeededForUserBookedCarArray() {
        if (nextAvailableSlotForUser + 1 > CAPACITY) {
            CarBooking[] newArray = new CarBooking[CAPACITY + 1];
            System.arraycopy(carBookingsByUser, 0, newArray, 0, carBookingsByUser.length);
            carBookingsByUser = newArray;
        }
    }
}
