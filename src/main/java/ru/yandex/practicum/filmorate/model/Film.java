package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Film {
    int filmId;
    @NotNull
    @NotBlank
    String name;
    String description;
    LocalDate releaseDate;
    @Min(1)
    int duration;
}
