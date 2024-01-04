package command.response;

import command.Command;
import functionality.money.ArtistMoney;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter @Setter
public class ResponseResultMoney extends Response {
    private Map<String, ArtistMoney> result;

    public ResponseResultMoney(final Command command, final Map<String, ArtistMoney> result) {
        super(command);
        this.result = result;
    }
}
