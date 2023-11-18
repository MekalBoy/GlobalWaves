package command;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommandNext extends Command {
    @Override
    public ResponseMsg processCommand() {
        String message = "";
        return new ResponseMsg(this, message);
    }
}
