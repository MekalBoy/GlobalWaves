package data;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Announcement {
    private String name, description;

    public Announcement() {
    }

    public Announcement(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public final String toString() {
        return name + " - " + description;
    }
}
