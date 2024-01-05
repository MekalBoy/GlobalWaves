package functionality.wrapped;

import data.User;
import data.ISelectable;
import data.Song;
import data.Library;
import data.Episode;
import functionality.money.MoneyManager;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter @Setter
public class WrappedUser extends Wrapped {
    private Map<String, Integer> topArtists = new LinkedHashMap<String, Integer>();
    private Map<String, Integer> topGenres = new LinkedHashMap<String, Integer>();
    private Map<String, Integer> topSongs = new LinkedHashMap<String, Integer>();
    private Map<String, Integer> topAlbums = new LinkedHashMap<String, Integer>();
    private Map<String, Integer> topEpisodes = new LinkedHashMap<String, Integer>();

    @Override
    public final void incrementTop(final ISelectable selected, final User user) {
        switch (selected.getType()) {
            case SONG:
                Song song = (Song) selected;
                incrementMap(song.getName(), topSongs);
                incrementMap(song.getArtist(), topArtists);
                incrementMap(song.getGenre(), topGenres);
                incrementMap(song.getAlbum(), topAlbums);

                User artist = Library.getInstance().seekUser(song.getArtist());
                artist.getPlayer().getWrappedStats().incrementTop(selected, user);

                MoneyManager.getInstance().tryAddArtist(song);
                MoneyManager.getInstance().tryAddUser(song, user);
                break;
            case PLAYLIST:
            case ALBUM:
                incrementMap(selected.getName(), topAlbums);
                break;
            case EPISODE:
                Episode episode = (Episode) selected;
                incrementMap(selected.getName(), topEpisodes);

                User host = Library.getInstance().seekUser(episode.getOwner());
                // this weird check is because while there are no cases
                // where the artist cannot exist as an user and have a wrapped
                // there is such a case in test10
                if (host != null) {
                    host.getPlayer().getWrappedStats().incrementTop(selected, user);
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid increment type");
        }
    }
}
