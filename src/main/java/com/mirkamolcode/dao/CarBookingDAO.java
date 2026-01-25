package com.mirkamolcode.dao;

import com.mirkamolcode.model.CarBooking;

import java.util.UUID;

public class CarBookingDAO {
    private static CarBooking[] bookings;
    private int capacity = 100;
    private int currentSize = 0;

    public CarBookingDAO() {
        bookings = new CarBooking[capacity];
    }

    public UUID saveCarBooking(CarBooking newBooking) {
        if (currentSize >= capacity) {
            this.capacity = bookings.length * 2;
            CarBooking[] newArray = new CarBooking[this.capacity];
            System.arraycopy(bookings, 0, newArray, 0, bookings.length);
            bookings = newArray;
        }

        int next = getNextAvailableSlot();
        if (next == -1) {
            throw new IllegalStateException("No available slots to save car");
        }

        bookings[next] = newBooking;
        currentSize++;
        return newBooking.getBookingId();

    }

    private int getNextAvailableSlot() {
        for (int i = 0; i < bookings.length; i++) {
            if (bookings[i] == null) {
                return i;
            }
        }
        return -1;
    }


    public CarBooking[] selectAllBookings() {
        return bookings;
    }



    public boolean deleteCarBooking(UUID bookingId) {
        int index = findBookingIndex(bookingId);
        if (index == -1) {
            return false;
        }

        removeAt(index);
        return true;
    }

    private int findBookingIndex(UUID bookingId) {
        int index = -1;

        for (int i = 0; i < bookings.length; i++) {
            if (bookings[i].getBookingId().equals(bookingId)) {
                index = i;
                break;
            }
        }
        return index;
    }

    private void removeAt(int index) {
        for (int i = index; i < currentSize - 1; i++) {
            bookings[i] = bookings[i + 1];
        }

        bookings[--currentSize] = null;
    }
}