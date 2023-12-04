package data;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Album extends Playlist implements ISelectable, Comparable<Album> {
    private String name, owner, description;
    private int releaseYear;
    private List<Song> songList = new ArrayList<Song>();

    public Album() {
    }

    public Album(final String name, final String owner, final String description,
                 final int releaseYear, final List<Song> songList) {
        this.name = name;
        this.owner = owner;
        this.description = description;
        this.releaseYear = releaseYear;
        this.songList = new ArrayList<Song>(songList);
    }

    @Override
    public int compareTo(Album album) {
        if (getTotalLikes() != album.getTotalLikes()) return getTotalLikes() - album.getTotalLikes();
        return album.getName().compareTo(name);
    }

    @Getter @Setter
    public static class AlbumInfo {
        private String name;
        private List<String> songs;

        public AlbumInfo(final Album album) {
            this.name = album.getName();
            this.songs = album.getSongList().stream().map(Song::getName).toList();
        }
    }

    /**
     * Adds or removes a song from the album and returns a confirmation boolean.
     * @return true if the song was added; false if the song was removed.
     */
    public boolean addRemove(final Song song) {
        if (this.songList.contains(song)) {
            this.songList.remove(song);
            return false;
        } else {
            this.songList.add(song);
            return true;
        }
    }

    /**
     * Returns the total likes of all songs in the album.
     */
    public final int getTotalLikes() {
        int total = 0;
        for (Song song : songList) {
            total += song.getNrLikes();
        }
        return total;
    }

    @Override
    public final SearchType getType() {
        return SearchType.ALBUM;
    }

    @Override
    public final boolean isCollection() {
        return true;
    }

    @Override
    public final Song getNextAfter(final AudioFile file) {
        Song song = (Song) file;
        int index = songList.indexOf(song);
        if (index == -1 || index == songList.size() - 1) {
            return null;
        }
        return songList.get(index + 1);
    }

    @Override
    public final Song getPrevBefore(final AudioFile file) {
        Song song = (Song) file;
        int index = songList.indexOf(song);
        if (index == -1 || index == 0) {
            return null;
        }
        return songList.get(index - 1);
    }
}
