package ru.yandex.practicum.filmorate.storage.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class InMemoryUserStorage implements UserStorage {

    private Map<Integer, User> users = new HashMap<>();

    public Map<Integer, User> getUsers() {
        return users;
    }

    public void setUsers(Map<Integer, User> users) {
        this.users = users;
    }

    private int lastId = 1;

    public int getLastId() {
        return lastId;
    }

    public void setLastId(int lastId) {
        this.lastId = lastId;
    }

    @GetMapping
    private Collection<User> getAllUsers() {
        log.debug(users.toString().toUpperCase());
        log.debug("users {} has been added", users.toString().toUpperCase());
        return users.values();
    }

    @PostMapping
    private User addUser(@Valid User user) {
        validate(user);
        user.setUserId(lastId++);
        users.put(user.getUserId(), user);
        log.debug("user {} has been added", users.toString().toUpperCase());
        return user;
    }

    @PutMapping
    private User updateUser( User user) {
        validate(user);
        users.put(user.getUserId(), user);
        log.debug("users {} has been updated", users.toString().toUpperCase());
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
