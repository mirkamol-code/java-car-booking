package com.mirkamolcode.dao;

import com.mirkamolcode.model.CarBooking;

import java.util.SortedMap;
import java.util.UUID;

public class CarBookingDAO {
    private static CarBooking[] carBookings;
    private static int nextAvailableSlot = 0;
    private static int CAPACITY = 100;

    static {
        carBookings = new CarBooking[CAPACITY];
    }

    public UUID saveCarBooking(CarBooking newBooking) {
        resizeIfNeeded();
        carBookings[nextAvailableSlot++] = newBooking;
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


    public String deleteCarBooking(String bookingId){
        UUID inputId = UUID.fromString(bookingId);
        System.out.println("Initial size: " + carBookings.length);
        for (int indexToRemove = 0; indexToRemove < carBookings.length; indexToRemove++) {
            if(carBookings[indexToRemove].getBookingId().equals(inputId)){
                CarBooking[] shrunkedArray = new CarBooking[carBookings.length - 1];

                System.arraycopy(carBookings, 0, shrunkedArray, 0, carBookings.length -1);
                System.arraycopy(carBookings, indexToRemove + 1, shrunkedArray, indexToRemove, carBookings.length - indexToRemove - 1);

                carBookings = shrunkedArray;
                nextAvailableSlot--;
                return bookingId;
            }
        }
        return null;
    }

}
