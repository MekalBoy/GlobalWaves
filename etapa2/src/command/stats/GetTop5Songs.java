package command.stats;

import command.Command;
import command.response.ResponseResultString;
import data.Library;
import data.Song;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter @Setter
public class GetTop5Songs extends Command {
    private final int songLimit = 5;

    @Override
    public final ResponseResultString processCommand() {
        List<String> result = new ArrayList<String>();

        List<Song> sortedSongs = new ArrayList<Song>(Library.instance.getSongs());
        sortedSongs.sort(Comparator.comparingInt(Song::getNrLikes).reversed());

        for (int i = 0; i < songLimit; i++) {
            result.add(sortedSongs.get(i).getName());
        }

        return new ResponseResultString(this.command, this.username, this.timestamp, result);
    }
}
