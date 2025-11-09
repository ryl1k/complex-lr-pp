package cli.commands;

import cli.Menu;
import cli.InputValidator;
import model.Wagon;
import model.Train;
import java.util.Scanner;

public class EditWagonCommand extends TrainCommand {
    public EditWagonCommand(Train train, Scanner scanner) {
        super(train, scanner);
    }

    @Override
    public String getDesc() {
        return "Edit wagon";
    }

    @Override
    public void execute(String parameter) {
        int id = InputValidator.readPositiveInt(scanner, "Enter wagon ID to edit: ");

        Wagon wagon = train.getWagonById(id);
        if (wagon == null) {
            System.out.println("Wagon with ID " + id + " not found!");
            return;
        }

        Menu editMenu = new Menu("Editing wagon ID=" + id);
        editMenu.addCommand("1", new EditTypeCommand(wagon, scanner));
        editMenu.addCommand("2", new EditPassengersCommand(wagon, scanner));
        editMenu.addCommand("3", new EditLuggageCommand(wagon, scanner));
        editMenu.addCommand("4", new EditComfortCommand(wagon, scanner));
        editMenu.run();
    }

    private static class EditTypeCommand implements cli.Command {
        private Wagon wagon;
        private Scanner scanner;

        public EditTypeCommand(Wagon wagon, Scanner scanner) {
            this.wagon = wagon;
            this.scanner = scanner;
        }

        @Override
        public String getDesc() {
            return "Change wagon type";
        }

        @Override
        public void execute(String parameter) {
            String type = InputValidator.readNonEmptyString(scanner, "Enter new type: ");
            wagon.setType(type);
            System.out.println("Type changed to: " + type);
        }
    }

    private static class EditPassengersCommand implements cli.Command {
        private Wagon wagon;
        private Scanner scanner;

        public EditPassengersCommand(Wagon wagon, Scanner scanner) {
            this.wagon = wagon;
            this.scanner = scanner;
        }

        @Override
        public String getDesc() {
            return "Change passenger count";
        }

        @Override
        public void execute(String parameter) {
            int passengers = InputValidator.readPositiveInt(scanner, "Enter new passenger count: ");
            wagon.setPassengerCount(passengers);
            System.out.println("Passenger count changed to: " + passengers);
        }
    }

    private static class EditLuggageCommand implements cli.Command {
        private Wagon wagon;
        private Scanner scanner;

        public EditLuggageCommand(Wagon wagon, Scanner scanner) {
            this.wagon = wagon;
            this.scanner = scanner;
        }

        @Override
        public String getDesc() {
            return "Change luggage weight";
        }

        @Override
        public void execute(String parameter) {
            double luggage = InputValidator.readPositiveDouble(scanner, "Enter new luggage weight (kg): ");
            wagon.setLuggageWeight(luggage);
            System.out.println("Luggage weight changed to: " + luggage + " kg");
        }
    }

    private static class EditComfortCommand implements cli.Command {
        private Wagon wagon;
        private Scanner scanner;

        public EditComfortCommand(Wagon wagon, Scanner scanner) {
            this.wagon = wagon;
            this.scanner = scanner;
        }

        @Override
        public String getDesc() {
            return "Change comfort level";
        }

        @Override
        public void execute(String parameter) {
            int comfort = InputValidator.readIntInRange(scanner, "Enter new comfort level (1-5): ", 1, 5);
            wagon.setComfortLevel(comfort);
            System.out.println("Comfort level changed to: " + comfort);
        }
    }
}
