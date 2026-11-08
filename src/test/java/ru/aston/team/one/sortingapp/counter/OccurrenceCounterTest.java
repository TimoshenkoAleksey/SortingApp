package ru.aston.team.one.sortingapp.counter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.aston.team.one.sortingapp.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OccurrenceCounterTest {

    private OccurrenceCounter counter;
    private Student target;
    private Student otherStudent;

    @BeforeEach
    void setUp() {
        counter = new OccurrenceCounter();
        target = new Student.Builder()
                .groupNumber(1)
                .averageNumber(4.5)
                .recordBookNumber(100)
                .build();
        otherStudent = new Student.Builder()
                .groupNumber(2)
                .averageNumber(3.0)
                .recordBookNumber(200)
                .build();
    }

    @Test
    void count_whenTargetExists_shouldReturnCorrectCount() {
        // Создаем копию target (другой экземпляр с теми же значениями),
        // чтобы проверить, что подсчет идет через .equals(), а не через ==
        Student targetCopy = new Student.Builder()
                .groupNumber(1)
                .averageNumber(4.5)
                .recordBookNumber(100)
                .build();

        List<Student> students = new ArrayList<>(List.of(target, otherStudent, targetCopy, target));

        int result = counter.count(students, target);
        assertEquals(3, result, "Должно быть найдено 3 вхождения (включая копию по equals)");
    }

    @Test
    void count_whenTargetNotFound_shouldReturnZero() {
        List<Student> students = new ArrayList<>();
        students.add(otherStudent);

        int result = counter.count(students, target);
        assertEquals(0, result, "Должно вернуться 0, так как элемента нет");
    }

    @Test
    void count_whenCollectionIsEmpty_shouldReturnZero() {
        List<Student> students = new ArrayList<>();

        int result = counter.count(students, target);
        assertEquals(0, result, "Должно вернуться 0 для пустой коллекции");
    }

    @Test
    void count_whenCollectionIsNull_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            counter.count(null, target);
        }, "Должно выбрасываться IllegalArgumentException при null-коллекции");
    }

    @Test
    void count_whenTargetIsNull_shouldThrowException() {
        List<Student> students = new ArrayList<>();
        students.add(otherStudent);

        assertThrows(IllegalArgumentException.class, () -> {
            counter.count(students, null);
        }, "Должно выбрасываться IllegalArgumentException при null-цели");
    }

    @Test
    void count_whenCalledConcurrently_shouldBeConsistent() throws Exception {
        // Тест на Race Conditions (состояние гонки)
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            students.add(i % 2 == 0 ? target : otherStudent);
        }

        int threads = 8;
        int iterations = 1_000;
        ExecutorService pool = Executors.newFixedThreadPool(threads);

        try {
            List<Future<Integer>> results = new ArrayList<>();
            for (int t = 0; t < threads; t++) {
                results.add(pool.submit(() -> {
                    int last = 0;
                    for (int i = 0; i < iterations; i++) {
                        last = counter.count(students, target);
                    }
                    return last;
                }));
            }

            // В списке 100 элементов, из них 50 - это target. Ожидаем 50.
            for (Future<Integer> f : results) {
                assertEquals(50, f.get(5, TimeUnit.SECONDS), "Многопоточный подсчет дал сбой");
            }
        } finally {
            pool.shutdownNow();
        }
    }
}