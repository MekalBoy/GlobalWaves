package data;

import functionality.SearchBar;

public interface ISelectable {
    public SearchBar.SearchType getType();
    public String getName();
    public boolean isCollection();
}
