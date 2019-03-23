package ru.bjcreslin.service;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleService {
    public static void print(String text) {
        System.out.print(text);
    }

    public static void crlf() {
        System.out.println();
    }

    public static void println(String text) {
        System.out.println(text);
    }

    public static int inputOneNumber() {
        int readSymbol;
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                readSymbol = sc.nextInt();
                break;
            } catch (Exception e) {
                sc.next();
            }
        }
        return readSymbol;
    }


    public static void printMenu() {
        println("Стартовое меню");
        println("выбор пункта меню цифрами");
        println("1. Запуск игры.");
        println("2. Настройки.");
        println("3. Выход.");
    }
}
