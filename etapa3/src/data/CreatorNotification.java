package data;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreatorNotification {
    private String name, description;

    public CreatorNotification(final String name, final String description) {
        this.name = name;
        this.description = description;
    }
}
