package command.response;

import command.Command;
import functionality.MusicPlayerStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResponseStats extends Response {
    private MusicPlayerStatus stats;

    public ResponseStats(final String command, final String user, final int timestamp,
                         final MusicPlayerStatus stats) {
        super(command, user, timestamp);
        this.stats = stats;
    }

    public ResponseStats(final Command command,
                         final MusicPlayerStatus stats) {
        super(command);
        this.stats = stats;
    }
}
