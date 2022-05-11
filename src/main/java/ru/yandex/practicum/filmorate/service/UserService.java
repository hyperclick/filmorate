package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;

@Slf4j
@Service
public class UserService {

    private Collection<User> getMutualFriends(User user, User user2) {
        Collection<User> mutualFriends = null;
        for (User u: user.getFriends()) {
            for (User u2: user2.getFriends()) {
                if (u == u2) {
                    assert false;
                    mutualFriends.add(u);
                }
            }
        }
        return mutualFriends;
    }

    private Collection<User> addFriend(User user, User user2) {
        if (!(user.getFriends().contains(user2.getUserId()))) {
            user.getFriends().add(user2);
            user2.getFriends().add(user);
        } else {
            log.info("User " + user + " is in your friends list already");
        }
        return user.getFriends();
    }

    private Collection<User> deleteFriend(User user, User user2) {
        user.getFriends().remove(user2);
        return user.getFriends();
    }
}
