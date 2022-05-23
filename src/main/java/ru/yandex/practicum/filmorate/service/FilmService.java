package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.stream.Stream;

@Slf4j
@Service
public class FilmService {

    private final FilmStorage filmStorage;
    private final UserStorage userStorage;

    @Autowired
    public FilmService(FilmStorage inMemoryFilmStorage, UserStorage userStorage) {
        this.filmStorage = inMemoryFilmStorage;
        this.userStorage = userStorage;
    }


    public Stream<Film> getMostPopular(int count) {
        return filmStorage.getAllFilms().stream()
                .sorted((a, b) -> b.getLikesCount() - a.getLikesCount())
                .limit(count);
    }

    public void addLike(int filmId, int userId) {
        userStorage.getById(userId).like(filmStorage.getById(filmId));
    }

    public void deleteLike(int filmId, int userId) {
        userStorage.getById(userId).unlike(filmStorage.getById(filmId));
    }
}
