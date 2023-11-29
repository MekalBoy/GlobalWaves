package command;

import data.Library;
import data.User;
import functionality.MusicPlayer;
import functionality.MusicPlayerStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommandStatus extends Command {

    @Override
    public final ResponseStats processCommand() {
        User user = Library.instance.seekUser(this.username);
        MusicPlayer player = Library.instance.seekUser(this.username).getPlayer();
        if (user.isOnline()) {
            player.updatePlaying(this.timestamp);
        } else {
            player.setLastUpdateTime(this.timestamp);
        }

        return new ResponseStats(this, new MusicPlayerStatus(player));
    }
}
