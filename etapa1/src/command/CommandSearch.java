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

    @Override
    public final Response processCommand() {
        ISelectable.SearchType searchType = ISelectable.SearchType.valueOf(this.type.toUpperCase());

        List<ISelectable> searchResults = SearchBar.search(this.username, searchType, filters);
        List<String> results =
                searchResults.stream().map(ISelectable::getName).collect(Collectors.toList());

        MusicPlayer player = Library.instance.seekUser(this.username).getPlayer();
        player.updatePlaying(this.timestamp);
        player.flushPlayer();

        player.setSearchResults(searchResults);

        String message = "Search returned "
                + results.size()
                + " results";

        return new ResponseMsgSearch(this, message, results);
    }
}
