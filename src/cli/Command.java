package cli;

// Command pattern interface - every command describes itself and executes a task
public interface Command {
    String getDesc();
    void execute(String parameter);
}
