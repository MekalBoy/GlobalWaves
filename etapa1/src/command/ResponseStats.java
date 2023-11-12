package command;

import functionality.MusicPlayerStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResponseStats extends Response {
    private MusicPlayerStatus stats;

    public ResponseStats(String command, String user, int timestamp, MusicPlayerStatus stats) {
        super(command, user, timestamp);
        this.stats = stats;
    }

    public ResponseStats(Command command, MusicPlayerStatus stats) {
        super(command);
        this.stats = stats;
    }
}
