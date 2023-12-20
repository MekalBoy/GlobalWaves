package functionality;

import data.ISelectable;
import data.Library;
import data.SearchFilter;
import data.User;

import java.util.ArrayList;
import java.util.List;

public final class SearchBar {

    /**
     * Uses the parameters to retrieve matching results from the Library's database.
     * Users cannot see private playlists that are not theirs.
     * @param username name of user to use the perspective of
     * @param searchType search for SONG, PLAYLIST or PODCAST
     * @param filterer filter object with wanted specs
     * @return first 5 results that match the requirements
     */
    public static List<ISelectable> search(final String username,
                                           final ISelectable.SearchType searchType,
                                           final SearchFilter filterer) {
        final int searchLimit = 5;
        final Library library = Library.getInstance();

        List<ISelectable> results = new ArrayList<ISelectable>();

        switch (searchType) {
            case SONG:
                results.addAll(library.getSongs().stream().filter((song) -> {
                    List<String> commonTags = new ArrayList<String>(song.getTags());
                    boolean chkTags = true;
                    for (String filterTag : filterer.getTags()) {
                        boolean doesntContain = true;
                        for (String tag : commonTags) {
                            if (tag.contains(filterTag)) {
                                doesntContain = false;
                                break;
                            }
                        }
                        if (doesntContain) {
                            chkTags = false;
                            break;
                        }
                    }

                    boolean left = filterer.getReleaseYear().startsWith("<");
                    int year = 0;
                    if (!filterer.getReleaseYear().isEmpty()) {
                        year = Integer.parseInt(filterer.getReleaseYear().substring(1));
                    }
                    boolean yearCheck = left
                            ? song.getReleaseYear() < year
                            : song.getReleaseYear() > year;

                    return (filterer.getName().isEmpty()
                                || song.getName().startsWith(filterer.getName()))
                            && (filterer.getAlbum().isEmpty()
                                || song.getAlbum().equals(filterer.getAlbum()))
                            && (filterer.getTags().isEmpty()
                                || chkTags)
                            && song.getLyrics().toUpperCase()
                                    .contains(filterer.getLyrics().toUpperCase())
                            && (filterer.getGenre().isEmpty()
                                || song.getGenre().equalsIgnoreCase(filterer.getGenre()))
                            && (filterer.getReleaseYear().isEmpty()
                                || yearCheck)
                            && (filterer.getArtist().isEmpty()
                                || song.getArtist().equals(filterer.getArtist()));
                }).toList());
                break;
            case PLAYLIST:
                results.addAll(library.getPlaylists().stream().filter((playlist) -> {
                    return playlist.getName().startsWith(filterer.getName())
                            && (filterer.getOwner().isEmpty()
                                || playlist.getOwner().equals(filterer.getOwner()))
                            && (!playlist.isPrivate()
                                || playlist.getOwner().equals(username));
                }).toList());
                break;
            case PODCAST:
                results.addAll(library.getPodcasts().stream().filter((podcast) -> {
                    return (filterer.getName().isEmpty()
                                || podcast.getName().startsWith(filterer.getName()))
                            && (filterer.getOwner().isEmpty()
                                || podcast.getOwner().equals(filterer.getOwner()));
                }).toList());
                break;
            case ALBUM:
                results.addAll(library.getAlbums().stream().filter((album) -> {
                    return album.getName().startsWith(filterer.getName())
                            && (filterer.getOwner().isEmpty()
                            || album.getOwner().startsWith(filterer.getOwner()))
                            && (filterer.getDescription().isEmpty()
                            || album.getDescription().startsWith(filterer.getDescription()));
                }).toList());
                break;
            case ARTIST:
                results.addAll(library.getUsers().stream().filter(user -> {
                    return user.getUserType() == User.UserType.ARTIST
                    && user.getName().startsWith(filterer.getName());
                }).toList());
                break;
            case HOST:
                results.addAll(library.getUsers().stream().filter(user -> {
                    return user.getUserType() == User.UserType.HOST
                            && user.getName().startsWith(filterer.getName());
                }).toList());
                break;
            default:
                throw new IllegalArgumentException("Invalid searchType");
        }

        // Truncate results to searchLimit
        if (results.size() > searchLimit) {
            results = results.subList(0, searchLimit);
        }

        return results;
    }

    private SearchBar() {
    }
}
