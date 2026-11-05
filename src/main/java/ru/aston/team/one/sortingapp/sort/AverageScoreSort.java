package ru.aston.team.one.sortingapp.sort;

import ru.aston.team.one.sortingapp.collection.CustomList;
import ru.aston.team.one.sortingapp.comparator.AverageScoreComparator;
import ru.aston.team.one.sortingapp.model.Student;

import java.util.Comparator;
import java.util.Objects;

public class AverageScoreSort implements Sort {
    private final Comparator<Student> comparator = new AverageScoreComparator();


    @Override
    public CustomList<Student> sort(CustomList<Student> list) {
        Objects.requireNonNull(list, "Список не должен быть пустым ");

        CustomList<Student> result = new CustomList<>();
        for (Student student : list) {
            result.add(student);
        }

        insertionsort(result);
        return result;
    }

    private void insertionsort(CustomList<Student> list) {
        for (int i = 1; i < list.size(); i++) {
            Student current = list.get(i);
            int j = i - 1;
            while (j >= 0 && comparator.compare(list.get(j), current) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, current);
        }
    }
}
