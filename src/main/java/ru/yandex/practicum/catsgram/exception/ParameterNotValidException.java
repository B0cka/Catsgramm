package ru.yandex.practicum.catsgram.exception;

public class ParameterNotValidException extends IllegalArgumentException {

    String parameter;
    String reason;

    public ParameterNotValidException(String parameter, String reason) {
        this.parameter = parameter;
        this.reason = reason;
    }
}
