package command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseMsg extends Response {
    protected String message;

    public ResponseMsg(String command, String user, int timestamp, String message) {
        super(command, user, timestamp);
        this.message = message;
    }

    @Override
    public String toString() {
        return "{" +
                "command='" + command + '\'' +
                ", user='" + user + '\'' +
                ", timestamp=" + timestamp +
                ", message='" + message + '\'' +
                '}';
    }
}
