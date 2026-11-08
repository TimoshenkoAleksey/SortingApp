package ru.aston.team.one.sortingapp.input;

import ru.aston.team.one.sortingapp.model.Student;

import java.util.Objects;

public class StudentInput {
    private final ConsoleInput input;

    public StudentInput(ConsoleInput input) {
        this.input = Objects.requireNonNull(input, "input не должен быть пустым");
    }

    public Student readStudent() {
        while (true) {
            int groupNumber = input.readInt("Введите номер группы: ");
            double averageNumber = input.readDouble("Введите средний балл: ");
            int recordBookNumber = input.readInt("Введите номер зачетной книжки: ");
            try {
                return new Student.Builder().groupNumber(groupNumber)
                        .averageNumber(averageNumber).recordBookNumber(recordBookNumber).build();
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage() + ". Повторите ввод.");
            }
        }
    }
}
