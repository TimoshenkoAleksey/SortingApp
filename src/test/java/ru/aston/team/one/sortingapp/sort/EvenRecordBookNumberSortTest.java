package ru.aston.team.one.sortingapp.sort;

import org.junit.jupiter.api.Test;
import ru.aston.team.one.sortingapp.collection.CustomList;
import ru.aston.team.one.sortingapp.model.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class EvenRecordBookNumberSortTest {

    @Test
    void shouldSortOnlyEvenRecordBookNumbersAndKeepOddInPlace() {
        Student odd5 = new Student.Builder().groupNumber(1).averageNumber(4.0).recordBookNumber(5).build();
        Student even8 = new Student.Builder().groupNumber(1).averageNumber(4.0).recordBookNumber(8).build();
        Student odd3 = new Student.Builder().groupNumber(1).averageNumber(4.0).recordBookNumber(3).build();
        Student even2 = new Student.Builder().groupNumber(1).averageNumber(4.0).recordBookNumber(2).build();
        Student odd7 = new Student.Builder().groupNumber(1).averageNumber(4.0).recordBookNumber(7).build();
        Student even6 = new Student.Builder().groupNumber(1).averageNumber(4.0).recordBookNumber(6).build();

        CustomList<Student> list = new CustomList<>();
        list.add(odd5);
        list.add(even8);
        list.add(odd3);
        list.add(even2);
        list.add(odd7);
        list.add(even6);

        EvenRecordBookNumberSort sorter = new EvenRecordBookNumberSort();
        CustomList<Student> sortedList = sorter.sort(list);

        assertEquals(6, sortedList.size(), "Размер списка не должен меняться");


        assertSame(odd5, sortedList.get(0), "Нечетный 5 сдвинулся");
        assertEquals(2, sortedList.get(1).recordBookNumber(), "Четные не отсортированы");
        assertSame(odd3, sortedList.get(2), "Нечетный 3 сдвинулся");
        assertEquals(6, sortedList.get(3).recordBookNumber(), "Четные не отсортированы");
        assertSame(odd7, sortedList.get(4), "Нечетный 7 сдвинулся");
        assertEquals(8, sortedList.get(5).recordBookNumber(), "Четные не отсортированы");
    }

    @Test
    void shouldHandleEmptyList() {
        CustomList<Student> list = new CustomList<>();
        CustomList<Student> result = new EvenRecordBookNumberSort().sort(list);
        assertEquals(0, result.size());
    }

    @Test
    void shouldKeepListUnchangedWhenAllRecordBookNumbersAreOdd() {
        Student first = new Student.Builder().groupNumber(1).averageNumber(4.0).recordBookNumber(5).build();
        Student second = new Student.Builder().groupNumber(1).averageNumber(4.0).recordBookNumber(7).build();

        CustomList<Student> list = new CustomList<>();
        list.add(first);
        list.add(second);

        CustomList<Student> result = new EvenRecordBookNumberSort().sort(list);

        assertSame(first, result.get(0));
        assertSame(second, result.get(1));
    }
}