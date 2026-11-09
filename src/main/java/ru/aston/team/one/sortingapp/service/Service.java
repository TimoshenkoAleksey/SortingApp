package ru.aston.team.one.sortingapp.service;

import ru.aston.team.one.sortingapp.collection.CustomList;
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

import java.util.List;
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
        this.repository = Objects.requireNonNull(repository, "repository должен бы не null");
        this.providers = Objects.requireNonNull(providers, "providers должен бы не null");
        this.sorts = Objects.requireNonNull(sorts, "sorts должен бы не null");
        this.consolePrinter = Objects.requireNonNull(consolePrinter, "consolePrinter должен бы не null");
        this.occurrenceCounter = Objects.requireNonNull(occurrenceCounter, "occurrenceCounter должен бы не null");
        this.studentInput = Objects.requireNonNull(studentInput, "studentInput должен бы не null");
        this.saver = Objects.requireNonNull(saver, "saver должен бы не null");
    }

    public void inputCollection(SourceType sourceType) {
        Provider provider = providers.get(sourceType);
        if (provider == null) {
            throw new IllegalArgumentException("Неизвестный источник: " + sourceType);
        }
        List<Student> students = provider.provide();
        repository.addAll(students);
    }

    public void printCollection() {
        CustomList<Student> collection = repository.getCollection();
        consolePrinter.printCollection(collection);
    }

    public void sortCollection(Field field) {
        Sort sort = sorts.get(field);
        if (sort == null) {
            throw new IllegalArgumentException("Неизвестное поле: " + field);
        }
        CustomList<Student> collection = repository.getCollection();
        CustomList<Student> sortedCollection = sort.sort(collection);
        repository.setCollection(sortedCollection);
    }

    public void saveToFile() {
        CustomList<Student> collection = repository.getCollection();
        saver.save(collection);
    }

    public void countOccurrences() {
        Student target = studentInput.readStudent();
        CustomList<Student> collection = repository.getCollection();
        int count = occurrenceCounter.count(collection, target);
        consolePrinter.printOccurrences(count);
    }
}