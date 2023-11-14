package command;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommandCreatePlaylist extends Command {
    private String playlistName;

    public CommandCreatePlaylist() {}

    public CommandCreatePlaylist(String command, String username, int timestamp, String playlistName) {
        super(command, username, timestamp);
        this.playlistName = playlistName;
    }

    @Override
    public ResponseMsg processCommand() {
        String message = "";

        return new ResponseMsg(this, message);
    }
}
