package command.response;

import command.Command;
import functionality.Wrapped;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResponseResultWrapped extends Response {
    private Wrapped result;

    public ResponseResultWrapped(final Command command, final Wrapped result) {
        super(command);
        this.result = result;
    }
}
