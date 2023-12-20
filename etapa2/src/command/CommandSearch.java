package command;

import command.response.Response;
import command.response.ResponseMsgSearch;
import data.ISelectable;
import data.SearchFilter;
import functionality.MusicPlayer;
import functionality.SearchBar;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CommandSearch extends Command {
    private String type;
    private SearchFilter filters;

    @Override
    public final Response processCommand() {
        String message;
        List<String> results;

        if (!library.seekUser(this.username).isOnline()) {
            results = new ArrayList<String>();
            message = this.username + " is offline.";
        } else {
            ISelectable.SearchType searchType =
                    ISelectable.SearchType.valueOf(this.type.toUpperCase());

            List<ISelectable> searchResults = SearchBar.search(this.username, searchType, filters);
            results = searchResults.stream().map(ISelectable::getName).collect(Collectors.toList());

            MusicPlayer player = library.seekUser(this.username).getPlayer();
            player.updatePlaying(this.timestamp);
            player.flushPlayer();

            player.setSearchResults(searchResults);

            message = "Search returned "
                    + results.size()
                    + " results";
        }

        return new ResponseMsgSearch(this, message, results);
    }
}
