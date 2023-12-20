package data;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ArtistEvent {
    private String name, description, date;

    public ArtistEvent() {
    }

    public ArtistEvent(final String name, final String description, final String date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    @Override
    public final String toString() {
        return name + " - " + date + ":\n\t" + description;
    }
}
