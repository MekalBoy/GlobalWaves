package command.stats;

import command.Command;
import command.response.ResponseResultMoney;
import functionality.money.MoneyManager;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EndProgram extends Command {
    private final String command = "endProgram";

    @Override
    public final ResponseResultMoney processCommand() {
        return new ResponseResultMoney(this, MoneyManager.getInstance().getDatabase());
    }
}
