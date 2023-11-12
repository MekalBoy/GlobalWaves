package command;

import data.Library;
import functionality.MusicPlayer;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommandLoad extends Command {

    @Override
    public ResponseMsg processCommand() {
        String message;
        MusicPlayer player = Library.instance.seekUser(this.username).getPlayer();

        if (player.getCurrentSelection() == null) {
            message = "Please select a source before attempting to load.";
        } else {
            //player.setCurrentlyLoaded(player.getCurrentSelection());
            player.LoadAudio();
            message = "Playback loaded successfully.";
        }

        return new ResponseMsg(this, message);
    }
}
