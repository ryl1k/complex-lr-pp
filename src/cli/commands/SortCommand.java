package cli.commands;

import cli.Menu;
import model.Train;
import service.TrainService;
import java.util.Scanner;

/**
 * Команда для сортування вагонів з підменю
 */
public class SortCommand extends TrainCommand {
    private TrainService trainService;

    public SortCommand(Train train, Scanner scanner) {
        super(train, scanner);
        this.trainService = new TrainService();
    }

    @Override
    public String getDesc() {
        return "Сортувати вагони";
    }

    @Override
    public void execute(String parameter) {
        Menu sortMenu = new Menu("Сортування вагонів");
        sortMenu.addCommand("1", new SortByComfortCommand(train));
        sortMenu.addCommand("2", new SortByPassengersCommand(train));
        sortMenu.run();
    }

    /**
     * Вложена команда для сортування за комфортом
     */
    private static class SortByComfortCommand implements cli.Command {
        private Train train;
        private TrainService trainService;

        public SortByComfortCommand(Train train) {
            this.train = train;
            this.trainService = new TrainService();
        }

        @Override
        public String getDesc() {
            return "За рівнем комфорту";
        }

        @Override
        public void execute(String parameter) {
            trainService.sortWagonsByComfort(train);
            System.out.println("✓ Вагони відсортовані!");
        }
    }

    /**
     * Вложена команда для сортування за кількістю пасажирів
     */
    private static class SortByPassengersCommand implements cli.Command {
        private Train train;
        private TrainService trainService;

        public SortByPassengersCommand(Train train) {
            this.train = train;
            this.trainService = new TrainService();
        }

        @Override
        public String getDesc() {
            return "За кількістю пасажирів";
        }

        @Override
        public void execute(String parameter) {
            trainService.sortWagonsByPassengers(train);
            System.out.println("✓ Вагони відсортовані!");
        }
    }
}
