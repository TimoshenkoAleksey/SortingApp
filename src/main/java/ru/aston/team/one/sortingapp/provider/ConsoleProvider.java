package ru.aston.team.one.sortingapp.provider;

import ru.aston.team.one.sortingapp.input.ConsoleInput;
import ru.aston.team.one.sortingapp.model.Student;

import java.util.ArrayList;
import java.util.List;

public class ConsoleProvider implements Provider {

    private final ConsoleInput input;

    public ConsoleProvider(ConsoleInput input) {
        this.input = input;
    }

    @Override
    public List<Student> provide() {
        List<Student> students = new ArrayList<>();
        int count = input.readInt("Сколько студентов вы хотите добавить? ");
        if (count <= 0) {
            System.out.println("Количество должно быть больше нуля. Возвращаю пустой список.");
            return students;
        }
        int addedCount = 0;
        while (addedCount < count) {
            System.out.println("Ввод студента #" + (addedCount + 1) + " из " + count);
            try {
                int groupNumber = input.readInt("Введите номер группы: ");
                double averageNumber = input.readDouble("Введите средний балл: ");
                int recordBookNumber = input.readInt("Введите номер зачетной книжки: ");
                Student student = new Student.Builder()
                        .groupNumber(groupNumber)
                        .averageNumber(averageNumber)
                        .recordBookNumber(recordBookNumber)
                        .build();
                students.add(student);
                addedCount++;
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
                System.out.println("Повторите ввод данных для студента #" + (addedCount + 1));
            }
        }
        return students;
    }
}
