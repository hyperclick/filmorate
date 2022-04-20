package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    Map<Integer, User> users = new HashMap<>();
    private int lastId = 1;


    @GetMapping
    private Collection<User> getAllUsers() {
        log.debug(users.toString().toUpperCase());
        return users.values();
    }

    @PostMapping
    private User addUser(@Valid @RequestBody User user) {
        validate(user);
        user.setUserId(lastId++);
        users.put(user.getUserId(), user);
        log.debug("user " + user.getName().toUpperCase() + " has been added");
        return user;
    }

    @PutMapping
    private User updateUser(@RequestBody User user) {
        validate(user);
        users.put(user.getUserId(), user);
        log.debug("user" + user.getName() + " has been updated");
        return user;
    }

    private void validate(User user) {
        if (user.getEmail() == null || user.getEmail().contains(" ") || !(user.getEmail().contains("@"))) {
            throw new ValidationException("The user email must include @, should be without spaces " +
                    "and shouldn't be blank");
        }
        if (user.getLogin() == null || user.getLogin().contains(" ")) {
            throw new ValidationException("The user login can't be empty or contains spaces");
        }
        if (user.getName() == null || user.getName().equals(" ")) {
            user.setName(user.getLogin());
        }
        if (user.getBirthday().isAfter(LocalDate.now())) {
            throw new ValidationException("The user birthday can't be after " + LocalDate.now());
        }
    }
}
