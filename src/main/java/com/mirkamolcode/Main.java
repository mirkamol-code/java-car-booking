package com.mirkamolcode;

import com.mirkamolcode.dao.*;
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

    static void main() {
        //Construct dependencies
        UserDAO userDAO = new UserArrayDAO();
        UserService userService = new UserService(userDAO);
        CarDAO carDAO = new CarDAO();
        CarService carService = new CarService(carDAO);
        CarBookingDAO carBookingDAO = new CarBookingDAO();

        //Inject dependencies
        CarBookingService carBookingService = new CarBookingService(carBookingDAO, carService, userService);

        printMenu();

        Scanner scanner = new Scanner(System.in);
        int inputString = scanner.nextInt();
        while (inputString != 7) {
            switch (inputString) {
                case 0:
                    if (carBookingService.isCarBookingListEmpty()) {
                        System.out.println(NO_BOOKINGS.getMessage());
                    } else {
                        carBookingService.printAllBookings();
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
                    carService.printAllCars();
                    System.out.println(SELECTION_OF_CAR_REG_NUMBER.getMessage());
                    scanner.nextLine();

                    String carRegNumber = scanner.nextLine();
                    if (!carService.isRegNumberExist(carRegNumber)) {
                        System.out.println(NOT_FOUND.getMessage());

                    } else {
                        Car carByRegNumber = carService.getCarByRegNumber(carRegNumber);
                        userService.printAllUsers();
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
                    userService.printAllUsers();
                    System.out.println(SELECTION_OF_USER_ID.getMessage());
                    scanner.nextLine();
                    UUID userId = UUID.fromString(scanner.nextLine());
                    carBookingService.printUserBookedCars(userId);
                    System.out.println();
                    printMenu();
                    break;
                case 3:
                    System.out.println();
                    carBookingService.printAllBookings();
                    System.out.println();
                    printMenu();
                    break;
                case 4:
                    System.out.println();
                    carService.printAllCars();
                    System.out.println();
                    printMenu();
                    break;
                case 5:
                    System.out.println();
                    carService.printElectricCars();
                    System.out.println();
                    printMenu();
                    break;
                case 6:
                    System.out.println();
                    userService.printAllUsers();
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
        for (Menu value : Menu.values()) {
            System.out.println(value.getMessage());
        }
    }
}
