package ru.yandex.practicum.catsgram.model;

;
import java.time.LocalDate;

import lombok.*;

@EqualsAndHashCode(of = { "email" })
@ToString
@Data
@Getter
@Setter
public class User {

    long id;
    String username;
    String email;
    String password;
    LocalDate registrationDate;

}
