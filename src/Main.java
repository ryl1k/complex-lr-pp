import cli.Menu;
import cli.commands.*;
import model.Train;
import java.util.Scanner;

/**
 * Головна точка входу до програми
 * Програма для управління складом пасажирського поїзда
 * Використовує паттерн Command для керування операціями
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════╗");
        System.out.println("║   МЕНЕДЖЕР СКЛАДУ ПАСАЖИРСЬКОГО    ║");
        System.out.println("║              ПОЇЗДА               ║");
        System.out.println("╚════════════════════════════════════╝\n");

        // Створюємо поїзд та сканер для введення
        Train train = new Train("Новий поїзд", "Невідома");
        Scanner scanner = new Scanner(System.in);

        // Створюємо головне меню
        Menu mainMenu = new Menu("ГОЛОВНЕ МЕНЮ");

        // Додаємо команди до меню
        mainMenu.addCommand("load", new LoadCommand(train, scanner));
        mainMenu.addCommand("create", new CreateTrainCommand(train, scanner));
        mainMenu.addCommand("show", new ShowWagonsCommand(train, scanner));
        mainMenu.addCommand("add", new AddWagonCommand(train, scanner));
        mainMenu.addCommand("edit", new EditWagonCommand(train, scanner));
        mainMenu.addCommand("remove", new RemoveWagonCommand(train, scanner));
        mainMenu.addCommand("stats", new StatisticsCommand(train, scanner));
        mainMenu.addCommand("sort", new SortCommand(train, scanner));
        mainMenu.addCommand("find", new FindCommand(train, scanner));
        mainMenu.addCommand("save", new SaveCommand(train, scanner));

        // Запускаємо меню
        mainMenu.run();

        scanner.close();
    }
}
