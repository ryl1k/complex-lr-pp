package cli.commands;

import cli.Command;
import model.Train;
import java.util.Scanner;

/**
 * Абстрактний клас для команд, які працюють з поїздом
 */
public abstract class TrainCommand implements Command {
    protected Train train;
    protected Scanner scanner;

    public TrainCommand(Train train, Scanner scanner) {
        this.train = train;
        this.scanner = scanner;
    }

    /**
     * Встановлює новий поїзд
     */
    public void setTrain(Train train) {
        this.train = train;
    }
}
