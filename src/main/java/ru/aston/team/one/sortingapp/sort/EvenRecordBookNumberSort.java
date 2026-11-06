package ru.aston.team.one.sortingapp.sort;

import ru.aston.team.one.sortingapp.collection.CustomList;
import ru.aston.team.one.sortingapp.comparator.RecordBookNumberComparator;
import ru.aston.team.one.sortingapp.model.Student;

/**
 * Сортирует только студентов с ЧЁТНЫМ номером зачётной книжки.
 * Нечётные остаются на своих исходных местах.
 */
public class EvenRecordBookNumberSort implements Sort {

    private final RecordBookNumberComparator comparator = new RecordBookNumberComparator();

    @Override
    public CustomList<Student> sort(CustomList<Student> list) {
        int size = list.size();

        // 1. Собираем чётные элементы и запоминаем их индексы
        Student[] evenStudents = new Student[size];
        int[] evenIndices = new int[size];
        int evenCount = 0;

        for (int i = 0; i < size; i++) {
            Student s = list.get(i);
            if (s.recordBookNumber() % 2 == 0) {
                evenStudents[evenCount] = s;
                evenIndices[evenCount] = i;
                evenCount++;
            }
        }

        // 2. Сортируем только чётные элементы (ручной пузырёк)
        for (int i = 0; i < evenCount - 1; i++) {
            for (int j = 0; j < evenCount - 1 - i; j++) {
                if (comparator.compare(evenStudents[j], evenStudents[j + 1]) > 0) {
                    Student temp = evenStudents[j];
                    evenStudents[j] = evenStudents[j + 1];
                    evenStudents[j + 1] = temp;
                }
            }
        }

        // 3. Возвращаем отсортированные чётные элементы на их исходные позиции
        for (int i = 0; i < evenCount; i++) {
            list.set(evenIndices[i], evenStudents[i]);
        }
        return list;
    }
}