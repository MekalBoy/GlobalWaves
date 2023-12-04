package command;

import command.response.ResponseMsg;
import data.Library;
import functionality.MusicPlayer;

public class CommandPlayPause extends Command {

    @Override
    public final ResponseMsg processCommand() {
        String message;
        if (!Library.instance.seekUser(this.username).isOnline()) {
            message = this.username
                    + " is offline.";
        } else {
            MusicPlayer player = Library.instance.seekUser(this.username).getPlayer();

            if (player.isPlaying()) {
                player.updatePlaying(timestamp);
            }

            if (player.getCurrentlyLoaded() == null) {
                message = "Please load a source before attempting to pause or resume playback.";
            } else {
                player.togglePlayPause(this.timestamp);
                message = player.isPlaying()
                        ? "Playback resumed successfully."
                        : "Playback paused successfully.";
            }
        }

        return new ResponseMsg(this, message);
    }
}
