package cli.commands;

import cli.Menu;
import model.Train;
import service.FileService;
import java.util.Scanner;

/**
 * Команда для збереження поїзда з підменю форматів
 */
public class SaveCommand extends TrainCommand {
    private FileService fileService;

    public SaveCommand(Train train, Scanner scanner) {
        super(train, scanner);
        this.fileService = new FileService("train_data.csv");
    }

    @Override
    public String getDesc() {
        return "Зберегти поїзд";
    }

    @Override
    public void execute(String parameter) {
        Menu formatMenu = new Menu("Виберіть формат збереження");
        formatMenu.addCommand("1", new SaveCSVCommand(train, fileService));
        formatMenu.addCommand("2", new SaveJSONCommand(train, fileService));
        formatMenu.run();
    }

    /**
     * Вложена команда для збереження у CSV
     */
    private static class SaveCSVCommand implements cli.Command {
        private Train train;
        private FileService fileService;

        public SaveCSVCommand(Train train, FileService fileService) {
            this.train = train;
            this.fileService = fileService;
        }

        @Override
        public String getDesc() {
            return "Зберегти у CSV";
        }

        @Override
        public void execute(String parameter) {
            fileService.setFilePath("train_data.csv");
            fileService.saveToCSV(train);
            System.out.println("✓ Поїзд збережено у CSV!");
        }
    }

    /**
     * Вложена команда для збереження у JSON
     */
    private static class SaveJSONCommand implements cli.Command {
        private Train train;
        private FileService fileService;

        public SaveJSONCommand(Train train, FileService fileService) {
            this.train = train;
            this.fileService = fileService;
        }

        @Override
        public String getDesc() {
            return "Зберегти у JSON";
        }

        @Override
        public void execute(String parameter) {
            fileService.setFilePath("train_data.json");
            fileService.saveToJSON(train);
            System.out.println("✓ Поїзд збережено у JSON!");
        }
    }
}
