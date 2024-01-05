package command;

import command.response.ResponseMsg;

public class CancelPremium extends Command {
    @Override
    public ResponseMsg processCommand() {
        String message = null;

        return new ResponseMsg(this, message);
    }
}
