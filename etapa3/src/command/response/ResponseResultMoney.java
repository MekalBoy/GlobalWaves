package command.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import command.Command;
import functionality.money.ArtistMoney;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter
public class ResponseResultMoney extends Response {
    private Map<String, ArtistMoney> result = new HashMap<String, ArtistMoney>();

    public ResponseResultMoney(final Command command, final Map<String, ArtistMoney> result) {
        super(command);
        this.result = result;
    }
}
