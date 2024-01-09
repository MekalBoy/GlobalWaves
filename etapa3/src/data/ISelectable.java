package data;

public interface ISelectable {

    /**
     * @return The object's type. See ISelectable.SearchType
     */
    SearchType getType();

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
    default AudioFile getNextAfter(AudioFile file) {
        return null;
    }

    /**
     * @param file AudioFile present in the collection
     * @return The previous audio file if the provided one isn't the first, or null.
     */
    default AudioFile getPrevBefore(AudioFile file) {
        return null;
    }

    enum SearchType {
        SONG,
        EPISODE,
        PLAYLIST,
        PODCAST,
        ALBUM,
        ARTIST,
        HOST
    }
}
