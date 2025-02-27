package ru.yandex.practicum.catsgram.model;

import java.time.Instant;

import lombok.*;

@EqualsAndHashCode(of = { "email" })
@ToString
@Data
@Getter
@Setter
public class User {

    Long id;
    String username;
    String email;
    String password;
    Instant registrationDate;

}
