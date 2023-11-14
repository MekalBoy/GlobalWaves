package command;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommandLike extends Command {

    public CommandLike() {}

    public CommandLike(String command, String username, int timestamp) {
        super(command, username, timestamp);
    }

    @Override
    public ResponseMsg processCommand() {
        String message = "";


        return new ResponseMsg(this, message);
    }
}
