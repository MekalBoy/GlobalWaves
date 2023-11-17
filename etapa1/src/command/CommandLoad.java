package command;

import data.Library;
import functionality.MusicPlayer;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommandLoad extends Command {

    @Override
    public final ResponseMsg processCommand() {
        String message;

        MusicPlayer player = Library.instance.seekUser(this.username).getPlayer();

        if (player.getCurrentSelection() == null) {
            message = "Please select a source before attempting to load.";
        } else {
            boolean succeeded = player.loadAudio(this.timestamp);
            message = succeeded
                    ? "Playback loaded successfully."
                    : "You can't load an empty audio collection!";
        }

        return new ResponseMsg(this, message);
    }
}
