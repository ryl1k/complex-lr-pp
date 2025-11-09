package cli.commands;

import cli.Menu;
import model.Wagon;
import model.Train;
import service.TrainService;
import java.util.List;
import java.util.Scanner;

/**
 * Команда для пошуку вагонів з підменю
 */
public class FindCommand extends TrainCommand {
    private TrainService trainService;

    public FindCommand(Train train, Scanner scanner) {
        super(train, scanner);
        this.trainService = new TrainService();
    }

    @Override
    public String getDesc() {
        return "Знайти вагони";
    }

    @Override
    public void execute(String parameter) {
        Menu findMenu = new Menu("Пошук вагонів");
        findMenu.addCommand("1", new FindByPassengersCommand(train, scanner));
        findMenu.addCommand("2", new FindByTypeCommand(train, scanner));
        findMenu.addCommand("3", new FindByComfortCommand(train, scanner));
        findMenu.run();
    }

    /**
     * Вложена команда для пошуку за діапазоном пасажирів
     */
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
            return "За діапазоном пасажирів";
        }

        @Override
        public void execute(String parameter) {
            System.out.print("Введіть мінімум пасажирів: ");
            int min = Integer.parseInt(scanner.nextLine());
            System.out.print("Введіть максимум пасажирів: ");
            int max = Integer.parseInt(scanner.nextLine());

            List<Wagon> results = trainService.findWagonsByPassengerRange(train, min, max);
            if (results.isEmpty()) {
                System.out.println("✗ Вагони не знайдені!");
            } else {
                System.out.println("✓ Знайдено " + results.size() + " вагон(ів):");
                for (Wagon wagon : results) {
                    System.out.println("  " + wagon);
                }
            }
        }
    }

    /**
     * Вложена команда для пошуку за типом вагона
     */
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
            return "За типом вагона";
        }

        @Override
        public void execute(String parameter) {
            System.out.print("Введіть тип вагона: ");
            String type = scanner.nextLine();

            List<Wagon> results = trainService.findWagonsByType(train, type);
            if (results.isEmpty()) {
                System.out.println("✗ Вагони не знайдені!");
            } else {
                System.out.println("✓ Знайдено " + results.size() + " вагон(ів):");
                for (Wagon wagon : results) {
                    System.out.println("  " + wagon);
                }
            }
        }
    }

    /**
     * Вложена команда для пошуку за рівнем комфорту
     */
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
            return "За рівнем комфорту";
        }

        @Override
        public void execute(String parameter) {
            System.out.print("Введіть рівень комфорту (1-5): ");
            int comfort = Integer.parseInt(scanner.nextLine());

            List<Wagon> results = trainService.findWagonsByComfortLevel(train, comfort);
            if (results.isEmpty()) {
                System.out.println("✗ Вагони не знайдені!");
            } else {
                System.out.println("✓ Знайдено " + results.size() + " вагон(ів):");
                for (Wagon wagon : results) {
                    System.out.println("  " + wagon);
                }
            }
        }
    }
}
