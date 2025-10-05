package cli;

import java.util.Scanner;

public class CLI {
    Scanner scanner = new Scanner(System.in);

    public static void cls() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static boolean menu(Visualization visual) {
        visual.printTitle("Головне меню", Visualization.BRIGHT_CYAN, Visualization.BRIGHT_WHITE);
        visual.printlnColor(Visualization.BRIGHT_WHITE, "1. Подивтись інформацію про потяги");
        visual.printlnColor(Visualization.BRIGHT_WHITE, "2. Переглянути список потягів");
        visual.printlnColor(Visualization.BRIGHT_WHITE, "3. Додати новий потяг");
        visual.printlnColor(Visualization.BRIGHT_WHITE, "4. Вийти з програми");
        visual.separator(Visualization.BLACK);
        visual.printlnColor(Visualization.BRIGHT_CYAN, "Напишіть число 1-4 для вибору");

        int choice = scanner.nextInt();

        switch(choice){
            case 1:
                //TODO
            case 2:
                //TODO
            case 3:
                //TODO
            case 4:
                return false;
            default:
                visual.printlnColor(Visualization.BRIGHT_RED, "Оберіть від 1 до 4!");
                visual.sleep(3000);
                return true;
        }

        return true;
    }

    public void run() {
        Visualization visual = new Visualization();

        while (true) {
            if(!menu(visual)){
                break;
            }
        }
    }

}
