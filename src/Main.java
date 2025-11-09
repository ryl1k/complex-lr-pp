import cli.Menu;
import cli.commands.*;
import model.Train;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        initializeLogging();
        logger.info("========== APPLICATION START ==========");

        System.out.println("====================================");
        System.out.println("   PASSENGER TRAIN MANAGER          ");
        System.out.println("====================================\n");

        Train train = new Train("New Train", "Unknown");
        Scanner scanner = new Scanner(System.in);
        logger.info("Created new train: " + train.getName());

        Menu mainMenu = new Menu("MAIN MENU");
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

        logger.info("Starting main menu");
        mainMenu.run();

        logger.info("========== APPLICATION SHUTDOWN ==========");
        scanner.close();
    }

    // Load logging config from file - falls back to defaults if file not found
    private static void initializeLogging() {
        try (FileInputStream configFile = new FileInputStream("logging.properties")) {
            LogManager.getLogManager().readConfiguration(configFile);
            System.out.println("Logging initialized successfully\n");
        } catch (IOException e) {
            System.err.println("Failed to load logging.properties, using default settings");
            System.err.println("Error: " + e.getMessage() + "\n");
        }
    }
}
