package command.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import command.Command;
import data.WrappedData;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter @Setter
public class ResponseResultWrapped extends Response {
    private WrappedData result;
    private String message;

    public ResponseResultWrapped(final Command command, final WrappedData result,
                                 final String message) {
        super(command);
        this.result = result;
        this.message = message;
    }
}
