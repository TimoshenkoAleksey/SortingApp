package ru.aston.team.one.sortingapp.input;

import org.junit.jupiter.api.Test;
import ru.aston.team.one.sortingapp.model.Student;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StudentInputTest {

    private static ConsoleInput createConsoleInput(String input) {
        return new ConsoleInput(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
    }

    @Test
    void readStudent_whenValidInput_shouldReturnStudent() {
        ConsoleInput consoleInput = createConsoleInput("101\n4.5\n12345\n");
        StudentInput studentInput = new StudentInput(consoleInput);

        Student result = studentInput.readStudent();

        assertEquals(101, result.groupNumber(), "Номер группы должен быть 101");
        assertEquals(4.5, result.averageNumber(), 0.0001, "Средний балл должен быть 4.5");
        assertEquals(12345, result.recordBookNumber(), "Номер зачетки должен быть 12345");
    }

    @Test
    void readStudent_whenInvalidThenValidInput_shouldReturnStudent() {
        ConsoleInput consoleInput = createConsoleInput("-1\n101\n4.5\n12345\n");
        StudentInput studentInput = new StudentInput(consoleInput);

        Student result = studentInput.readStudent();

        assertEquals(101, result.groupNumber(), "После ошибки должен вернуться корректный студент");
        assertEquals(4.5, result.averageNumber(), 0.0001, "Средний балл должен быть 4.5");
        assertEquals(12345, result.recordBookNumber(), "Номер зачетки должен быть 12345");
    }

    @Test
    void readStudent_whenEmptyInput_shouldThrowIllegalStateException() {
        ConsoleInput consoleInput = createConsoleInput("");
        StudentInput studentInput = new StudentInput(consoleInput);

        assertThrows(IllegalStateException.class, () -> {
            studentInput.readStudent();
        }, "При пустом вводе должно выбрасываться IllegalStateException");
    }

    @Test
    void readStudent_whenInvalidThenEof_shouldThrowIllegalStateException() {
        ConsoleInput consoleInput = createConsoleInput("-1\n");
        StudentInput studentInput = new StudentInput(consoleInput);

        assertThrows(IllegalStateException.class, () -> {
            studentInput.readStudent();
        }, "После невалидного значения и EOF должно быть исключение");
    }

    @Test
    void readStudent_whenNotNumberThenValid_shouldRetryAndReturnStudent() {
        ConsoleInput consoleInput = createConsoleInput("abc\n101\n4.5\n12345\n");
        StudentInput studentInput = new StudentInput(consoleInput);

        Student result = studentInput.readStudent();

        assertEquals(101, result.groupNumber(), "После ошибки парсинга ввод должен быть повторён");
        assertEquals(4.5, result.averageNumber(), 0.0001, "Средний балл должен быть 4.5");
        assertEquals(12345, result.recordBookNumber(), "Номер зачетки должен быть 12345");
    }
}