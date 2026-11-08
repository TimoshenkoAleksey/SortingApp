package ru.aston.team.one.sortingapp.sort;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.aston.team.one.sortingapp.collection.CustomList;
import ru.aston.team.one.sortingapp.model.Student;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SortTest {

    @Test
    @DisplayName("GroupNumberSort сортирует по возрастанию номера группы")
    void sort_shouldOrderByGroupNumber_whenGroupNumbersDiffer() {
        Student first = new Student(1, 4.0, 300);
        Student second = new Student(2, 3.0, 100);
        Student third = new Student(3, 5.0, 200);
        Student fourth = new Student(4, 4.5, 400);

        CustomList<Student> result = new GroupNumberSort()
                .sort(toCustomList(third, fourth, first, second));

        assertEquals(List.of(first, second, third, fourth), result);
    }

    @Test
    @DisplayName("AverageScoreSort сортирует по возрастанию среднего балла")
    void sort_shouldOrderByAverageScore_whenScoresDiffer() {
        Student first = new Student(3, 2.5, 300);
        Student second = new Student(1, 3.8, 100);
        Student third = new Student(4, 4.2, 200);
        Student fourth = new Student(2, 5.0, 400);

        CustomList<Student> result = new AverageScoreSort()
                .sort(toCustomList(fourth, second, first, third));

        assertEquals(List.of(first, second, third, fourth), result);
    }

    @Test
    @DisplayName("RecordBookNumberSort сортирует по возрастанию номера зачётки")
    void sort_shouldOrderByRecordBookNumber_whenNumbersDiffer() {
        Student first = new Student(3, 4.0, 101);
        Student second = new Student(1, 3.0, 202);
        Student third = new Student(4, 5.0, 303);
        Student fourth = new Student(2, 4.5, 404);

        CustomList<Student> result = new RecordBookNumberSort()
                .sort(toCustomList(second, fourth, first, third));

        assertEquals(List.of(first, second, third, fourth), result);
    }

    private CustomList<Student> toCustomList(Student... students) {
        return new CustomList<>(List.of(students));
    }
}