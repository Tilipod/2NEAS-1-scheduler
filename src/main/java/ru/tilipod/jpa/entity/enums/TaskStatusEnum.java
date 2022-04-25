package ru.tilipod.jpa.entity.enums;

public enum TaskStatusEnum {
    CREATED, // Создана
    ANALYSIS, // В анализе (парсинг)
    ANALYZED, // Анализ закончен
    DISTRIBUTING, // В процессе выгрузки датасетов
    DISTRIBUTED, // Выгрузка датасетов закончена
    TRAINING, // В обучении
    TRAINED, // Обучена
    CONFIRMED, // Подтверждена клиентом
    STOPPED, // Остановлена клиентом
    ANALYSIS_ERROR, // Ошибка анализа (парсинга)
    TRAINING_ERROR, // Ошибка обучения
    DISTRIBUTE_ERROR, // Ошибка получения выборки
    SYSTEM_ERROR // Системная ошибка
}
