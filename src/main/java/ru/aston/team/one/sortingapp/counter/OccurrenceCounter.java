package ru.aston.team.one.sortingapp.counter;

import ru.aston.team.one.sortingapp.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class OccurrenceCounter {

    public int count(List<Student> students, Student target) {
        if (students == null) {
            throw new IllegalArgumentException("Коллекция не может быть null");
        }
        if (target == null) {
            throw new IllegalArgumentException("Искомый студент не может быть null");
        }
        if (students.isEmpty()) {
            return 0;
        }
        int size = students.size();
        int threadCount = Math.max(1, Math.min(Runtime.getRuntime().availableProcessors(), size));
        int chunkSize = (int) Math.ceil((double) size / threadCount);
        List<Chunk> chunks = IntStream.range(0, threadCount)
                .mapToObj(i -> {
                    int start = i * chunkSize;
                    int end = Math.min(start + chunkSize, size);
                    return new Chunk(start, end);
                })
                .filter(chunk -> !chunk.isEmpty())
                .toList();
        AtomicInteger counter = new AtomicInteger(0);
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        try {
            List<Future<?>> futures = new ArrayList<>();
            for (Chunk chunk : chunks) {
                futures.add(executor.submit(() -> {
                    List<Student> subList = students.subList(chunk.start, chunk.end);
                    for (Student student : subList) {
                        if (student.equals(target)) {
                            counter.incrementAndGet();
                        }
                    }
                }));
            }
            for (Future<?> future : futures) {
                try {
                    future.get();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Поток был прерван во время подсчета", e);
                } catch (ExecutionException e) {
                    throw new RuntimeException("Ошибка при выполнении задачи подсчета", e.getCause());
                }
            }
            return counter.get();
        } finally {
            executor.shutdown();
        }
    }

    private record Chunk(int start, int end) {
        boolean isEmpty() {
            return start >= end;
        }
    }
}
