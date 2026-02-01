package com.mirkamolcode;

import com.mirkamolcode.dao.*;
import com.mirkamolcode.model.Car;
import com.mirkamolcode.model.User;
import com.mirkamolcode.model.enums.Menu;
import com.mirkamolcode.model.enums.ResponseMessage;
import com.mirkamolcode.service.CarBookingService;
import com.mirkamolcode.service.CarService;
import com.mirkamolcode.service.UserService;

import java.util.Arrays;
import java.util.Scanner;
import java.util.UUID;

import static com.mirkamolcode.model.enums.ResponseMessage.*;


public class Main {

    static void main() {
        //Construct dependencies
        UserDAO userDAO = new UserFileDAO();
        UserService userService = new UserService(userDAO);
        CarDAO carDAO = new CarDAO();
        CarService carService = new CarService(carDAO);
        CarBookingDAO carBookingDAO = new CarBookingDAO();

        //Inject dependencies
        CarBookingService carBookingService = new CarBookingService(carBookingDAO, carService);

        printMenu();

        Scanner scanner = new Scanner(System.in);
        int inputString = scanner.nextInt();
        while (inputString != 7) {
            switch (inputString) {
                case 0:
                    if (carBookingService.isCarBookingListEmpty()) {
                        System.out.println(NO_BOOKINGS.getMessage());

                    } else {
                        carBookingDAO.selectAllBookings().forEach(System.out::println);
                        System.out.println(SELECTION_OF_BOOKING_ID.getMessage());
                        scanner.nextLine();
                        UUID bookingId = UUID.fromString(scanner.nextLine());

                        boolean carBookingExist = carBookingService.isCarBookingExist(bookingId);
                        if (!carBookingExist) {
                            System.out.println(NOT_FOUND);

                        } else {
                            System.out.println(carBookingService.deleteCarBooking(bookingId));

                        }
                    }
                    printMenu();
                    break;
                case 1:
                    carDAO.selectAllCars().forEach(System.out::println);
                    System.out.println(SELECTION_OF_CAR_REG_NUMBER.getMessage());
                    scanner.nextLine();

                    String carRegNumber = scanner.nextLine();
                    if (!carService.isRegNumberExist(carRegNumber)) {
                        System.out.println(NOT_FOUND.getMessage());

                    } else {
                        Car carByRegNumber = carService.getCarByRegNumber(carRegNumber);
                        userService.getAllUsers().forEach(System.out::println);
                        System.out.println(SELECTION_OF_USER_ID.getMessage());
                        var userId = UUID.fromString(scanner.nextLine());

                        if (!userService.isUserPresent(userId)) {
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
                    userService.getAllUsers().forEach(System.out::println);
                    System.out.println(SELECTION_OF_USER_ID.getMessage());
                    scanner.nextLine();
                    UUID userId = UUID.fromString(scanner.nextLine());
                    if (!userService.isUserPresent(userId)) {
                        System.out.println(X_USER.getMessage());

                    } else {
                        if (carBookingService.getUserBookedCarsByUserId(userId).isEmpty()) {
                            System.out.println(X_USER.getMessage() + userId + NOT_BOOKED.getMessage());

                        } else {
                            carBookingService.getUserBookedCarsByUserId(userId).forEach(System.out::println);

                        }
                    }
                    System.out.println();
                    printMenu();
                    break;
                case 3:
                    System.out.println();
                    if (carBookingService.isCarBookingListEmpty()) {
                        System.out.println(NO_BOOKINGS.getMessage());

                    } else {
                        carBookingDAO.selectAllBookings().forEach(System.out::println);

                    }
                    System.out.println();
                    printMenu();
                    break;
                case 4:
                    System.out.println();
                    carDAO.selectAllCars().forEach(System.out::println);
                    System.out.println();
                    printMenu();
                    break;
                case 5:
                    System.out.println();
                    carService.getElectricCars().forEach(System.out::println);
                    System.out.println();
                    printMenu();
                    break;
                case 6:
                    System.out.println();
                    userService.getAllUsers().forEach(System.out::println);
                    System.out.println();
                    printMenu();
                    break;
                default:
                    System.out.println(inputString + INVALID_OPTION.getMessage());
            }
            inputString = scanner.nextInt();
        }


    }

    static void printMenu() {
        Arrays.stream(Menu.values())
                .forEach(value -> System.out.println(value.getMessage()));
    }
}
