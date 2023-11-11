package functionality;

import data.SearchFilter;
import data.Library;

import java.util.ArrayList;
import java.util.List;

public class SearchBar {
    public enum SearchType {
        SONG,
        PLAYLIST,
        PODCAST
    }

    public static List<Object> Search(String username, SearchType searchType, SearchFilter filterer) {
        List<Object> results = new ArrayList<Object>();

        switch(searchType) {
            case SONG:
                results.addAll(Library.instance.getSongs().stream().filter((song) -> {
                    List<String> commonTags = song.getTags();
                    commonTags.retainAll(filterer.tags);
                    boolean left = filterer.releaseYear.startsWith("<");
                    int year = Integer.parseInt(filterer.releaseYear.substring(1));
                    boolean yearCheck = left ? song.getReleaseYear() < year : song.getReleaseYear() > year;

                    return song.getName().startsWith(filterer.name)
                            && (filterer.album.isEmpty() || song.getAlbum().equals(filterer.album))
                            && (filterer.tags.isEmpty() || !commonTags.isEmpty())
                            && song.getLyrics().toUpperCase().contains(filterer.lyrics.toUpperCase())
                            && song.getGenre().equals(filterer.genre)
                            && yearCheck
                            && (filterer.artist.isEmpty() || song.getArtist().equals(filterer.artist));
                }).toList());
                break;
            case PLAYLIST:
                results.addAll(Library.instance.getPlaylists().stream().filter((playlist) -> {
                    return playlist.getName().startsWith(filterer.name)
                            && (filterer.owner.isEmpty() || playlist.getOwner().equals(filterer.owner))
                            && (!playlist.isPrivate() || playlist.getOwner().equals(username));
                }).toList());
                break;
            case PODCAST:
                results.addAll(Library.instance.getPodcasts().stream().filter((podcast) -> {
                    return podcast.getName().startsWith(filterer.name)
                            && (filterer.owner.isEmpty() || podcast.getOwner().equals(filterer.owner));
                }).toList());
                break;
            default:
                throw new IllegalArgumentException("Invalid searchType");
        }

        return results;
    }
}
