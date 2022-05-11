package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.InMemoryFilmStorage;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {

//    private Map<Integer, Film> films = new HashMap<>();
//
//    public Map<Integer, Film> getFilms() {
//        return films;
//    }
//
//    public void setFilms(Map<Integer, Film> films) {
//        this.films = films;
//    }
//
//    private int lastId = 1;

    private final InMemoryFilmStorage inMemoryFilmStorage;
    @Autowired
    public FilmController (InMemoryFilmStorage inMemoryFilmStorage) {
        this.inMemoryFilmStorage = inMemoryFilmStorage;
    }

    @GetMapping
    private Collection<Film> getAllFilms() {
        log.debug("films {} has been added", inMemoryFilmStorage.getFilms().toString().toUpperCase());
        return inMemoryFilmStorage.getFilms().values();
    }

    @PostMapping
    private Film addFilm(@Valid @RequestBody Film film) {
//        validate(film);
        film.setFilmId(inMemoryFilmStorage.getLastId() + 1);
        inMemoryFilmStorage.getFilms().put(film.getFilmId(), film);
        log.debug("film {} has been added", film.getName().toUpperCase());
        return film;
    }

    @PutMapping
    private Film updateFilm(@RequestBody Film film) {
//        validate(film);
        inMemoryFilmStorage.getFilms().put(film.getFilmId(), film);
        log.debug("film {} has been updated", film.getName().toUpperCase());
        return film;
    }


}
