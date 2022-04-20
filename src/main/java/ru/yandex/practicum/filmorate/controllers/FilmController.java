package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {

    Map<Integer, Film> films = new HashMap<>();

    private int lastId = 1;

    @GetMapping
    private Collection<Film> getAllFilms() {
        log.debug(films.toString().toUpperCase());
        return films.values();
    }

    @PostMapping
    private Film addFilm(@Valid @RequestBody Film film) {
        validate(film);
        film.setFilmId(lastId++);
        films.put(film.getFilmId(), film);
        log.debug("film " + film.getName().toUpperCase() + " has been added");
        return film;
    }

    @PutMapping
    private Film updateFilm(@RequestBody Film film) {
        validate(film);
        films.put(film.getFilmId(), film);
        log.debug("film" + film.getName().toUpperCase() + " has been updated");
        return film;
    }

    private void validate(Film film) {
        if (film.getDescription().length() > 200) {
            throw new ValidationException("The film description should be be less then 200 symbols");
        }
        if (film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            throw new ValidationException("The release date should be after 1985-12-28");
        }
        if (film.getDuration() < 1) {
            throw new ValidationException("Film duration should be positive ");
        }
    }
}
