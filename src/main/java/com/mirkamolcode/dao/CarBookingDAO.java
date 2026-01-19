package com.mirkamolcode.dao;

import com.mirkamolcode.model.CarBooking;

import java.util.UUID;

public class CarBookingDAO {
    private static CarBooking[] carBookings;
    private static int nextAvailableSlot = 0;
    private static final int CAPACITY = 1;

    static {
        carBookings = new CarBooking[CAPACITY];
    }

    public UUID saveCarBooking(CarBooking newBooking) {
        resizeIfNeeded();
        carBookings[nextAvailableSlot] = newBooking;
        ++nextAvailableSlot;
        return newBooking.getBookingId();
    }

    private void resizeIfNeeded() {
        if (nextAvailableSlot + 1 > CAPACITY) {
            CarBooking[] newArray = new CarBooking[carBookings.length + 1];
            System.arraycopy(carBookings, 0, newArray, 0, carBookings.length);
            carBookings = newArray;
        }
    }

    public CarBooking[] selectAllCarBookings() {
        return carBookings;
    }
}
