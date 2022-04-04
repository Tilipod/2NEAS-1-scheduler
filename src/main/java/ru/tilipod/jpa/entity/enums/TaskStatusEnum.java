package ru.tilipod.jpa.entity.enums;

public enum TaskStatusEnum {
    CREATED, // Создана
    ANALYSIS, // В анализе (парсинг)
    ANALYZED, // Анализ закончен
    TRAINING, // В обучении
    TRAINED, // Обучена
    CONFIRMED, // Подтверждена клиентом
    ANALYSIS_ERROR, // Ошибка анализа (парсинга)
    TRAINING_ERROR, // Ошибка обучения
    DISTRIBUTE_ERROR, // Ошибка получения выборки
    SYSTEM_ERROR // Системная ошибка
}
