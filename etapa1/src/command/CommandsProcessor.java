package command;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CommandsProcessor {

    public List<Command> commandsList;

    public CommandsProcessor() {
    }

    public CommandsProcessor(final List<Command> commandsList) {
        this.commandsList = commandsList;
    }
}
