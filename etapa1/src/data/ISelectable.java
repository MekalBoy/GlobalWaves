package data;

import functionality.SearchBar;

public interface ISelectable {

    /**
     * Method should return SONG, PLAYLIST, PODCAST or null.
     */
    SearchBar.SearchType getType();

    /**
     * Method should return the name of the object.
     */
    String getName();

    /**
     * Method should return whether the object is a PLAYLIST/PODCAST (collection)
     * or an AudioFile (not a collection).
     */
    boolean isCollection();

    /**
     * @param file AudioFile present in the collection
     * @return The next audio file if there's still other audio in the collection, or null.
     */
    AudioFile getNextAfter(AudioFile file);

    /**
     * @param file AudioFile present in the collection
     * @return The previous audio file if the provided one isn't the first, or null.
     */
    AudioFile getPrevBefore(AudioFile file);
}
