package ru.aston.team.one.sortingapp.provider;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.aston.team.one.sortingapp.input.ConsoleInput;
import ru.aston.team.one.sortingapp.model.Student;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FileProvider implements Provider {

    private static final String DEFAULT_FILE = "students.json";

    private final ConsoleInput input;
    private final ObjectMapper mapper;

    public FileProvider(ConsoleInput input) {
        this(input, createDefaultMapper());
    }

    public FileProvider(ConsoleInput input, ObjectMapper mapper) {
        this.input = Objects.requireNonNull(input, "ConsoleInput must not be null");
        this.mapper = Objects.requireNonNull(mapper, "ObjectMapper must not be null");
    }

    private static ObjectMapper createDefaultMapper() {
        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public List<Student> provide() {
        String path;

        try {
            path = input.readString("Введите путь к файлу (или нажмите Enter для стандартного): ");
        } catch (RuntimeException e) {
            System.out.println("Ошибка при чтении пути: " + e.getMessage());
            return new ArrayList<>();
        }

        try (InputStream in = openStream(path)) {
            Student[] students = mapper.readValue(in, Student[].class);

            if (students == null) {
                throw new IllegalArgumentException("Файл не содержит данных");
            }

            for (Student student : students) {
                if (student == null) {
                    throw new IllegalArgumentException("Файл содержит null-студента");
                }
            }

            return new ArrayList<>(Arrays.asList(students));
        } catch (IOException | RuntimeException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private InputStream openStream(String path) {
        if (path == null || path.isBlank()) {
            InputStream in = FileProvider.class.getClassLoader().getResourceAsStream(DEFAULT_FILE);

            if (in == null) {
                throw new IllegalArgumentException("Стандартный файл " + DEFAULT_FILE + " не найден в resources");
            }

            return in;
        }

        Path file = Paths.get(path.trim());

        if (!Files.isRegularFile(file)) {
            throw new IllegalArgumentException("Файл по пути " + path + " не существует или не является обычным файлом");
        }

        try {
            return Files.newInputStream(file);
        } catch (IOException e) {
            throw new IllegalArgumentException("Не удалось открыть файл по пути " + path, e);
        }
    }
}