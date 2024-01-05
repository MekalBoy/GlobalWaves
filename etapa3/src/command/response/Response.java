package command.response;

import command.Command;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Response {
    protected String command;
    protected String user;
    protected Integer timestamp;

    public Response(final String command, final String user, final int timestamp) {
        this.command = command;
        this.user = user;
        this.timestamp = timestamp;
    }

    public Response(final Command command) {
        this.command = command.getCommand();
        this.user = command.getUsername();
        this.timestamp = command.getTimestamp();
    }
}
