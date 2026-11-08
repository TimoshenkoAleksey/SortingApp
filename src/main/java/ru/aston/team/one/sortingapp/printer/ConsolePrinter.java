package ru.aston.team.one.sortingapp.printer;

import ru.aston.team.one.sortingapp.model.Student;

import java.util.List;

public class ConsolePrinter {

    public void printCollection(List<Student> students) {
        if (students == null || students.isEmpty()) {
            System.out.println("Коллекция пуста. Нечего выводить.");
            return;
        }

        System.out.println();
        System.out.println("========== СПИСОК СТУДЕНТОВ ==========");
        System.out.printf("%-15s | %-15s | %-20s%n", "Номер группы", "Средний балл", "Номер зачетки");
        System.out.println("--------------------------------------------------------");

        for (Student student : students) {
            if (student == null) {
                continue;
            }
            System.out.printf("%-15d | %-15.2f | %-20d%n",
                    student.groupNumber(),
                    student.averageNumber(),
                    student.recordBookNumber());
        }

        System.out.println("========================================================");
        System.out.println();
    }

    public void printOccurrences(int count) {
        System.out.println("========================================================");
        System.out.printf("РЕЗУЛЬТАТ ПОДСЧЁТА: %d вхождений%n", count);
        System.out.println("========================================================");
        System.out.println();
    }
}