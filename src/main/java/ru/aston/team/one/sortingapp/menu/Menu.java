package ru.aston.team.one.sortingapp.menu;

import ru.aston.team.one.sortingapp.enums.Field;
import ru.aston.team.one.sortingapp.enums.SourceType;
import ru.aston.team.one.sortingapp.input.ConsoleInput;
import ru.aston.team.one.sortingapp.service.Service;

import java.util.Objects;

public class Menu {

    private final ConsoleInput input;
    private final Service service;
    private boolean isRunning;

    public Menu(ConsoleInput input, Service service) {
        this.input = Objects.requireNonNull(input, "input must not be null");
        this.service = Objects.requireNonNull(service, "service must not be null");
    }

    public void start() {
        System.out.println("Добро пожаловать в SortingApp");
        isRunning = true;
        while (isRunning) {
            try {
                printMenu();
                int choice = input.readInt("Выберите пункт меню");
                handleChoice(choice);
            } catch (RuntimeException e) {
                System.out.println("Ошибка: " + e.getMessage());
                exit();
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("========== МЕНЮ ==========");
        System.out.println("1.  Заполнить коллекцию вручную");
        System.out.println("2.  Заполнить коллекцию случайными данными");
        System.out.println("3.  Загрузить коллекцию из файла");
        System.out.println("4.  Вывести коллекцию в консоль");
        System.out.println("5.  Сортировка по номеру группы");
        System.out.println("6.  Сортировка по среднему баллу");
        System.out.println("7.  Сортировка по номеру зачётной книжки");
        System.out.println("8.  Сортировка по чётным номерам зачёток (доп.)");
        System.out.println("9.  Сохранить коллекцию в файл (дозапись)");
        System.out.println("10. Подсчёт вхождений (доп.)");
        System.out.println("0.  Выход");
        System.out.println("==========================");
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1 -> service.inputCollection(SourceType.MANUAL);
            case 2 -> service.inputCollection(SourceType.RANDOM);
            case 3 -> service.inputCollection(SourceType.FILE);
            case 4 -> service.printCollection();
            case 5 -> service.sortCollection(Field.GROUP_NUMBER);
            case 6 -> service.sortCollection(Field.AVERAGE_SCORE);
            case 7 -> service.sortCollection(Field.RECORD_BOOK_NUMBER);
            case 8 -> service.sortCollection(Field.EVEN_RECORD_BOOK_NUMBER);
            case 9 -> service.saveToFile();
            case 10 -> service.countOccurrences();
            case 0 -> exit();
            default -> System.out.println("Неверный пункт меню.");
        }
    }

    private void exit() {
        System.out.println("Выход из программы. До свидания!");
        isRunning = false;
    }

}
