package cli.commands;

import cli.Menu;
import model.Wagon;
import model.Train;
import java.util.Scanner;

/**
 * Команда для редагування вагона
 */
public class EditWagonCommand extends TrainCommand {
    public EditWagonCommand(Train train, Scanner scanner) {
        super(train, scanner);
    }

    @Override
    public String getDesc() {
        return "Редагувати вагон";
    }

    @Override
    public void execute(String parameter) {
        System.out.print("Введіть ID вагона для редагування: ");
        int id = Integer.parseInt(scanner.nextLine());

        Wagon wagon = train.getWagonById(id);
        if (wagon == null) {
            System.out.println("✗ Вагон з ID " + id + " не знайдено!");
            return;
        }

        Menu editMenu = new Menu("Редагування вагона ID=" + id);
        editMenu.addCommand("1", new EditTypeCommand(wagon, scanner));
        editMenu.addCommand("2", new EditPassengersCommand(wagon, scanner));
        editMenu.addCommand("3", new EditLuggageCommand(wagon, scanner));
        editMenu.addCommand("4", new EditComfortCommand(wagon, scanner));
        editMenu.run();
    }

    /**
     * Вложена команда для редагування типу вагона
     */
    private static class EditTypeCommand implements cli.Command {
        private Wagon wagon;
        private Scanner scanner;

        public EditTypeCommand(Wagon wagon, Scanner scanner) {
            this.wagon = wagon;
            this.scanner = scanner;
        }

        @Override
        public String getDesc() {
            return "Змінити тип вагона";
        }

        @Override
        public void execute(String parameter) {
            System.out.print("Введіть новий тип: ");
            String type = scanner.nextLine();
            wagon.setType(type);
            System.out.println("✓ Тип змінено на: " + type);
        }
    }

    /**
     * Вложена команда для редагування кількості пасажирів
     */
    private static class EditPassengersCommand implements cli.Command {
        private Wagon wagon;
        private Scanner scanner;

        public EditPassengersCommand(Wagon wagon, Scanner scanner) {
            this.wagon = wagon;
            this.scanner = scanner;
        }

        @Override
        public String getDesc() {
            return "Змінити кількість пасажирів";
        }

        @Override
        public void execute(String parameter) {
            System.out.print("Введіть нову кількість пасажирів: ");
            int passengers = Integer.parseInt(scanner.nextLine());
            wagon.setPassengerCount(passengers);
            System.out.println("✓ Кількість пасажирів змінена на: " + passengers);
        }
    }

    /**
     * Вложена команда для редагування ваги багажу
     */
    private static class EditLuggageCommand implements cli.Command {
        private Wagon wagon;
        private Scanner scanner;

        public EditLuggageCommand(Wagon wagon, Scanner scanner) {
            this.wagon = wagon;
            this.scanner = scanner;
        }

        @Override
        public String getDesc() {
            return "Змінити вагу багажу";
        }

        @Override
        public void execute(String parameter) {
            System.out.print("Введіть нову вагу багажу (кг): ");
            double luggage = Double.parseDouble(scanner.nextLine());
            wagon.setLuggageWeight(luggage);
            System.out.println("✓ Вага багажу змінена на: " + luggage + " кг");
        }
    }

    /**
     * Вложена команда для редагування рівня комфорту
     */
    private static class EditComfortCommand implements cli.Command {
        private Wagon wagon;
        private Scanner scanner;

        public EditComfortCommand(Wagon wagon, Scanner scanner) {
            this.wagon = wagon;
            this.scanner = scanner;
        }

        @Override
        public String getDesc() {
            return "Змінити рівень комфорту";
        }

        @Override
        public void execute(String parameter) {
            System.out.print("Введіть новий рівень комфорту (1-5): ");
            int comfort = Integer.parseInt(scanner.nextLine());
            wagon.setComfortLevel(comfort);
            System.out.println("✓ Рівень комфорту змінено на: " + comfort);
        }
    }
}
