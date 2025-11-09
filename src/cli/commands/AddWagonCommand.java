package cli.commands;

import cli.Menu;
import cli.InputValidator;
import model.*;
import java.util.Scanner;

public class AddWagonCommand extends TrainCommand {
    public AddWagonCommand(Train train, Scanner scanner) {
        super(train, scanner);
    }

    @Override
    public String getDesc() {
        return "Add wagon";
    }

    @Override
    public void execute(String parameter) {
        Menu wagonTypeMenu = new Menu("Select wagon type");
        wagonTypeMenu.addCommand("1", new AddSimpleWagonCommand(train, scanner));
        wagonTypeMenu.addCommand("2", new AddPassengerWagonCommand(train, scanner));
        wagonTypeMenu.addCommand("3", new AddLuxuryWagonCommand(train, scanner));
        wagonTypeMenu.run();
    }

    private static class AddSimpleWagonCommand implements cli.Command {
        private Train train;
        private Scanner scanner;

        public AddSimpleWagonCommand(Train train, Scanner scanner) {
            this.train = train;
            this.scanner = scanner;
        }

        @Override
        public String getDesc() {
            return "Simple wagon";
        }

        @Override
        public void execute(String parameter) {
            int id = InputValidator.readPositiveInt(scanner, "Enter wagon ID: ");
            String type = InputValidator.readNonEmptyString(scanner, "Enter wagon type: ");
            int passengers = InputValidator.readPositiveInt(scanner, "Enter passenger count: ");
            double luggage = InputValidator.readPositiveDouble(scanner, "Enter luggage weight (kg): ");
            int comfort = InputValidator.readIntInRange(scanner, "Enter comfort level (1-5): ", 1, 5);

            Wagon wagon = new PassengerWagon(id, type, passengers, luggage, comfort, false, false);
            train.addWagon(wagon);
            System.out.println("Simple wagon added!");
        }
    }

    private static class AddPassengerWagonCommand implements cli.Command {
        private Train train;
        private Scanner scanner;

        public AddPassengerWagonCommand(Train train, Scanner scanner) {
            this.train = train;
            this.scanner = scanner;
        }

        @Override
        public String getDesc() {
            return "Passenger wagon (WiFi, AC)";
        }

        @Override
        public void execute(String parameter) {
            int id = InputValidator.readPositiveInt(scanner, "Enter wagon ID: ");
            String type = InputValidator.readNonEmptyString(scanner, "Enter wagon type: ");
            int passengers = InputValidator.readPositiveInt(scanner, "Enter passenger count: ");
            double luggage = InputValidator.readPositiveDouble(scanner, "Enter luggage weight (kg): ");
            int comfort = InputValidator.readIntInRange(scanner, "Enter comfort level (1-5): ", 1, 5);
            boolean wifi = InputValidator.readBoolean(scanner, "Has WiFi? (true/false): ");
            boolean ac = InputValidator.readBoolean(scanner, "Has air conditioning? (true/false): ");

            PassengerWagon wagon = new PassengerWagon(id, type, passengers, luggage, comfort, wifi, ac);
            train.addWagon(wagon);
            System.out.println("Passenger wagon added!");
        }
    }

    private static class AddLuxuryWagonCommand implements cli.Command {
        private Train train;
        private Scanner scanner;

        public AddLuxuryWagonCommand(Train train, Scanner scanner) {
            this.train = train;
            this.scanner = scanner;
        }

        @Override
        public String getDesc() {
            return "Luxury wagon (restaurant, bar)";
        }

        @Override
        public void execute(String parameter) {
            int id = InputValidator.readPositiveInt(scanner, "Enter wagon ID: ");
            String type = InputValidator.readNonEmptyString(scanner, "Enter wagon type: ");
            int passengers = InputValidator.readPositiveInt(scanner, "Enter passenger count: ");
            double luggage = InputValidator.readPositiveDouble(scanner, "Enter luggage weight (kg): ");
            int comfort = InputValidator.readIntInRange(scanner, "Enter comfort level (1-5): ", 1, 5);
            boolean wifi = InputValidator.readBoolean(scanner, "Has WiFi? (true/false): ");
            boolean ac = InputValidator.readBoolean(scanner, "Has air conditioning? (true/false): ");
            boolean restaurant = InputValidator.readBoolean(scanner, "Has restaurant? (true/false): ");
            boolean bar = InputValidator.readBoolean(scanner, "Has bar? (true/false): ");
            double price = InputValidator.readPositiveDouble(scanner, "Enter price per seat: ");

            LuxuryWagon wagon = new LuxuryWagon(id, type, passengers, luggage, comfort, wifi, ac, restaurant, bar, price);
            train.addWagon(wagon);
            System.out.println("Luxury wagon added!");
        }
    }
}
