package command;

import data.*;
import functionality.SearchBar;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommandSearch extends Command {
    private String type;
    private SearchFilter filter;

    public CommandSearch(String command, String username, int timestamp, String type, SearchFilter filter) {
        super(command, username, timestamp);
        this.type = type;
        this.filter = filter;
    }

    @Override
    public ResponseMsgSearch processCommand() {
        String message;
        List<String> results;

        SearchBar.SearchType searchType = SearchBar.SearchType.valueOf(this.type);

        List<Object> searchResults = SearchBar.Search(this.username, searchType, filter);
        switch(searchType) {
            case SONG:
                List<Song> songs = new ArrayList<>();
                for (Object obj : searchResults) {
                    songs.add((Song) obj);
                }
                results = songs.stream().map(Song::getName).collect(Collectors.toList());
                break;
            case PLAYLIST:
                List<Playlist> playlists = new ArrayList<>();
                for (Object obj : searchResults) {
                    playlists.add((Playlist) obj);
                }
                results = playlists.stream().map(Playlist::getName).collect(Collectors.toList());
                break;
            case PODCAST:
                List<Podcast> podcasts = new ArrayList<>();
                for (Object obj : searchResults) {
                    podcasts.add((Podcast) obj);
                }
                results = podcasts.stream().map(Podcast::getName).collect(Collectors.toList());
                break;
            default:
                throw new IllegalArgumentException("Invalid searchType");
        }

        User user = Library.instance.seekUser(this.username);

        // TODO: set user's search results
        //List<ISelectable> userResults = new ArrayList<ISelectable>();
        //user.setSearchResults(results);

        message = "Search returned " + results.size() + " results";

        return new ResponseMsgSearch(this.command, this.username, this.timestamp, message, results);
    }
}
