package command;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResponseMsg extends Response {
    protected String message;

    public ResponseMsg(final String command, final String user, final int timestamp,
                       final String message) {
        super(command, user, timestamp);
        this.message = message;
    }

    public ResponseMsg(final Command command, final String message) {
        super(command);
        this.message = message;
    }
}
