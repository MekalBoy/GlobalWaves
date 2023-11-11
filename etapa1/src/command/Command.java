package command;

public abstract class Command {
    protected String command, username;
    protected int timestamp;

    public Command() {}

    public Command(String command, String username, int timestamp) {
        this.command = command;
        this.username = username;
        this.timestamp = timestamp;
    }

    public abstract Response processCommand();
}
