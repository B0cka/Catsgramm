package ru.yandex.practicum.catsgram.model;

import lombok.*;

@EqualsAndHashCode(of = { "id" })
@ToString
@Data
@Getter
@Setter
public class Image {

    Long id;
    long postId;
    String originalFileName;
    String filePath;

}
