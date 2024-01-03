package functionality.wrapped;

import data.ISelectable;
import data.Song;
import data.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.LinkedHashMap;

@Getter @Setter
public class WrappedArtist extends Wrapped {
    private Map<String, Integer> topAlbums = new LinkedHashMap<String, Integer>();
    private Map<String, Integer> topSongs = new LinkedHashMap<String, Integer>();
    private Map<String, Integer> topFans = new LinkedHashMap<String, Integer>();

    @Override
    public final void incrementTop(final ISelectable selected, final User user) {
        Song song = (Song) selected;
        incrementMap(song.getName(), topSongs);
        incrementMap(song.getAlbum(), topAlbums);
        incrementMap(user.getUsername(), topFans);
    }
}
