package ru.aston.team.one.sortingapp.provider;

import ru.aston.team.one.sortingapp.input.ConsoleInput;
import ru.aston.team.one.sortingapp.input.StudentInput;
import ru.aston.team.one.sortingapp.model.Student;

import java.util.ArrayList;
import java.util.List;

public class ConsoleProvider implements Provider {

    private final ConsoleInput input;
    private final StudentInput studentInput;

    public ConsoleProvider(ConsoleInput input, StudentInput studentInput) {
        this.input = input;
        this.studentInput = studentInput;
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
            students.add(studentInput.readStudent());
            addedCount++;
        }
        return students;
    }
}
