package cli.commands;

import model.Train;
import java.util.Scanner;

/**
 * Команда для виведення всіх вагонів поїзда
 */
public class ShowWagonsCommand extends TrainCommand {
    public ShowWagonsCommand(Train train, Scanner scanner) {
        super(train, scanner);
    }

    @Override
    public String getDesc() {
        return "Показати всі вагони";
    }

    @Override
    public void execute(String parameter) {
        train.printAllWagons();
    }
}
