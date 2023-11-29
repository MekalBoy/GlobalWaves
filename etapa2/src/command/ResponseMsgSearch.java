package command;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ResponseMsgSearch extends ResponseMsg {
    private List<String> results;

    public ResponseMsgSearch(final String command, final String user, final int timestamp,
                             final String message, final List<String> results) {
        super(command, user, timestamp, message);
        this.results = results;
    }

    public ResponseMsgSearch(final Command command,
                             final String message, final List<String> results) {
        super(command, message);
        this.results = results;
    }
}
