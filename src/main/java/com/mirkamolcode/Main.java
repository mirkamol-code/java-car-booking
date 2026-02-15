package com.mirkamolcode;

import com.mirkamolcode.dao.*;
import com.mirkamolcode.model.enums.Menu;
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
        UserDAO userDAO = new UserFakerDAS();
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
            try {
                switch (inputString) {
                    case 0:
                        carBookingService.getAllBookings().forEach(System.out::println);
                        System.out.println(SELECTION_OF_BOOKING_ID.getMessage());
                        scanner.nextLine();
                        UUID bookingId = UUID.fromString(scanner.nextLine());
                        System.out.println(carBookingService.deleteCarBooking(bookingId));

                        printMenu();
                        break;
                    case 1:
                        carService.getAllCars().forEach(System.out::println);
                        System.out.println(SELECTION_OF_CAR_REG_NUMBER.getMessage());
                        scanner.nextLine();
                        var carRegNumber = scanner.nextLine();

                        userService.getAllUsers().forEach(System.out::println);
                        System.out.println(SELECTION_OF_USER_ID.getMessage());
                        var userId = UUID.fromString(scanner.nextLine());

                        carBookingService.bookCar(carRegNumber, userId);

                        System.out.println();
                        printMenu();
                        break;
                    case 2:
                        userService.getAllUsers().forEach(System.out::println);
                        System.out.println(SELECTION_OF_USER_ID.getMessage());
                        scanner.nextLine();
                        UUID id = UUID.fromString(scanner.nextLine());
                        carBookingService.getUserBookedCarsByUserId(id).forEach(System.out::println);
                        System.out.println();
                        printMenu();
                        break;
                    case 3:
                        System.out.println();
                        carBookingService.getAllBookings().forEach(System.out::println);
                        System.out.println();
                        printMenu();
                        break;
                    case 4:
                        System.out.println();
                        carService.getAllCars().forEach(System.out::println);
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
            } catch (Exception e) {
                System.out.println(e.getMessage());
                printMenu();
            }
            inputString = scanner.nextInt();
        }
    }

    static void printMenu() {
        Arrays.stream(Menu.values())
                .forEach(value -> System.out.println(value.getMessage()));
    }
}
