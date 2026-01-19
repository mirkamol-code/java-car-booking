package com.mirkamolcode;

import com.mirkamolcode.model.Car;
import com.mirkamolcode.model.User;
import com.mirkamolcode.model.enums.Menu;
import com.mirkamolcode.service.CarBookingService;
import com.mirkamolcode.service.CarService;
import com.mirkamolcode.service.UserService;

import java.util.Scanner;

import static com.mirkamolcode.model.enums.ResponseMessage.*;


public class Main {
    private static CarBookingService carBookingService = new CarBookingService();
    private static CarService carService = new CarService();
    private static UserService userService = new UserService();

    static void main() {
        printMenu();
        Scanner scanner = new Scanner(System.in);

        String inputString = scanner.nextLine();
        while (!inputString.equals("7")) {
            switch (inputString) {
                case "1":
                    carService.getAllCars();
                    System.out.println(SELECTION_OF_CAR_REG_NUMBER.getMessage());

                    String carRegNumber = scanner.nextLine();
                    if (!carService.isRegNumberExist(carRegNumber)) {
                        System.out.println(NOT_FOUND.getMessage());

                    } else {
                        Car carByRegNumber = carService.getCarByRegNumber(carRegNumber);

                        userService.getAllUsers();
                        System.out.println(SELECTION_OF_USER_ID.getMessage());
                        String user_id = scanner.nextLine();

                        if (!userService.isUserExist(user_id)) {
                            System.out.println(NOT_FOUND.getMessage());

                        } else {
                            User userById = userService.getUserById(user_id);
                            carBookingService.bookCar(userById, carByRegNumber);
                            System.out.println();

                        }
                    }
                    printMenu();
                    break;
                case "2":
                    userService.getAllUsers();
                    System.out.println(SELECTION_OF_USER_ID.getMessage());
                    String userId = scanner.nextLine();
                    carBookingService.getUserBookedCarsByUserId(userId);
                    System.out.println();
                    printMenu();
                    break;
                case "3":
                    System.out.println();
                    carBookingService.getAllBookings();
                    System.out.println();
                    printMenu();
                    break;
                case "4":
                    System.out.println();
                    carService.getAllCars();
                    System.out.println();
                    printMenu();
                    break;
                case "5":
                    System.out.println();
                    carService.getElectricCars();
                    System.out.println();
                    printMenu();
                    break;
                case "6":
                    System.out.println();
                    userService.getAllUsers();
                    System.out.println();
                    printMenu();
                    break;
                default:
                    System.out.println(inputString + INVALID_OPTION.getMessage());
            }
            inputString = scanner.nextLine();
        }


    }

    static void printMenu() {
        for (Menu value : Menu.values()) {
            System.out.println(value.getMessage());
        }
    }
}
