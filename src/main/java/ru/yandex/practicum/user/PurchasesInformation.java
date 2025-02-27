package ru.yandex.practicum.user;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.Date;

// Информация о покупках пользователя
@Data
@Value
@Builder
public class PurchasesInformation {
    // Дата последней покупки
    private Date lastPurchase;
    // Общее количество покупок
    private long purchaseCounts = 0;
}