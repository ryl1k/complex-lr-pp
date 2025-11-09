package cli.commands;

import model.Train;
import model.Wagon;
import java.util.Scanner;

/**
 * Команда для видалення вагона
 */
public class RemoveWagonCommand extends TrainCommand {
    public RemoveWagonCommand(Train train, Scanner scanner) {
        super(train, scanner);
    }

    @Override
    public String getDesc() {
        return "Видалити вагон";
    }

    @Override
    public void execute(String parameter) {
        System.out.print("Введіть ID вагона для видалення: ");
        int id = Integer.parseInt(scanner.nextLine());

        Wagon wagon = train.getWagonById(id);
        if (wagon == null) {
            System.out.println("✗ Вагон з ID " + id + " не знайдено!");
            return;
        }

        train.removeWagon(id);
        System.out.println("✓ Вагон видалено!");
    }
}
