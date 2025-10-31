package cli.commands;

import model.Train;
import service.TrainService;
import java.util.Scanner;

/**
 * Команда для виведення статистики поїзда
 */
public class StatisticsCommand extends TrainCommand {
    private TrainService trainService;

    public StatisticsCommand(Train train, Scanner scanner) {
        super(train, scanner);
        this.trainService = new TrainService();
    }

    @Override
    public String getDesc() {
        return "Показати статистику";
    }

    @Override
    public void execute(String parameter) {
        trainService.printTrainStatistics(train);
    }
}
