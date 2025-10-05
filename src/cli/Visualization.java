package cli;

public class Visualization {

    public static final String RESET = "\u001B[0m";

    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    // BRIGHT VARIATIONS
    public static final String BRIGHT_BLACK = "\u001B[90m";
    public static final String BRIGHT_RED = "\u001B[91m";
    public static final String BRIGHT_GREEN = "\u001B[92m";
    public static final String BRIGHT_YELLOW = "\u001B[93m";
    public static final String BRIGHT_BLUE = "\u001B[94m";
    public static final String BRIGHT_PURPLE = "\u001B[95m";
    public static final String BRIGHT_CYAN = "\u001B[96m";
    public static final String BRIGHT_WHITE = "\u001B[97m";

    // BACKGROUNDS
    public static final String BG_BLACK = "\u001B[40m";
    public static final String BG_RED = "\u001B[41m";
    public static final String BG_GREEN = "\u001B[42m";
    public static final String BG_YELLOW = "\u001B[43m";
    public static final String BG_BLUE = "\u001B[44m";
    public static final String BG_PURPLE = "\u001B[45m";
    public static final String BG_CYAN = "\u001B[46m";
    public static final String BG_WHITE = "\u001B[47m";

    public void printColor(String color, String text) {
        System.out.print(color + text + RESET);
    }

    public void printlnColor(String color, String text) {
        System.out.println(color + text + RESET);
    }

    public void printTitle(String text, String color1, String color2) {
        System.out.println(color1 + "╔" + "═".repeat(text.length() + 2) + "╗" + RESET);
        System.out.println(color1 + "║ " + color2 + text + color1 +            " ║" + RESET);
        System.out.println(color1 + "╚" + "═".repeat(text.length() + 2) + "╝" + RESET);
    }

    public void separator(String color) {
        System.out.println(color + "─".repeat(40) + RESET);
    }

    public void showLoadingBar(int percent, String color) {
        int total = 30;
        int filled = percent * total / 100;
        String bar = "█".repeat(filled) + "-".repeat(total - filled);
        System.out.print("\r" + color + "[" + bar + "] " + percent + "%" + RESET);
        if (percent == 100) System.out.println();
    }

    public void printCentered(String text, int width) {
        int padding = Math.max(0, (width - text.length()) / 2);
        System.out.println(" ".repeat(padding) + text);
    }

    public void sleep(int ms){
        try{
            Thread.sleep(ms);
        } catch (InterruptedException ignored){}
    }
}
