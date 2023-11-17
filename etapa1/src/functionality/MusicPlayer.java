package functionality;

import data.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter @Setter
public class MusicPlayer {
    public enum RepeatType {
        NO,
        ALL,
        CURRENT
    }

    private boolean isPlaying = true;
    private boolean shuffle = false;
    private RepeatType repeatType = RepeatType.NO;

    private int remainedTime;
    private int lastUpdateTime; // timestamp of last update

    private AudioFile audioPlaying;
    private ISelectable currentlyLoaded;
    private ISelectable currentSelection;
    private List<ISelectable> searchResults;

    private List<Song> likedSongs = new ArrayList<Song>();
    private List<Playlist> createdPlaylists = new ArrayList<Playlist>();
    private List<Playlist> followedPlaylists = new ArrayList<Playlist>();
    //private List<Integer> shuffleOrder = null; // used for shuffle
    private ISelectable backupPlaylist = null; // used for shuffle

    private Map<Podcast, Episode> resumePodcasts = new HashMap<Podcast, Episode>();
    private Map<Episode, Integer> resumeEpisodes = new HashMap<Episode, Integer>();

    private final int windingSpeed = 90;

    public MusicPlayer() {
    }

    /**
     * Flushes the player's contents (audio playing, load, select, search)
     * and resets the remainedTime, repeatType and shuffle values.
     */
    public void flushPlayer(final int timestamp) {
        updatePlaying(timestamp);

        if (currentlyLoaded != null && currentlyLoaded.getType() == SearchBar.SearchType.PODCAST) {
            Podcast podcast = (Podcast) currentlyLoaded;
            Episode episode = (Episode) audioPlaying;
            if (resumePodcasts.containsKey(podcast)) { // we've played this podcast before
                if (resumeEpisodes.containsKey(episode)) { // this is where we left off
                    resumeEpisodes.replace(episode, remainedTime);
                } else { // first time we stop this episode
                    resumeEpisodes.put(episode, remainedTime);
                }
            } else { // we haven't played this podcast before
                resumePodcasts.put(podcast, episode);
                resumeEpisodes.put(episode, remainedTime);
            }
        }

        if (audioPlaying != null) {
            isPlaying = !isPlaying;
        }
        audioPlaying = null;
        currentlyLoaded = null;
        currentSelection = null;
        searchResults = null;
        remainedTime = 0;
        repeatType = RepeatType.NO;
        shuffle = false;
    }

