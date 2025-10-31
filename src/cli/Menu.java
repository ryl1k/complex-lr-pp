package cli;

import java.util.*;

/**
 * Клас Menu управляє командами та взаємодією з користувачем
 * Реалізує багаторівневу структуру меню
 */
public class Menu implements Command {
    private String title;
    private Map<String, Command> commands;
    private Scanner scanner;
    private boolean running;

    public Menu(String title) {
        this.title = title;
        this.commands = new LinkedHashMap<>();
        this.scanner = new Scanner(System.in);
        this.running = false;
    }

    /**
     * Додає команду до меню
     * @param key ключ команди
     * @param command об'єкт команди
     */
    public void addCommand(String key, Command command) {
        commands.put(key, command);
    }

    /**
     * Видаляє команду з меню
     * @param key ключ команди
     */
    public void removeCommand(String key) {
        commands.remove(key);
    }

    /**
     * Повертає опис меню
     */
    @Override
    public String getDesc() {
        return title;
    }

    /**
     * Запускає меню (основний цикл)
     * Параметр не використовується для кореневого меню
     */
    @Override
    public void execute(String parameter) {
        run();
    }

    /**
     * Запускає цикл меню
     * Циклічно запитує користувача про команди та виконує їх
     */
    public void run() {
        running = true;
        while (running) {
            displayMenu();
            System.out.print("Введіть команду: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("help")) {
                showHelp();
                continue;
            }

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Вихід з меню...");
                running = false;
                continue;
            }

            // Розділяємо введення на ключ команди і параметри
            String[] parts = input.split(" ", 2);
            String commandKey = parts[0];
            String parameter = parts.length > 1 ? parts[1] : "";

            Command command = commands.get(commandKey);
            if (command != null) {
                try {
                    command.execute(parameter);
                } catch (Exception e) {
                    System.out.println("Помилка при виконанні команди: " + e.getMessage());
                }
            } else {
                System.out.println("Невідома команда: " + commandKey);
                System.out.println("Введіть 'help' для списку команд або 'exit' для виходу.");
            }
        }
    }

    /**
     * Виводить меню з усіма доступними командами
     */
    private void displayMenu() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║  " + centerText(title, 32) + "  ║");
        System.out.println("╚════════════════════════════════════╝");
        System.out.println("Доступні команди:");
        for (String key : commands.keySet()) {
            Command cmd = commands.get(key);
            System.out.println("  " + String.format("%-15s", key) + " - " + cmd.getDesc());
        }
        System.out.println("  " + String.format("%-15s", "help") + " - Показати цю справку");
        System.out.println("  " + String.format("%-15s", "exit") + " - Вийти з меню");
    }

    /**
     * Показує довідку з описом команд
     */
    private void showHelp() {
        System.out.println("\n=== ДОВІДКА ===");
        System.out.println("Доступні команди:");
        for (String key : commands.keySet()) {
            Command cmd = commands.get(key);
            System.out.println("  " + key + " - " + cmd.getDesc());
        }
        System.out.println("  help - Показати цю справку");
        System.out.println("  exit - Вийти з меню");
        System.out.println("================\n");
    }

    /**
     * Центрує текст в межах заданої ширини
     */
    private String centerText(String text, int width) {
        if (text.length() >= width) {
            return text.substring(0, width);
        }
        int padding = (width - text.length()) / 2;
        return String.format("%" + (text.length() + padding) + "s%-" + (width - text.length() - padding) + "s", text, "");
    }

    /**
     * Зупиняє цикл меню
     */
    public void stop() {
        running = false;
    }

    /**
     * Повертає Scanner для введення даних
     */
    public Scanner getScanner() {
        return scanner;
    }

    /**
     * Повертає Map команд
     */
    public Map<String, Command> getCommands() {
        return commands;
    }
}
