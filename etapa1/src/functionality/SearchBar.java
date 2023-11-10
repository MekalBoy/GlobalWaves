package functionality;

import data.*;

import java.util.ArrayList;
import java.util.List;

public class SearchBar {
    public enum SearchType {
        SONG,
        PLAYLIST,
        PODCAST
    }

    public class Filter {
        public String name, album, lyrics, genre, artist, owner;
        public int releaseYear;
        public List<String> tags;
    }

    public List<ISearchable> Search(SearchType searchType, Filter filters) {
        List<ISearchable> results = new ArrayList<>();

        switch(searchType) {
            case SearchType.SONG:
                for (Song song : Library.instance.getSongs()) {
                    
                }
                break;
            case SearchType.PLAYLIST:
                break;
            case SearchType.PODCAST:
                break;
            default:
                System.out.print("How did we get here?");
                break;
        }

        return null;
    }
}
