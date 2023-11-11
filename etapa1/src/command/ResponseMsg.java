package command;

public class ResponseMsg extends Response {
    protected String message;

    public ResponseMsg(String command, String user, int timestamp, String message) {
        super(command, user, timestamp);
        this.message = message;
    }
}
