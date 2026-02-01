package com.mirkamolcode.dao;

import com.mirkamolcode.model.CarBooking;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CarBookingDAO {
    private static List<CarBooking> bookingList = new ArrayList<>();

    public CarBookingDAO() {
    }

    public List<CarBooking> selectAllBookings() {
        return bookingList;
    }

    public CarBooking getCarBookingById(UUID id) {
        return bookingList.stream()
                .filter(carBooking -> carBooking.getBookingId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public UUID saveCarBooking(CarBooking newBooking) {
        bookingList.add(newBooking);
        return newBooking.getBookingId();
    }

    public boolean deleteCarBooking(UUID id) {
        CarBooking carBooking = getCarBookingById(id);
        return bookingList.remove(carBooking);
    }
}