package data;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class SearchFilter {
    private String name = "", album = "", lyrics = "", genre = "", artist = "", owner = "";
    private String releaseYear = "", description = "";
    private List<String> tags = new ArrayList<String>();

    public SearchFilter() {
    }
}
