package cli.commands;

import cli.Menu;
import cli.InputValidator;
import model.Wagon;
import model.Train;
import service.TrainService;
import java.util.List;
import java.util.Scanner;

public class FindCommand extends TrainCommand {
    private TrainService trainService;

    public FindCommand(Train train, Scanner scanner) {
        super(train, scanner);
        this.trainService = new TrainService();
    }

    @Override
    public String getDesc() {
        return "Find wagons";
    }

    @Override
    public void execute(String parameter) {
        Menu findMenu = new Menu("Find wagons");
        findMenu.addCommand("1", new FindByPassengersCommand(train, scanner));
        findMenu.addCommand("2", new FindByTypeCommand(train, scanner));
        findMenu.addCommand("3", new FindByComfortCommand(train, scanner));
        findMenu.run();
    }

    private static class FindByPassengersCommand implements cli.Command {
        private Train train;
        private Scanner scanner;
        private TrainService trainService;

        public FindByPassengersCommand(Train train, Scanner scanner) {
            this.train = train;
            this.scanner = scanner;
            this.trainService = new TrainService();
        }

        @Override
        public String getDesc() {
            return "By passenger range";
        }

        @Override
        public void execute(String parameter) {
            int min = InputValidator.readPositiveInt(scanner, "Enter minimum passengers: ");
            int max = InputValidator.readPositiveInt(scanner, "Enter maximum passengers: ");

            List<Wagon> results = trainService.findWagonsByPassengerRange(train, min, max);
            if (results.isEmpty()) {
                System.out.println("No wagons found!");
            } else {
                System.out.println("Found " + results.size() + " wagon(s):");
                for (Wagon wagon : results) {
                    System.out.println("  " + wagon);
                }
            }
        }
    }

    private static class FindByTypeCommand implements cli.Command {
        private Train train;
        private Scanner scanner;
        private TrainService trainService;

        public FindByTypeCommand(Train train, Scanner scanner) {
            this.train = train;
            this.scanner = scanner;
            this.trainService = new TrainService();
        }

        @Override
        public String getDesc() {
            return "By wagon type";
        }

        @Override
        public void execute(String parameter) {
            String type = InputValidator.readNonEmptyString(scanner, "Enter wagon type: ");

            List<Wagon> results = trainService.findWagonsByType(train, type);
            if (results.isEmpty()) {
                System.out.println("No wagons found!");
            } else {
                System.out.println("Found " + results.size() + " wagon(s):");
                for (Wagon wagon : results) {
                    System.out.println("  " + wagon);
                }
            }
        }
    }

    private static class FindByComfortCommand implements cli.Command {
        private Train train;
        private Scanner scanner;
        private TrainService trainService;

        public FindByComfortCommand(Train train, Scanner scanner) {
            this.train = train;
            this.scanner = scanner;
            this.trainService = new TrainService();
        }

        @Override
        public String getDesc() {
            return "By comfort level";
        }

        @Override
        public void execute(String parameter) {
            int comfort = InputValidator.readIntInRange(scanner, "Enter comfort level (1-5): ", 1, 5);

            List<Wagon> results = trainService.findWagonsByComfortLevel(train, comfort);
            if (results.isEmpty()) {
                System.out.println("No wagons found!");
            } else {
                System.out.println("Found " + results.size() + " wagon(s):");
                for (Wagon wagon : results) {
                    System.out.println("  " + wagon);
                }
            }
        }
    }
}
