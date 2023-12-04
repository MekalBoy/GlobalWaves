package command;

import command.response.ResponseMsg;
import data.Library;
import functionality.MusicPlayer;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommandNext extends Command {
    @Override
    public final ResponseMsg processCommand() {
        String message = "Please load a source before skipping to the next track.";

        if (!Library.instance.seekUser(this.username).isOnline()) {
            message = this.username
                    + " is offline.";
        } else {
            MusicPlayer player = Library.instance.seekUser(this.username).getPlayer();
            player.updatePlaying(timestamp);

            if (player.getCurrentlyLoaded() != null) {
                boolean isGood = player.next();
                message = isGood
                        ? "Skipped to next track successfully. The current track is "
                        + player.getAudioPlaying().getName()
                        + "."
                        : message;
            }
        }

        return new ResponseMsg(this, message);
    }
}
