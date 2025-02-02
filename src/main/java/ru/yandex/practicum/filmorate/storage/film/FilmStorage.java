package ru.yandex.practicum.filmorate.storage.film;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;

public interface FilmStorage {

    Collection<Film> getAllFilms();

    void addFilm(Film film);

    void updateFilm(Film film);

    Film getById(int filmId);
}
