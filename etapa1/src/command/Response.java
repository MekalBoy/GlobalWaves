package command;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    protected String command;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected String user;
    protected int timestamp;

    public Response() {
    }

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
