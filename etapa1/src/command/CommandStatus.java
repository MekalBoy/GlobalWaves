package command;

import data.Library;
import functionality.MusicPlayer;
import functionality.MusicPlayerStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommandStatus extends Command {

    @Override
    public final ResponseStats processCommand() {
        MusicPlayer player = Library.instance.seekUser(this.username).getPlayer();
        player.UpdatePlaying(this.timestamp);
        
        return new ResponseStats(this, new MusicPlayerStatus(player));
    }
}
