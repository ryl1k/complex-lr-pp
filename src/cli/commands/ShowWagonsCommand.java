package cli.commands;

import model.Train;
import java.util.Scanner;

public class ShowWagonsCommand extends TrainCommand {
    public ShowWagonsCommand(Train train, Scanner scanner) {
        super(train, scanner);
    }

    @Override
    public String getDesc() {
        return "Show all wagons";
    }

    @Override
    public void execute(String parameter) {
        train.printAllWagons();
    }
}
