package cli.commands;

import model.Train;
import java.util.Scanner;

public class CreateTrainCommand extends TrainCommand {
    public CreateTrainCommand(Train train, Scanner scanner) {
        super(train, scanner);
    }

    @Override
    public String getDesc() {
        return "Create new train";
    }

    @Override
    public void execute(String parameter) {
        System.out.print("Enter train name: ");
        String name = scanner.nextLine();
        System.out.print("Enter destination: ");
        String destination = scanner.nextLine();

        train.setName(name);
        train.setDestination(destination);
        train.getWagons().clear();

        System.out.println("New train '" + name + "' created!");
    }
}
