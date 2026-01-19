package com.mirkamolcode.model.enums;

public enum ResponseMessage {
    INVALID_OPTION("not a valid option ❌"),
    NO_BOOKINGS("no bookings available 😕"),
    SELECTION_OF_USER_ID("➡️ select user id"),
    SELECTION_OF_CAR_REG_NUMBER("➡️ select car reg number"),
    X_USER("❌ user "), NOT_BOOKED(" has no car booked"),
    BOOKED_CAR("🎉 Successfully booked car with reg number "),
    BOOKING_REF("Booking ref: "),
    BOOKINGS("booking = "),
    NO_CARS("❌ No cars available for renting");

    private final String message;

    ResponseMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
