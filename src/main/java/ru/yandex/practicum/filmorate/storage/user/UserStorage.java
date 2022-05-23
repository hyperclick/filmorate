package ru.yandex.practicum.filmorate.storage.user;

import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;

public interface UserStorage {

    public Collection<User> getAllUsers();

    public void addUser(User user);

    public void updateUser(User user);

    User getById(Integer userId);

}
