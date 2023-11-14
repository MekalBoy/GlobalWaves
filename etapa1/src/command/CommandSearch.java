package command;

import data.ISelectable;
import data.Library;
import data.SearchFilter;
import functionality.MusicPlayer;
import functionality.SearchBar;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CommandSearch extends Command {
    private String type;
    private SearchFilter filters;

    public CommandSearch() {}

    public CommandSearch(String command, String username, int timestamp, String type, SearchFilter filters) {
        super(command, username, timestamp);
        this.type = type;
        this.filters = filters;
    }

    @Override
    public Response processCommand() {
        SearchBar.SearchType searchType = SearchBar.SearchType.valueOf(this.type.toUpperCase());

        List<ISelectable> searchResults = SearchBar.Search(this.username, searchType, filters);
        List<String> results = searchResults.stream().map(ISelectable::getName).collect(Collectors.toList());

        MusicPlayer player = Library.instance.seekUser(this.username).getPlayer();
        player.flushPlayer(this.timestamp);

        player.setSearchResults(searchResults);

        String message = "Search returned " + results.size() + " results";

        return new ResponseMsgSearch(this, message, results);
    }

    @Override
    public String toString() {
        return "CommandSearch{" +
                "type='" + type + '\'' +
                ", filters=" + filters +
                ", command='" + command + '\'' +
                ", username='" + username + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
