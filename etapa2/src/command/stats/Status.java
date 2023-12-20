package command.stats;

import command.Command;
import command.response.ResponseStats;
import data.User;
import functionality.MusicPlayer;
import functionality.MusicPlayerStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Status extends Command {

    @Override
    public final ResponseStats processCommand() {
        User user = library.seekUser(this.username);
        MusicPlayer player = library.seekUser(this.username).getPlayer();
        if (user.isOnline()) {
            player.updatePlaying(this.timestamp);
        } else {
            player.setLastUpdateTime(this.timestamp);
        }

        return new ResponseStats(this, new MusicPlayerStatus(player));
    }
}
