package data;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Album extends Playlist implements ISelectable, Comparable<Album> {
    private String description;
    private int releaseYear;

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
    public final int compareTo(final Album album) {
        if (getTotalLikes() != album.getTotalLikes()) {
            return getTotalLikes() - album.getTotalLikes();
        }
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
     * Returns the total likes of all songs within the album.
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
}
