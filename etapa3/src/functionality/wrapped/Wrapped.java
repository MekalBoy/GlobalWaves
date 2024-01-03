package functionality.wrapped;

import data.ISelectable;
import data.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter @Setter
public abstract class Wrapped {
    /**
     * Defines how a wrap's data is handled and incremented
     * based on the user and the currently selected thing.
     */
    public abstract void incrementTop(ISelectable selected, User user);

    /**
     * Helper function for incrementing a value within a map.
     */
    protected void incrementMap(final String key, final Map<String, Integer> dict) {
        dict.put(key, dict.getOrDefault(key, 0) + 1);
    }
}
