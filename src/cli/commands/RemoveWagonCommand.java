package cli.commands;

import cli.InputValidator;
import model.Train;
import model.Wagon;
import java.util.Scanner;

public class RemoveWagonCommand extends TrainCommand {
    public RemoveWagonCommand(Train train, Scanner scanner) {
        super(train, scanner);
    }

    @Override
    public String getDesc() {
        return "Remove wagon";
    }

    @Override
    public void execute(String parameter) {
        int id = InputValidator.readPositiveInt(scanner, "Enter wagon ID to remove: ");

        Wagon wagon = train.getWagonById(id);
        if (wagon == null) {
            System.out.println("Wagon with ID " + id + " not found!");
            return;
        }

        train.removeWagon(id);
        System.out.println("Wagon removed!");
    }
}
