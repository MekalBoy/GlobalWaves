package command;

import data.Library;
import functionality.MusicPlayer;
import functionality.MusicPlayerStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommandStatus extends Command{

    @Override
    public ResponseStats processCommand() {
        MusicPlayer player = Library.instance.seekUser(this.username).getPlayer();
        return new ResponseStats(this, new MusicPlayerStatus(player));
    }
}
