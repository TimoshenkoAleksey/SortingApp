package ru.aston.team.one.sortingapp.input;

import java.io.InputStream;
import java.util.Scanner;

public class ConsoleInput {
    private final Scanner scanner;

    ConsoleInput(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    public ConsoleInput() {
        this(System.in);
    }

    public int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(readLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введено не целое число или число вне диапазона int. Попробуйте снова!");
            }
        }
    }

    public double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(readLine().trim());
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
        return readLine().trim();
    }

    private String readLine() {
        if (!scanner.hasNextLine()) {
            throw new IllegalStateException("Ввод завершён (EOF)");
        }
        return scanner.nextLine();
    }
}
