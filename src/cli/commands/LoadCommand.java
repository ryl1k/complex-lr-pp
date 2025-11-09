package cli.commands;

import model.Train;
import service.FileService;
import java.util.Scanner;

public class LoadCommand extends TrainCommand {
    private FileService fileService;

    public LoadCommand(Train train, Scanner scanner) {
        super(train, scanner);
        this.fileService = new FileService("train_data.csv");
    }

    @Override
    public String getDesc() {
        return "Load train";
    }

    @Override
    public void execute(String parameter) {
        System.out.print("Enter filename (without extension): ");
        String filename = scanner.nextLine();
        System.out.print("Enter train name: ");
        String name = scanner.nextLine();
        System.out.print("Enter destination: ");
        String destination = scanner.nextLine();

        fileService.setFilePath(filename + ".csv");
        Train loadedTrain = fileService.loadFromCSV(name, destination);

        // Replace current train with loaded data
        train.setName(loadedTrain.getName());
        train.setDestination(loadedTrain.getDestination());
        train.getWagons().clear();
        train.getWagons().addAll(loadedTrain.getWagons());

        System.out.println("Train loaded!");
    }
}
