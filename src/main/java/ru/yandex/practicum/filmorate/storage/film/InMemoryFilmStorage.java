package ru.yandex.practicum.filmorate.storage.film;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class InMemoryFilmStorage implements FilmStorage {

    private Map<Integer, Film> films = new HashMap<>();

    public Map<Integer, Film> getFilms() {
        return films;
    }

    public void setFilms(Map<Integer, Film> films) {
        this.films = films;
    }

    private int lastId = 1;

    public int getLastId() {
        return lastId;
    }

    public void setLastId(int lastId) {
        this.lastId = lastId;
    }

    @GetMapping
    private Collection<Film> getAllFilms() {
        log.debug("films {} has been added", films.toString().toUpperCase());
        return films.values();
    }

    @PostMapping
    private Film addFilm(@Valid Film film) {
        validate(film);
        film.setFilmId(lastId++);
        films.put(film.getFilmId(), film);
        log.debug("film {} has been added", film.getName().toUpperCase());
        return film;
    }

    @PutMapping
    private Film updateFilm( Film film) {
        validate(film);
        films.put(film.getFilmId(), film);
        log.debug("film {} has been updated", film.getName().toUpperCase());
        return film;
    }
    private void validate(Film film) {
        if (film.getName().equals("")) {
            throw new ValidationException("The film name should be added");
        }
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
