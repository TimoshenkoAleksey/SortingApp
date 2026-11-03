package ru.aston.team.one.sortingapp.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.aston.team.one.sortingapp.model.Student;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileProvider implements Provider{
    private static final String FILE_PATH = "students.json";
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<Student> provide() {
        try (InputStream in = openFile()) {
            Student[] students = mapper.readValue(in, Student[].class);
            if (students == null){
                throw new IllegalStateException("Не удалось прочитать файл " + FILE_PATH + " не содержит данных");
            }
            return new ArrayList<>(Arrays.asList(students));
        } catch (IOException e) {
            throw new IllegalStateException("Не удалось прочитать файл" + FILE_PATH + ": "+ e.getMessage(),e);
        }
    }

    private InputStream openFile() {
        InputStream in = FileProvider.class.getClassLoader().getResourceAsStream(FILE_PATH);
        if (in == null) {
            throw new IllegalStateException("Файл " + FILE_PATH + " не найден в resources");
        }
        return in;
    }
}
