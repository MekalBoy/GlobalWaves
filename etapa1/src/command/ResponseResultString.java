package command;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ResponseResultString extends Response {
    List<String> result;

    public ResponseResultString(String command, String user, int timestamp, List<String> result) {
        super(command, user, timestamp);
        this.result = result;
    }

    public ResponseResultString(Command command, List<String> result) {
        super(command);
        this.result = result;
    }
}