    /**
     * Loads the selection into the player.
     * @return true if load succeeds; false if collection is empty
     */
    public boolean loadAudio(final int timestamp) {
        this.setCurrentlyLoaded(this.getCurrentSelection());

        switch (currentlyLoaded.getType()) {
            case SONG:
                this.audioPlaying = (Song) this.getCurrentSelection();
                this.remainedTime = audioPlaying.getDuration();
                break;
            case PLAYLIST:
                if (((Playlist) this.getCurrentSelection()).getSongList().isEmpty()) {
                    return false;
                }
                this.audioPlaying = ((Playlist) this.getCurrentSelection()).getSongList().get(0);
                this.remainedTime = audioPlaying.getDuration();
                break;
            case PODCAST:
                Podcast podcast = ((Podcast) this.getCurrentSelection());

                if (podcast.getEpisodes().isEmpty()) {
                    return false;
                }

                if (resumePodcasts.containsKey(podcast)) { // we've played this podcast before
                    Episode episode = resumePodcasts.get(podcast);
                    this.audioPlaying = episode;
                    if (resumeEpisodes.containsKey(episode)) { // this is where we left off
                        this.remainedTime = resumeEpisodes.get(episode);
                    } else { // first time we play this episode
                        this.remainedTime = episode.getDuration();
                    }
                } else { // we haven't played this podcast before
                    this.audioPlaying = ((Podcast) this.getCurrentSelection()).getEpisodes().get(0);
                    this.remainedTime = audioPlaying.getDuration();
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid loadType");
        }

        currentSelection = null;
        isPlaying = true;

        this.lastUpdateTime = timestamp;
        return true;
    }

    /**
     * Plays the provided audio.
     * @param audioFile audio to load
     */
    public void playAudio(final AudioFile audioFile) {
        this.audioPlaying = audioFile;
        this.remainedTime = audioFile.getDuration();
        setPlaying(true);
    }

    /**
     * Updates the remainedTime and current audio of the player by simulating the time
     * that's passed between the provided (current) timestamp and the last update time.
     * @param timestamp command timestamp, probably
     */
    public void updatePlaying(final int timestamp) {
        if (!isPlaying) {
            return;  // when paused we don't need to update anything
        }

        if (audioPlaying == null) {
            return;
        }

        remainedTime -= (timestamp - lastUpdateTime);
        lastUpdateTime = timestamp;

        if (remainedTime <= 0) {
            int leftover = -remainedTime;

            if (!currentlyLoaded.isCollection()) {  // It was a single song, check for repeat type
                switch (repeatType) {
                    case NO:
                        currentlyLoaded = null;
                        audioPlaying = null;
                        remainedTime = 0;

                        isPlaying = false;
                        shuffle = false;
                        return;
                    case ALL: // repeat once
                        repeatType = RepeatType.NO;
                        break;
                    case CURRENT: // repeat infinitely
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid repeatType");
                }
                playAudio(audioPlaying);
                remainedTime -= leftover % audioPlaying.getDuration();
            } else { // Podcast or Playlist (collection), so try and get the next song/episode
                AudioFile nextThing = currentlyLoaded.getNextAfter(audioPlaying);

                if (nextThing == null) { // nothing left in collection, check for repeat type
                    switch (repeatType) {
                        case NO: // no repeat, yeet
                            if (audioPlaying != null) {
                                isPlaying = !isPlaying;
                            }
                            audioPlaying = null;
                            currentlyLoaded = null;
                            currentSelection = null;
                            searchResults = null;
                            remainedTime = 0;
                            return;
                        case ALL:
                            if (currentlyLoaded.getType() == SearchBar.SearchType.PLAYLIST) {
                                // replay from first song
                                playAudio(((Playlist) currentlyLoaded).getSongList().get(0));
                            } else { // repeat current episode for podcast
                                repeatType = RepeatType.NO; // only run again once
                                playAudio(audioPlaying);
                            }
                            break;
                        case CURRENT:
                            playAudio(audioPlaying);
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid repeatType");
                    }
                    remainedTime -= leftover % audioPlaying.getDuration();
                    return;
                }

                // if we got to here there still is something to play next
                // but we still need to check for repeat
                if (repeatType == RepeatType.CURRENT) { // repeat current thing infinitely
                    playAudio(audioPlaying);
                    remainedTime -= leftover % audioPlaying.getDuration();
                    return;
                }

                if (repeatType == RepeatType.ALL) {
                    leftover -= nextThing.getDuration();
                    while (leftover > 0) {
                        nextThing = currentlyLoaded.getNextAfter(nextThing);
                        if (nextThing == null) {
                            nextThing = ((Playlist) currentlyLoaded).getSongList().get(0);
                        }
                        leftover -= nextThing.getDuration();
                    }

                    playAudio(nextThing);
                    remainedTime -= nextThing.getDuration() % -leftover;
                    return;
                }

                if (repeatType == RepeatType.NO) {
                    leftover -= nextThing.getDuration();
                    while (leftover > 0) {
                        nextThing = currentlyLoaded.getNextAfter(nextThing);
                        if (nextThing == null) {
                            isPlaying = false;
                            audioPlaying = null;
                            currentlyLoaded = null;
                            currentSelection = null;
                            searchResults = null;
                            remainedTime = 0;
                            return;
                        }
                        leftover -= nextThing.getDuration();
                    }

                    playAudio(nextThing);
                    remainedTime -= nextThing.getDuration() % -leftover;
                    return;
                }

                playAudio(nextThing);
                remainedTime -= leftover % audioPlaying.getDuration();
            }
        }
    }

    /**
     * Pauses or unpauses the player.
     */
    public void togglePlayPause(final int timestamp) {
        if (isPlaying) {
            updatePlaying(timestamp);
        }
        lastUpdateTime = timestamp;
        isPlaying = !isPlaying;
    }

    /**
     * Toggles shuffle mode on/off for the current playlist.
     * @param seed Random object initialization seed
     * @return true if shuffle was toggled on; false if shuffle was toggled off.
     */
    public boolean toggleShuffle(final int timestamp, final int seed) {
        shuffle = !shuffle;

        if (shuffle) {
            Playlist shuffledPlaylist = new Playlist((Playlist) currentlyLoaded);
            List<Song> shuffledSongs = shuffledPlaylist.getSongList();
//            List<Integer> shuffledInts = new ArrayList<Integer>(IntStream
//                    .rangeClosed(0, shuffledSongs.size() - 1).boxed().toList());
//            Collections.shuffle(shuffledInts, new Random(seed));
            Collections.shuffle(shuffledSongs, new Random(seed));
            shuffledPlaylist.setSongList(shuffledSongs);

            //shuffleOrder = shuffledInts;
            backupPlaylist = currentlyLoaded;
            currentlyLoaded = shuffledPlaylist;
        } else {
            currentlyLoaded = backupPlaylist;
            backupPlaylist = null;
            //shuffleOrder = null;
        }
        updatePlaying(timestamp);

        return shuffle;
    }

/*    public Song GetNextShuffled(Song song) {
        Playlist playlist = (Playlist) currentlyLoaded;
        Integer k = playlist.getSongList().indexOf(song);
        int kInShuffle = shuffleOrder.indexOf(k);
        return playlist.getSongList().get(shuffleOrder.get(kInShuffle + 1));
    }*/

    /**
     * Likes or unlikes the provided song.
     * @param song liked/unliked Song object
     * @return true if the song was liked; false if the song was unliked.
     */
    public boolean likeUnlike(final Song song) {
        if (likedSongs.contains(song)) {
            song.setNrLikes(song.getNrLikes() - 1);
            likedSongs.remove(song);
            return false;
        } else {
            song.setNrLikes(song.getNrLikes() + 1);
            likedSongs.add(song);
            return true;
        }
    }

    /**
     * Adds the (perhaps freshly created) playlist to the createdPlaylists list.
     * @param playlist freshly created Playlist object
     */
    public void addToCreatedPlaylists(final Playlist playlist) {
        createdPlaylists.add(playlist);
    }

    /**
     * Adds or removes the song in the provided playlist.
     * @param id the id of the created playlist
     * @param song the song that needs to be added or removed
     * @return true if the song was added; false if the song was removed.
     */
    public boolean addRemoveInPlaylist(final int id, final Song song) {
        if (id >= createdPlaylists.size()) {
            throw new IllegalArgumentException(
                    "Provided id higher than amount of created playlists."
            );
        }
        Playlist playlist = createdPlaylists.get(id);
        return playlist.addRemove(song);
    }

    /**
     * Follows or unfollows the provided playlist.
     * @return true if the playlist was followed; false if the playlist was unfollowed.
     */
    public boolean followUnfollow(final Playlist playlist) {
        if (followedPlaylists.contains(playlist)) {
            playlist.setFollowers(playlist.getFollowers() - 1);
            followedPlaylists.remove(playlist);
            return false;
        } else {
            playlist.setFollowers(playlist.getFollowers() + 1);
            followedPlaylists.add(playlist);
            return true;
        }
    }

    /**
     * Cycles through to the next repeatType of the player.
     * @return new repeatType
     */
    public RepeatType switchRepeat(final int timestamp) {
        updatePlaying(timestamp);
        this.repeatType = switch (this.repeatType) {
            case NO -> RepeatType.ALL;
            case ALL -> RepeatType.CURRENT;
            case CURRENT -> RepeatType.NO;
            default -> throw new IllegalArgumentException("Invalid repeatType");
        };
        return this.repeatType;
    }

/*    public void ForwardBackward(int timestamp, int timeToSkip) {
        UpdatePlaying(timestamp);
        if (audioPlaying.getDuration() - remainedTime < 90) {
            remainedTime = audioPlaying.getDuration();
        } else if (remainedTime - 90 < 0) {
            PlayAudio(currentlyLoaded.getNextAfter(audioPlaying));
        } else {
            remainedTime += 90;
        }
    }*/

    /**
     * Forwards the player by 90s or skips to the next audio file.
     */
    public void forward(final int timestamp) {
        updatePlaying(timestamp);
        if (remainedTime - windingSpeed < 0) {
            playAudio(currentlyLoaded.getNextAfter(audioPlaying));
        } else {
            remainedTime -= windingSpeed;
        }
    }

    /**
     * Rewinds the player by 90s or to the start of the audio file.
     */
    public void backward(final int timestamp) {
        updatePlaying(timestamp);
        if (audioPlaying.getDuration() - remainedTime < windingSpeed) {
            remainedTime = audioPlaying.getDuration();
        } else {
            remainedTime += windingSpeed;
        }
    }
}
