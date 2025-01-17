package command;

import data.Library;
import functionality.MusicPlayer;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommandPrev extends Command {
    @Override
    public final ResponseMsg processCommand() {
        String message = "";

        MusicPlayer player = Library.instance.seekUser(this.username).getPlayer();
        player.updatePlaying(timestamp);

        if (player.getCurrentlyLoaded() == null) {
            message = "Please load a source before returning to the previous track.";
        } else {
            player.prev();
            message = "Returned to previous track successfully. The current track is "
                    + player.getAudioPlaying().getName()
                    + ".";
        }

        return new ResponseMsg(this, message);
    }
}
