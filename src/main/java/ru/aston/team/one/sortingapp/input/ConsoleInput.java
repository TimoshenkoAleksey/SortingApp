package ru.aston.team.one.sortingapp.input;

import java.util.Scanner;

public class ConsoleInput {
    private final Scanner scanner = new Scanner(System.in);

    public int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введено не целое число или число вне диапазона int. Попробуйте снова!");
            }
        }
    }

    public double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String input = scanner.nextLine();
                double value = Double.parseDouble(input.trim());
                if (Double.isNaN(value) || Double.isInfinite(value)) {
                    System.out.println("Ошибка: введите конечное число. Попробуйте снова!");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введено не число (дробная часть отделяется точкой). Попробуйте снова!");
            }
        }
    }

    public String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public String readFilePath(String prompt) {
        return readString(prompt);
    }
}
