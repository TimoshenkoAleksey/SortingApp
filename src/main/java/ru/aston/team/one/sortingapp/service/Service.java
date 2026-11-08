package ru.aston.team.one.sortingapp.service;

import ru.aston.team.one.sortingapp.counter.OccurrenceCounter;
import ru.aston.team.one.sortingapp.enums.Field;
import ru.aston.team.one.sortingapp.enums.SourceType;
import ru.aston.team.one.sortingapp.input.StudentInput;
import ru.aston.team.one.sortingapp.model.Student;
import ru.aston.team.one.sortingapp.printer.ConsolePrinter;
import ru.aston.team.one.sortingapp.provider.Provider;
import ru.aston.team.one.sortingapp.repository.Repository;
import ru.aston.team.one.sortingapp.saver.Saver;
import ru.aston.team.one.sortingapp.sort.Sort;

import java.util.Map;
import java.util.Objects;

public class Service {

    private final Repository repository;
    private final Map<SourceType, Provider> providers;
    private final Map<Field, Sort> sorts;
    private final ConsolePrinter consolePrinter;
    private final OccurrenceCounter occurrenceCounter;
    private final StudentInput studentInput;
    private final Saver saver;

    public Service(Repository repository,
                   Map<SourceType, Provider> providers,
                   Map<Field, Sort> sorts,
                   ConsolePrinter consolePrinter,
                   OccurrenceCounter occurrenceCounter,
                   StudentInput studentInput,
                   Saver saver) {
        this.repository = Objects.requireNonNull(repository, "repository не должен быть null");
        this.providers = Objects.requireNonNull(providers, "providers не должен быть null");
        this.sorts = Objects.requireNonNull(sorts, "sorts не должен быть null");
        this.consolePrinter = Objects.requireNonNull(consolePrinter, "consolePrinter не должен быть null");
        this.occurrenceCounter =
                Objects.requireNonNull(occurrenceCounter, "occurrenceCounter не должен быть null");
        this.studentInput = Objects.requireNonNull(studentInput, "studentInput не должен быть null");
        this.saver = Objects.requireNonNull(saver, "saver не должен быть null");
    }

    public void inputCollection(SourceType sourceType) {
        Provider provider = providers.get(sourceType);
        if (provider == null) {
            throw new IllegalArgumentException("Неизвестный источник: " + sourceType);
        }
        repository.addAll(provider.provide());
    }

    public void printCollection() {
        consolePrinter.printCollection(repository.getCollection());
    }

    public void sortCollection(Field field) {
        Sort sort = sorts.get(field);
        if (sort == null) {
            throw new IllegalArgumentException("Неизвестное поле: " + field);
        }
        repository.setCollection(sort.sort(repository.getCollection()));
    }

    public void saveToFile() {
        saver.save(repository.getCollection());
    }

    public void countOccurrences() {
        Student target = studentInput.readStudent();
        int count = occurrenceCounter.count(repository.getCollection(), target);
        consolePrinter.printOccurrences(count);
    }
}
