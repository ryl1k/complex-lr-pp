package cli;

/**
 * Інтерфейс для реалізації паттерну Command
 * Кожна команда повинна описати себе і виконати задачу
 */
public interface Command {
    /**
     * Повертає опис команди для відображення в меню
     * @return опис команди
     */
    String getDesc();

    /**
     * Виконує команду
     * @param parameter параметр для команди (значення ключа)
     */
    void execute(String parameter);
}
