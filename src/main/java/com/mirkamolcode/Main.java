package com.mirkamolcode;

import com.mirkamolcode.model.enums.Menu;


public class Main {
    static void main() {
        printMenu();
    }

    static void printMenu(){
        for (Menu value : Menu.values()) {
            System.out.println(value.getMessage());
        }
    }
}
