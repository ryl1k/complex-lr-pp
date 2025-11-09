package cli.commands;

import model.Train;
import service.TrainService;
import java.util.Scanner;

public class StatisticsCommand extends TrainCommand {
    private TrainService trainService;

    public StatisticsCommand(Train train, Scanner scanner) {
        super(train, scanner);
        this.trainService = new TrainService();
    }

    @Override
    public String getDesc() {
        return "Show statistics";
    }

    @Override
    public void execute(String parameter) {
        trainService.printTrainStatistics(train);
    }
}
