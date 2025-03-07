package ru.yandex.practicum.catsgram.model;

import java.time.LocalDate;

import lombok.*;

@EqualsAndHashCode(of = { "id" })
@ToString
@Data
@Getter
@Setter
public class Post {

    Long id;
    long authorId;
    String description;
    LocalDate postDate;

}
