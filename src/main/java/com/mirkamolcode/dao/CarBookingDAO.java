package com.mirkamolcode.dao;

import com.mirkamolcode.model.CarBooking;

public class CarBookingDAO {
    private static CarBooking[] carBookings;
    private static int nextAvailableSlot = 0;
    private static final int CAPACITY = 1;

    static {
        carBookings = new CarBooking[CAPACITY];
    }

    public void saveCarBook(CarBooking newBooking) {
        resizeIfNeeded();
        carBookings[nextAvailableSlot] = newBooking;
        ++nextAvailableSlot;
    }

    private void resizeIfNeeded() {
        if (nextAvailableSlot + 1 >= CAPACITY) {
            CarBooking[] newArray = new CarBooking[CAPACITY + 1];
            System.arraycopy(carBookings, 0, newArray, 0, carBookings.length);
            carBookings = newArray;
        }
    }
}
