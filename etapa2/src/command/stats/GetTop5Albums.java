package command.stats;

import command.Command;
import command.response.ResponseResultString;
import data.Album;
import data.Library;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter @Setter
public class GetTop5Albums extends Command {
    private final int albumLimit = 5;

    @Override
    public final ResponseResultString processCommand() {
        List<String> result = new ArrayList<String>();

        List<Album> sortedAlbums = new ArrayList<Album>(Library.instance.getAlbums());
        sortedAlbums.sort(Album::compareTo);
        Collections.reverse(sortedAlbums);

        for (int i = 0; i < sortedAlbums.size() && i < albumLimit; i++) {
            result.add(sortedAlbums.get(i).getName());
        }

        return new ResponseResultString(this, result);
    }
}
