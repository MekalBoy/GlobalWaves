package command;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ResponseResultString extends Response {
    private List<String> result;

    public ResponseResultString(final String command, final String user, final int timestamp,
                                final List<String> result) {
        super(command, user, timestamp);
        this.result = result;
    }

    public ResponseResultString(final Command command,
                                final List<String> result) {
        super(command);
        this.result = result;
    }
}
