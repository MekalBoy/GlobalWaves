package command.unimplemented;

import command.Command;
import command.response.ResponseMsg;

public class NextPage extends Command {
    @Override
    public final ResponseMsg processCommand() {
        String message = null;

        return new ResponseMsg(this, message);
    }
}
