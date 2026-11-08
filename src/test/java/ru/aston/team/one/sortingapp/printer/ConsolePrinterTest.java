package ru.aston.team.one.sortingapp.printer;

import org.junit.jupiter.api.Test;
import ru.aston.team.one.sortingapp.model.Student;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ConsolePrinterTest {

    private final ConsolePrinter printer = new ConsolePrinter();

    @Test
    void printCollection_shouldPrintHeadersAndData_whenCollectionIsNotEmpty() {
        List<Student> students = new ArrayList<>();
        students.add(new Student.Builder().groupNumber(101).averageNumber(4.5).recordBookNumber(12345).build());
        students.add(new Student.Builder().groupNumber(102).averageNumber(3.8).recordBookNumber(54321).build());

        String output = captureOutput(() -> printer.printCollection(students));

        assertTrue(output.contains("Номер группы"), "Должен быть заголовок 'Номер группы'");


        assertTrue(output.contains("101"), "Должен быть номер группы 101");
        assertTrue(output.contains("4,50") || output.contains("4.50"), "Должен быть средний балл 4.50");
        assertTrue(output.contains("12345"), "Должен быть номер зачетки 12345");


        assertTrue(output.contains("102"), "Должен быть номер группы 102");
        assertTrue(output.contains("54321"), "Должен быть номер зачетки 54321");
    }

    @Test
    void printCollection_shouldPrintEmptyMessage_whenCollectionIsEmpty() {
        String output = captureOutput(() -> printer.printCollection(Collections.emptyList()));
        assertTrue(output.contains("Коллекция пуста. Нечего выводить."), "Должно выводиться сообщение о пустой коллекции");
    }

    @Test
    void printCollection_shouldPrintEmptyMessage_whenCollectionIsNull() {
        String output = captureOutput(() -> printer.printCollection(null));
        assertTrue(output.contains("Коллекция пуста. Нечего выводить."), "Должно выводиться сообщение при null");
    }

    @Test
    void printOccurrences_shouldPrintResult_whenCountIsPositive() {
        String output = captureOutput(() -> printer.printOccurrences(5));
        assertTrue(output.contains("РЕЗУЛЬТАТ ПОДСЧЁТА: 5 вхождений"), "Должен быть точный вывод результата");
    }

    @Test
    void printOccurrences_shouldPrintZero_whenCountIsZero() {
        String output = captureOutput(() -> printer.printOccurrences(0));
        assertTrue(output.contains("РЕЗУЛЬТАТ ПОДСЧЁТА: 0 вхождений"), "Должен выводиться ноль");
    }


    private static String captureOutput(Runnable action) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(buffer, true, StandardCharsets.UTF_8));
        try {
            action.run();
        } finally {
            System.setOut(originalOut);
        }
        return buffer.toString(StandardCharsets.UTF_8);
    }
}