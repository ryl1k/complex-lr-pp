package cli;

import java.util.*;

// Manages commands and user interaction with multi-level menu structure
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

    public void addCommand(String key, Command command) {
        commands.put(key, command);
    }

    public void removeCommand(String key) {
        commands.remove(key);
    }

    @Override
    public String getDesc() {
        return title;
    }

    @Override
    public void execute(String parameter) {
        run();
    }

    public void run() {
        running = true;
        while (running) {
            displayMenu();
            System.out.print("Enter command: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("help")) {
                showHelp();
                continue;
            }

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting menu...");
                running = false;
                continue;
            }

            // Split input into command key and parameters
            String[] parts = input.split(" ", 2);
            String commandKey = parts[0];
            String parameter = parts.length > 1 ? parts[1] : "";

            Command command = commands.get(commandKey);
            if (command != null) {
                try {
                    command.execute(parameter);
                } catch (Exception e) {
                    System.out.println("Error executing command: " + e.getMessage());
                }
            } else {
                System.out.println("Unknown command: " + commandKey);
                System.out.println("Type 'help' for command list or 'exit' to quit.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n====================================");
        System.out.println("  " + centerText(title, 32) + "  ");
        System.out.println("====================================");
        System.out.println("Available commands:");
        for (String key : commands.keySet()) {
            Command cmd = commands.get(key);
            System.out.println("  " + String.format("%-15s", key) + " - " + cmd.getDesc());
        }
        System.out.println("  " + String.format("%-15s", "help") + " - Show this help");
        System.out.println("  " + String.format("%-15s", "exit") + " - Exit menu");
    }

    private void showHelp() {
        System.out.println("\n=== HELP ===");
        System.out.println("Available commands:");
        for (String key : commands.keySet()) {
            Command cmd = commands.get(key);
            System.out.println("  " + key + " - " + cmd.getDesc());
        }
        System.out.println("  help - Show this help");
        System.out.println("  exit - Exit menu");
        System.out.println("================\n");
    }

    private String centerText(String text, int width) {
        if (text.length() >= width) {
            return text.substring(0, width);
        }
        int padding = (width - text.length()) / 2;
        return String.format("%" + (text.length() + padding) + "s%-" + (width - text.length() - padding) + "s", text, "");
    }

    public void stop() {
        running = false;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public Map<String, Command> getCommands() {
        return commands;
    }
}
