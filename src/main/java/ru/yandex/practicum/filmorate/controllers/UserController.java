package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.InMemoryUserStorage;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

//    private Map<Integer, User> users = new HashMap<>();
//
//    public Map<Integer, User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Map<Integer, User> users) {
//        this.users = users;
//    }
//
//    private int lastId = 1;
    private final InMemoryUserStorage inMemoryUserStorage;

    public UserController(InMemoryUserStorage inMemoryUserStorage) {
        this.inMemoryUserStorage = inMemoryUserStorage;
    }

    @GetMapping
    private Collection<User> getAllUsers() {
        log.debug(inMemoryUserStorage.getUsers().toString().toUpperCase());
        log.debug("users {} has been added", inMemoryUserStorage.getUsers().toString().toUpperCase());
        return inMemoryUserStorage.getUsers().values();
    }

    @PostMapping
    private User addUser(@Valid @RequestBody User user) {
//        validate(user);
        user.setUserId(inMemoryUserStorage.getLastId() + 1);
        inMemoryUserStorage.getUsers().put(user.getUserId(), user);
        log.debug("user {} has been added", inMemoryUserStorage.getUsers().toString().toUpperCase());
        return user;
    }

    @PutMapping
    private User updateUser(@RequestBody User user) {
//        validate(user);
        inMemoryUserStorage.getUsers().put(user.getUserId(), user);
        log.debug("users {} has been updated", inMemoryUserStorage.getUsers().toString().toUpperCase());
        return user;
    }
}
