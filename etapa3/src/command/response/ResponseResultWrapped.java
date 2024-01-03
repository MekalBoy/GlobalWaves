package command.response;

import command.Command;
import data.WrappedData;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResponseResultWrapped extends Response {
    private WrappedData result;

    public ResponseResultWrapped(final Command command, final WrappedData result) {
        super(command);
        this.result = result;
    }
}
