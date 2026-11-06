package ru.aston.team.one.sortingapp.provider;

import ru.aston.team.one.sortingapp.input.ConsoleInput;
import ru.aston.team.one.sortingapp.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class GeneratedProvider implements Provider {

    private final ConsoleInput input;
    private final Random random;

    public GeneratedProvider(ConsoleInput input) {
        this(input, new Random());
    }

    public GeneratedProvider(ConsoleInput input, Random random) {
        this.input = Objects.requireNonNull(input, "ConsoleInput must not be null");
        this.random = Objects.requireNonNull(random, "Random must not be null");
    }

    @Override
    public List<Student> provide() {
        int count = input.readInt("Введите количество студентов для генерации: ");

        if (count <= 0) {
            return new ArrayList<>();
        }

        List<Student> students = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            // Используем новые методы Java 21
            int groupNumber = random.nextInt(1, 101);
            double averageNumber = random.nextInt(0, 501) / 100.0;
            int recordBookNumber = random.nextInt(100_000, 1_000_000);

            students.add(new Student.Builder()
                    .groupNumber(groupNumber)
                    .averageNumber(averageNumber)
                    .recordBookNumber(recordBookNumber)
                    .build());
        }

        return students;
    }
}