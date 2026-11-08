package ru.aston.team.one.sortingapp;

import com.fasterxml.jackson.core.type.TypeReference;
import ru.aston.team.one.sortingapp.repository.Repository;
import ru.aston.team.one.sortingapp.counter.OccurrenceCounter;
import ru.aston.team.one.sortingapp.enums.Field;
import ru.aston.team.one.sortingapp.enums.SourceType;
import ru.aston.team.one.sortingapp.input.ConsoleInput;
import ru.aston.team.one.sortingapp.input.StudentInput;
import ru.aston.team.one.sortingapp.menu.Menu;
import ru.aston.team.one.sortingapp.printer.ConsolePrinter;
import ru.aston.team.one.sortingapp.provider.ConsoleProvider;
import ru.aston.team.one.sortingapp.provider.FileProvider;
import ru.aston.team.one.sortingapp.provider.GeneratedProvider;
import ru.aston.team.one.sortingapp.provider.Provider;
import ru.aston.team.one.sortingapp.saver.JsonSaver;
import ru.aston.team.one.sortingapp.saver.Saver;
import ru.aston.team.one.sortingapp.service.Service;
import ru.aston.team.one.sortingapp.sort.AverageScoreSort;
import ru.aston.team.one.sortingapp.sort.EvenRecordBookNumberSort;
import ru.aston.team.one.sortingapp.sort.GroupNumberSort;
import ru.aston.team.one.sortingapp.sort.RecordBookNumberSort;
import ru.aston.team.one.sortingapp.sort.Sort;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        ConsoleInput consoleInput = new ConsoleInput();
        ConsolePrinter consolePrinter = new ConsolePrinter();
        StudentInput studentInput = new StudentInput(consoleInput);
        Repository repository = new Repository();

        Saver saver = new JsonSaver(new TypeReference<List<?>>() {});

        OccurrenceCounter occurrenceCounter = new OccurrenceCounter();

        Map<SourceType, Provider> providers = new HashMap<>();
        providers.put(SourceType.MANUAL, new ConsoleProvider(consoleInput, studentInput));
        providers.put(SourceType.RANDOM, new GeneratedProvider(consoleInput));
        providers.put(SourceType.FILE, new FileProvider(consoleInput));

        Map<Field, Sort> sorts = new HashMap<>();
        sorts.put(Field.GROUP_NUMBER, new GroupNumberSort());
        sorts.put(Field.AVERAGE_SCORE, new AverageScoreSort());
        sorts.put(Field.RECORD_BOOK_NUMBER, new RecordBookNumberSort());
        sorts.put(Field.EVEN_RECORD_BOOK_NUMBER, new EvenRecordBookNumberSort());

        Service service = new Service(
                repository,
                providers,
                sorts,
                consolePrinter,
                occurrenceCounter,
                studentInput,
                saver
        );

        Menu menu = new Menu(consoleInput, service);
        menu.start();
    }
}