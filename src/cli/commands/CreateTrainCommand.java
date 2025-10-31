package cli.commands;

import model.Train;
import java.util.Scanner;

/**
 * Команда для створення нового поїзда
 */
public class CreateTrainCommand extends TrainCommand {
    public CreateTrainCommand(Train train, Scanner scanner) {
        super(train, scanner);
    }

    @Override
    public String getDesc() {
        return "Створити новий поїзд";
    }

    @Override
    public void execute(String parameter) {
        System.out.print("Введіть назву поїзда: ");
        String name = scanner.nextLine();
        System.out.print("Введіть напрямок: ");
        String destination = scanner.nextLine();

        train.setName(name);
        train.setDestination(destination);
        train.getWagons().clear();

        System.out.println("✓ Новий поїзд '" + name + "' створено!");
    }
}
