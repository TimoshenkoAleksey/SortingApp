package ru.aston.team.one.sortingapp.saver;

import java.util.List;

public interface Saver {
    <T> void save(List<T> list);
}
