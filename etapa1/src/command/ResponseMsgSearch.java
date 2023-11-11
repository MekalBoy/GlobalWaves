package command;

import java.util.List;

public class ResponseMsgSearch extends ResponseMsg {
    private List<String> results;

    public ResponseMsgSearch(String command, String user, int timestamp, String message, List<String> results) {
        super(command, user, timestamp, message);
        this.results = results;
    }
}
