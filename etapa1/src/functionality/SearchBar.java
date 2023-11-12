package functionality;

import data.ISelectable;
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

    public static List<ISelectable> Search(String username, SearchType searchType, SearchFilter filterer) {
        List<ISelectable> results = new ArrayList<ISelectable>();

        switch(searchType) {
            case SONG:
                results.addAll(Library.instance.getSongs().stream().filter((song) -> {
                    List<String> commonTags = new ArrayList<String>(song.getTags());
                    boolean chkTags = commonTags.stream().anyMatch(s1 -> filterer.tags.stream().anyMatch(s2 -> s1.contains(s2)));

                    boolean left = filterer.releaseYear.startsWith("<");
                    int year = 0;
                    if (!filterer.releaseYear.isEmpty())
                        year = Integer.parseInt(filterer.releaseYear.substring(1));
                    boolean yearCheck = left ? song.getReleaseYear() < year : song.getReleaseYear() > year;

                    return (filterer.name.isEmpty() || song.getName().startsWith(filterer.name))
                            && (filterer.album.isEmpty() || song.getAlbum().equals(filterer.album))
                            && (filterer.tags.isEmpty() || chkTags)
                            && song.getLyrics().toUpperCase().contains(filterer.lyrics.toUpperCase())
                            && (filterer.genre.isEmpty() || song.getGenre().equals(filterer.genre))
                            && (filterer.releaseYear.isEmpty() || yearCheck)
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
                    return (filterer.name.isEmpty() || podcast.getName().startsWith(filterer.name))
                            && (filterer.owner.isEmpty() || podcast.getOwner().equals(filterer.owner));
                }).toList());
                break;
            default:
                throw new IllegalArgumentException("Invalid searchType");
        }

        return results;
    }
}
