package command;

import command.response.ResponseMsg;
import functionality.MusicPlayer;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommandLoad extends Command {

    @Override
    public final ResponseMsg processCommand() {
        String message;

        if (!library.seekUser(this.username).isOnline()) {
            message = this.username + " is offline.";
        } else {
            MusicPlayer player = library.seekUser(this.username).getPlayer();

            if (player.getCurrentSelection() == null) {
                message = "Please select a source before attempting to load.";
            } else {
                boolean succeeded = player.loadAudio(this.timestamp);
                message = succeeded
                        ? "Playback loaded successfully."
                        : "You can't load an empty audio collection!";
            }
        }

        return new ResponseMsg(this, message);
    }
}
