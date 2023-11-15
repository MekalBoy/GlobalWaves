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
    List<String> result;

    @Override
    public ResponseResultString processCommand() {
        result = new ArrayList<String>();

        MusicPlayer player = Library.instance.seekUser(this.username).getPlayer();

        result.addAll(player.getLikedSongs().stream().map(AudioFile::getName).toList());
        //result.add(player.getLikedSongs().get(0).getName());

        return new ResponseResultString(this, result);
    }
}
