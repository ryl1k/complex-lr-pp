package cli.commands;

import cli.Menu;
import model.Train;
import service.FileService;
import java.util.Scanner;

public class SaveCommand extends TrainCommand {
    private FileService fileService;

    public SaveCommand(Train train, Scanner scanner) {
        super(train, scanner);
        this.fileService = new FileService("train_data.csv");
    }

    @Override
    public String getDesc() {
        return "Save train";
    }

    @Override
    public void execute(String parameter) {
        Menu formatMenu = new Menu("Select save format");
        formatMenu.addCommand("1", new SaveCSVCommand(train, fileService));
        formatMenu.addCommand("2", new SaveJSONCommand(train, fileService));
        formatMenu.run();
    }

    private static class SaveCSVCommand implements cli.Command {
        private Train train;
        private FileService fileService;

        public SaveCSVCommand(Train train, FileService fileService) {
            this.train = train;
            this.fileService = fileService;
        }

        @Override
        public String getDesc() {
            return "Save to CSV";
        }

        @Override
        public void execute(String parameter) {
            fileService.setFilePath("train_data.csv");
            fileService.saveToCSV(train);
            System.out.println("Train saved to CSV!");
        }
    }

    private static class SaveJSONCommand implements cli.Command {
        private Train train;
        private FileService fileService;

        public SaveJSONCommand(Train train, FileService fileService) {
            this.train = train;
            this.fileService = fileService;
        }

        @Override
        public String getDesc() {
            return "Save to JSON";
        }

        @Override
        public void execute(String parameter) {
            fileService.setFilePath("train_data.json");
            fileService.saveToJSON(train);
            System.out.println("Train saved to JSON!");
        }
    }
}
