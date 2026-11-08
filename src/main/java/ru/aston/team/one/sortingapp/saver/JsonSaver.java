package ru.aston.team.one.sortingapp.saver;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JsonSaver implements Saver {
    private static final String FILE_NAME = "data/students.json";

    private final ObjectMapper mapper = new ObjectMapper();
    private final TypeReference<List<?>> typeReference;

    public JsonSaver(TypeReference<List<?>> typeReference) {
        this.typeReference = typeReference;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> void save(List<T> list) {
        Path path = Path.of(FILE_NAME);
        Path parentDir = path.getParent();
        List<T> allElements = new ArrayList<>();
        try {
            if (parentDir != null && !Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
            }
            if (Files.exists(path) && Files.size(path) > 0) {
                allElements = (List<T>) mapper.readValue(path.toFile(), typeReference);
            }
            allElements.addAll(list);
            mapper.writeValue(path.toFile(), allElements);
        } catch (IOException e) {
            System.err.println("Ошибка при работе с файлом " + FILE_NAME + ": " + e.getMessage());
            return;
        }
        System.out.println("Данные успешно отправлены на сохранение.");
    }
}
