package command;

import data.Library;
import lombok.Getter;
import lombok.Setter;
import functionality.Page;

@Getter @Setter
public class PrintCurrentPage extends Command {
    @Override
    public final ResponseMsg processCommand() {
        String message;

        Page page = Library.instance.seekUser(this.username).getPage();

        message = page.toString();

        return new ResponseMsg(this, message);
    }
}
