package command;

import data.AudioFile;
import data.Library;
import functionality.MusicPlayer;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class CommandShowPreferredSongs extends Command {
    private List<String> result;

    @Override
    public final ResponseResultString processCommand() {
        result = new ArrayList<String>();

        MusicPlayer player = Library.instance.seekUser(this.username).getPlayer();

        result.addAll(player.getLikedSongs().stream().map(AudioFile::getName).toList());

        return new ResponseResultString(this, result);
    }
}
