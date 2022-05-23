package ru.yandex.practicum.filmorate.model;

import lombok.Getter;

@Getter
public class FriendshipRequest {
    private final User from;
    private final User to;
    private FriendshipRequestStatus status = FriendshipRequestStatus.Awaiting;

    public FriendshipRequest(User initiator, User to) {

        this.from = initiator;
        this.to = to;
    }
}
