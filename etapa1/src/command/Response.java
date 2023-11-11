package command;

public class Response {
    protected String command, user;
    protected int timestamp;

    public Response() {}

    public Response(String command, String user, int timestamp) {
        this.command = command;
        this.user = user;
        this.timestamp = timestamp;
    }
}
