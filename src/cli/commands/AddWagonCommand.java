package cli.commands;

import cli.Menu;
import model.*;
import java.util.Scanner;

/**
 * Команда для додавання вагона з підменю типів вагонів
 */
public class AddWagonCommand extends TrainCommand {
    public AddWagonCommand(Train train, Scanner scanner) {
        super(train, scanner);
    }

    @Override
    public String getDesc() {
        return "Додати вагон";
    }

    @Override
    public void execute(String parameter) {
        Menu wagonTypeMenu = new Menu("Виберіть тип вагона");
        wagonTypeMenu.addCommand("1", new AddSimpleWagonCommand(train, scanner));
        wagonTypeMenu.addCommand("2", new AddPassengerWagonCommand(train, scanner));
        wagonTypeMenu.addCommand("3", new AddLuxuryWagonCommand(train, scanner));
        wagonTypeMenu.run();
    }

    /**
     * Вложена команда для додавання звичайного вагона
     */
    private static class AddSimpleWagonCommand implements cli.Command {
        private Train train;
        private Scanner scanner;

        public AddSimpleWagonCommand(Train train, Scanner scanner) {
            this.train = train;
            this.scanner = scanner;
        }

        @Override
        public String getDesc() {
            return "Звичайний вагон";
        }

        @Override
        public void execute(String parameter) {
            System.out.print("Введіть ID вагона: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Введіть тип вагона: ");
            String type = scanner.nextLine();
            System.out.print("Введіть кількість пасажирів: ");
            int passengers = Integer.parseInt(scanner.nextLine());
            System.out.print("Введіть вагу багажу (кг): ");
            double luggage = Double.parseDouble(scanner.nextLine());
            System.out.print("Введіть рівень комфорту (1-5): ");
            int comfort = Integer.parseInt(scanner.nextLine());

            Wagon wagon = new Wagon(id, type, passengers, luggage, comfort);
            train.addWagon(wagon);
            System.out.println("✓ Звичайний вагон додано!");
        }
    }

    /**
     * Вложена команда для додавання пасажирського вагона
     */
    private static class AddPassengerWagonCommand implements cli.Command {
        private Train train;
        private Scanner scanner;

        public AddPassengerWagonCommand(Train train, Scanner scanner) {
            this.train = train;
            this.scanner = scanner;
        }

        @Override
        public String getDesc() {
            return "Пасажирський вагон (WiFi, AC)";
        }

        @Override
        public void execute(String parameter) {
            System.out.print("Введіть ID вагона: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Введіть тип вагона: ");
            String type = scanner.nextLine();
            System.out.print("Введіть кількість пасажирів: ");
            int passengers = Integer.parseInt(scanner.nextLine());
            System.out.print("Введіть вагу багажу (кг): ");
            double luggage = Double.parseDouble(scanner.nextLine());
            System.out.print("Введіть рівень комфорту (1-5): ");
            int comfort = Integer.parseInt(scanner.nextLine());
            System.out.print("Чи є WiFi? (true/false): ");
            boolean wifi = Boolean.parseBoolean(scanner.nextLine());
            System.out.print("Чи є кондиціонер? (true/false): ");
            boolean ac = Boolean.parseBoolean(scanner.nextLine());

            PassengerWagon wagon = new PassengerWagon(id, type, passengers, luggage, comfort, wifi, ac);
            train.addWagon(wagon);
            System.out.println("✓ Пасажирський вагон додано!");
        }
    }

    /**
     * Вложена команда для додавання люкс вагона
     */
    private static class AddLuxuryWagonCommand implements cli.Command {
        private Train train;
        private Scanner scanner;

        public AddLuxuryWagonCommand(Train train, Scanner scanner) {
            this.train = train;
            this.scanner = scanner;
        }

        @Override
        public String getDesc() {
            return "Люкс вагон (ресторан, бар)";
        }

        @Override
        public void execute(String parameter) {
            System.out.print("Введіть ID вагона: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Введіть тип вагона: ");
            String type = scanner.nextLine();
            System.out.print("Введіть кількість пасажирів: ");
            int passengers = Integer.parseInt(scanner.nextLine());
            System.out.print("Введіть вагу багажу (кг): ");
            double luggage = Double.parseDouble(scanner.nextLine());
            System.out.print("Введіть рівень комфорту (1-5): ");
            int comfort = Integer.parseInt(scanner.nextLine());
            System.out.print("Чи є WiFi? (true/false): ");
            boolean wifi = Boolean.parseBoolean(scanner.nextLine());
            System.out.print("Чи є кондиціонер? (true/false): ");
            boolean ac = Boolean.parseBoolean(scanner.nextLine());
            System.out.print("Чи є ресторан? (true/false): ");
            boolean restaurant = Boolean.parseBoolean(scanner.nextLine());
            System.out.print("Чи є бар? (true/false): ");
            boolean bar = Boolean.parseBoolean(scanner.nextLine());
            System.out.print("Введіть ціну за місце: ");
            double price = Double.parseDouble(scanner.nextLine());

            LuxuryWagon wagon = new LuxuryWagon(id, type, passengers, luggage, comfort, wifi, ac, restaurant, bar, price);
            train.addWagon(wagon);
            System.out.println("✓ Люкс вагон додано!");
        }
    }
}
