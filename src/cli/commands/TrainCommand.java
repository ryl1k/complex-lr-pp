package cli.commands;

import cli.Command;
import model.Train;
import java.util.Scanner;

// Base class for commands that work with a train
public abstract class TrainCommand implements Command {
    protected Train train;
    protected Scanner scanner;

    public TrainCommand(Train train, Scanner scanner) {
        this.train = train;
        this.scanner = scanner;
    }

    public void setTrain(Train train) {
        this.train = train;
    }
}
