package com.mirkamolcode;

import com.mirkamolcode.model.Car;
import com.mirkamolcode.model.User;
import com.mirkamolcode.model.enums.Menu;
import com.mirkamolcode.service.CarBookingService;
import com.mirkamolcode.service.CarService;
import com.mirkamolcode.service.UserService;

import java.util.Scanner;
import java.util.UUID;

import static com.mirkamolcode.model.enums.ResponseMessage.*;


public class Main {
    private static final CarBookingService carBookingService = new CarBookingService();
    private static final CarService carService = new CarService();
    private static final UserService userService = new UserService();

    static void main() {
        printMenu();
        Scanner scanner = new Scanner(System.in);

        int inputString = scanner.nextInt();
        while (inputString != 7) {
            switch (inputString) {
                case 0:
                    if (!carBookingService.isCarBookingArrayEmpty()){
                        System.out.println(NO_BOOKINGS);
                    }else {
                        carBookingService.getAllBookings();
                        System.out.println(SELECTION_OF_BOOKING_ID.getMessage());
                        UUID bookingId = UUID.fromString(scanner.nextLine());

                        boolean carBookingExist = carBookingService.isCarBookingExist(bookingId);
                        if (!carBookingExist) {
                            System.out.println(NOT_FOUND);

                        } else {
                            carBookingService.deleteCarBooking(bookingId);
                        }
                    }
                    printMenu();
                    break;
                case 1:
                    carService.getAllCars();
                    System.out.println(SELECTION_OF_CAR_REG_NUMBER.getMessage());

                    String carRegNumber = scanner.nextLine();
                    if (!carService.isRegNumberExist(carRegNumber)) {
                        System.out.println(NOT_FOUND.getMessage());

                    } else {
                        Car carByRegNumber = carService.getCarByRegNumber(carRegNumber);
                        printAllUsers();
                        System.out.println(SELECTION_OF_USER_ID.getMessage());
                        var userId = UUID.fromString(scanner.nextLine());

                        if (!userService.isUserExist(userId)) {
                            System.out.println(NOT_FOUND.getMessage());

                        } else {
                            User userById = userService.getUserById(userId);
                            carBookingService.bookCar(userById, carByRegNumber);
                            System.out.println();

                        }
                    }
                    printMenu();
                    break;
                case 2:
                    printAllUsers();
                    System.out.println(SELECTION_OF_USER_ID.getMessage());
                    UUID userId = UUID.fromString(scanner.nextLine());
                    carBookingService.getUserBookedCarsByUserId(userId);
                    System.out.println();
                    printMenu();
                    break;
                case 3:
                    System.out.println();
                    carBookingService.getAllBookings();
                    System.out.println();
                    printMenu();
                    break;
                case 4:
                    System.out.println();
                    carService.getAllCars();
                    System.out.println();
                    printMenu();
                    break;
                case 5:
                    System.out.println();
                    carService.getElectricCars();
                    System.out.println();
                    printMenu();
                    break;
                case 6:
                    System.out.println();
                    printAllUsers();
                    System.out.println();
                    printMenu();
                    break;
                default:
                    System.out.println(inputString + INVALID_OPTION.getMessage());
            }
            inputString = scanner.nextInt();
        }


    }

    private static void printAllUsers() {
        for (User u : userService.getAllUsers()) {
            System.out.println(u);
        }
    }

    static void  printMenu() {
        for (Menu value : Menu.values()) {
            System.out.println(value.getMessage());
        }
    }
}
