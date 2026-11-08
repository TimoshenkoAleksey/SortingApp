package ru.aston.team.one.sortingapp.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StudentTest {

    @Test
    @DisplayName("Builder создаёт студента с корректными данными")
    void build_shouldCreateStudent_whenAllFieldsAreValid() {
        Student student = new Student.Builder().groupNumber(5).averageNumber(4.5).recordBookNumber(1234).build();
        assertEquals(5, student.groupNumber());
        assertEquals(4.5, student.averageNumber());
        assertEquals(1234, student.recordBookNumber());
    }

    @Test
    @DisplayName("Номер группы должен быть больше 0")
    void build_shouldThrowException_whenGroupNumberIsNotPositive() {
        assertThrows(IllegalArgumentException.class, () -> new Student.Builder()
                .groupNumber(0).averageNumber(4.3).recordBookNumber(5252).build());
    }

    @Test
    @DisplayName("Средний балл не может быть отрицательным")
    void build_shouldThrowException_whenAverageNumberIsNegative(){
        assertThrows(IllegalArgumentException.class, () -> new Student.Builder()
                .groupNumber(6).averageNumber(-0.1).recordBookNumber(4242).build());
    }

    @Test
    @DisplayName("Средний балл не может быть больше 5.0")
    void build_shouldThrowException_whenAverageNumberIsGreaterThanMax() {
        assertThrows(IllegalArgumentException.class, () -> new Student.Builder()
                .groupNumber(7).averageNumber(5.2).recordBookNumber(6767).build());
    }

    @Test
    @DisplayName("Номер зачетной книжки должен быть больше 0")
    void build_shouldThrowException_whenRecordBookNumberIsNotPositive() {
        assertThrows(IllegalArgumentException.class, () -> new Student.Builder()
                .groupNumber(10).averageNumber(3.5).recordBookNumber(0).build());
    }
}
