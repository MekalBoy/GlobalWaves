package functionality.wrapped;

import data.ISelectable;
import data.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter @Setter
public abstract class Wrapped {
    abstract public void incrementTop(final ISelectable selected, final User user);

    protected void incrementMap(final String key, final Map<String, Integer> dict) {
        dict.put(key, dict.getOrDefault(key, 0) + 1);
    }
}
