package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.InMemoryFilmStorage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Slf4j
@Service
public class FilmService {

    private Collection<Film> theBestFilms;
    private final InMemoryFilmStorage inMemoryFilmStorage;

    public FilmService(InMemoryFilmStorage inMemoryFilmStorage) {
        this.inMemoryFilmStorage = inMemoryFilmStorage;
    }


    private Collection<Film> getTenTheBestFilms() {
        ArrayList<Integer> likes = new ArrayList<>();
        for (Film f : inMemoryFilmStorage.getFilms().values()) {
            likes.add(f.getLike());
        }
        Collections.sort(likes);
        for (int i = 0; i < 9; i++) {
            for (Film f : inMemoryFilmStorage.getFilms().values()) {
                if (likes.get(i) == f.getLike()) {
                    theBestFilms.add(f);
                }
            }
        }
        return theBestFilms;
    }

    private void addLike(Film film) {
        film.setLike(film.getLike() + 1);
    }

    private void deleteLike(Film film) {
        film.setLike(film.getLike() - 1);
    }
}
