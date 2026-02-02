package com.mirkamolcode.dao;

import com.mirkamolcode.model.CarBooking;

import java.util.*;

import static com.mirkamolcode.model.enums.ResponseMessage.BOOKING_ID_NOT_FOUND;

public class CarBookingDAO {
    private static List<CarBooking> bookingList = new ArrayList<>();

    public CarBookingDAO() {
    }

    public List<CarBooking> selectAllBookings() {
        return bookingList;
    }

    public Optional<CarBooking> getCarBookingById(UUID id) {
        return bookingList.stream()
                .filter(carBooking -> carBooking.getBookingId().equals(id))
                .findFirst();
    }

    public UUID saveCarBooking(CarBooking newBooking) {
        bookingList.add(newBooking);
        return newBooking.getBookingId();
    }

    public boolean deleteCarBooking(UUID id) {
        CarBooking carBooking = getCarBookingById(id)
                .orElseThrow(()-> new NoSuchElementException(BOOKING_ID_NOT_FOUND.getMessage()));
        return bookingList.remove(carBooking);
    }
}