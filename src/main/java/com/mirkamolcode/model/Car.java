package com.mirkamolcode.model;

import com.mirkamolcode.model.enums.Brand;

public class Car {
    private String regNumber;
    private double rentalPricePerDay;
    private Brand brand;
    private boolean isElectric;

    public Car(String regNumber, double rentalPricePerDay, Brand brand, boolean isElectric) {
        this.regNumber = regNumber;
        this.rentalPricePerDay = rentalPricePerDay;
        this.brand = brand;
        this.isElectric = isElectric;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public boolean isElectric() {
        return isElectric;
    }

    @Override
    public String toString() {
        return "Car{" +
                "regNumber=" + regNumber +
                ", rentalPricePerDay=" + rentalPricePerDay +
                ", brand=" + brand +
                ", isElectric=" + isElectric +
                '}';
    }
}
