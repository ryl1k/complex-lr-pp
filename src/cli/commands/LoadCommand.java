package cli.commands;

import model.Train;
import service.FileService;
import java.util.Scanner;

/**
 * Команда для завантаження поїзда з файлу
 */
public class LoadCommand extends TrainCommand {
    private FileService fileService;

    public LoadCommand(Train train, Scanner scanner) {
        super(train, scanner);
        this.fileService = new FileService("train_data.csv");
    }

    @Override
    public String getDesc() {
        return "Завантажити поїзд";
    }

    @Override
    public void execute(String parameter) {
        System.out.print("Введіть назву файлу (без розширення): ");
        String filename = scanner.nextLine();
        System.out.print("Введіть назву поїзда: ");
        String name = scanner.nextLine();
        System.out.print("Введіть напрямок: ");
        String destination = scanner.nextLine();

        fileService.setFilePath(filename + ".csv");
        Train loadedTrain = fileService.loadFromCSV(name, destination);

        // Заміняємо поточний поїзд завантаженим
        train.setName(loadedTrain.getName());
        train.setDestination(loadedTrain.getDestination());
        train.getWagons().clear();
        train.getWagons().addAll(loadedTrain.getWagons());

        System.out.println("✓ Поїзд завантажено!");
    }
}
