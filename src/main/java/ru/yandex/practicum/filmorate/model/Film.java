package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Film {

    private int id;

    private final Set<Integer> likes = new HashSet<>();
    @NotNull
    @NotBlank
    private String name;
    @Size(max = 200)
    private String description;
    private LocalDate releaseDate;
    @Min(1)
    private int duration;

    public int getLikesCount() {
        return likes.size();
    }

    public void addLike(User user) {
        likes.add(user.getId());
    }

    public void deleteLike(int userId) {
        likes.remove(userId);
    }
}
