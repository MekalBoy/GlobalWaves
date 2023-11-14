package command;

import data.Library;
import functionality.MusicPlayer;

public class CommandPlayPause extends Command {

    @Override
    public ResponseMsg processCommand() {
        String message;
        MusicPlayer player = Library.instance.seekUser(this.username).getPlayer();

        if (player.getCurrentlyLoaded() == null) {
            message = "Please load a source before attempting to pause or resume playback.";
        } else {
            player.TogglePlayPause(this.timestamp);
            message = player.isPlaying() ? "Playback resumed successfully." : "Playback paused successfully.";
        }

        return new ResponseMsg(this, message);
    }
}
