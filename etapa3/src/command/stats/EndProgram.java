package command.stats;

import command.Command;
import command.response.Response;

public class EndProgram extends Command {
    @Override
    public Response processCommand() {
        return new Response(this);
    }
}
