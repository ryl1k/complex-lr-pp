package cli.commands;

import cli.Menu;
import model.Train;
import service.TrainService;
import java.util.Scanner;

public class SortCommand extends TrainCommand {
    private TrainService trainService;

    public SortCommand(Train train, Scanner scanner) {
        super(train, scanner);
        this.trainService = new TrainService();
    }

    @Override
    public String getDesc() {
        return "Sort wagons";
    }

    @Override
    public void execute(String parameter) {
        Menu sortMenu = new Menu("Sort wagons");
        sortMenu.addCommand("1", new SortByComfortCommand(train));
        sortMenu.addCommand("2", new SortByPassengersCommand(train));
        sortMenu.run();
    }

    private static class SortByComfortCommand implements cli.Command {
        private Train train;
        private TrainService trainService;

        public SortByComfortCommand(Train train) {
            this.train = train;
            this.trainService = new TrainService();
        }

        @Override
        public String getDesc() {
            return "By comfort level";
        }

        @Override
        public void execute(String parameter) {
            trainService.sortWagonsByComfort(train);
            System.out.println("Wagons sorted!");
        }
    }

    private static class SortByPassengersCommand implements cli.Command {
        private Train train;
        private TrainService trainService;

        public SortByPassengersCommand(Train train) {
            this.train = train;
            this.trainService = new TrainService();
        }

        @Override
        public String getDesc() {
            return "By passenger count";
        }

        @Override
        public void execute(String parameter) {
            trainService.sortWagonsByPassengers(train);
            System.out.println("Wagons sorted!");
        }
    }
}
