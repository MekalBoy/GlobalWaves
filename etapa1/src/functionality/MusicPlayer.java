package functionality;

import data.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter @Setter
public class MusicPlayer {
    public enum RepeatType {
        NO,
        ALL,
        CURRENT;
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

    private Map<Podcast, Episode> resumePodcasts = new HashMap<Podcast, Episode>();
    private Map<Episode, Integer> resumeEpisodes = new HashMap<Episode, Integer>();

    public MusicPlayer() {}

    public void flushPlayer(int timestamp) {
        UpdatePlaying(timestamp);

        if (currentlyLoaded != null && currentlyLoaded.getType() == SearchBar.SearchType.PODCAST) {
            Podcast podcast = (Podcast) currentSelection;
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

        //if (audioPlaying != null) isPlaying = !isPlaying;
        audioPlaying = null;
        currentlyLoaded = null;
        currentSelection = null;
        searchResults = null;
    }

    public boolean LoadAudio(int timestamp) {
        this.setCurrentlyLoaded(this.getCurrentSelection());

        switch (currentlyLoaded.getType()) {
            case SONG:
                this.audioPlaying = (Song)this.getCurrentSelection();
                this.remainedTime = audioPlaying.getDuration();
                break;
            case PLAYLIST:
                if (((Playlist)this.getCurrentSelection()).getSongList().isEmpty()) return false;
                this.audioPlaying = ((Playlist)this.getCurrentSelection()).getSongList().get(0);
                this.remainedTime = audioPlaying.getDuration();
                break;
            case PODCAST:
                Podcast podcast = ((Podcast)this.getCurrentSelection());

                if (podcast.getEpisodes().isEmpty()) return false;

                if (resumePodcasts.containsKey(podcast)) { // we've played this podcast before
                    Episode episode = resumePodcasts.get(podcast);
                    this.audioPlaying = episode;
                    if (resumeEpisodes.containsKey(episode)) { // this is where we left off
                        this.remainedTime = resumeEpisodes.get(episode);
                    } else { // first time we play this episode
                        this.remainedTime = episode.getDuration();
                    }
                } else { // we haven't played this podcast before
                    this.audioPlaying = ((Podcast)this.getCurrentSelection()).getEpisodes().get(0);
                    this.remainedTime = audioPlaying.getDuration();
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid loadType");
        }

        this.lastUpdateTime = timestamp;
        return true;
    }

    public void PlayAudio(AudioFile audioFile) {
        this.audioPlaying = audioFile;
        this.remainedTime = audioFile.getDuration();
    }

    public void UpdatePlaying(int timestamp) {
        if (!isPlaying) return;  // when paused we don't need to update anything

        if (audioPlaying == null) return;

        remainedTime -= (timestamp - lastUpdateTime);
        lastUpdateTime = timestamp;

        if (remainedTime <= 0) {
            audioPlaying = null;
            remainedTime = 0;
            TogglePlayPause(timestamp);
            if (!currentlyLoaded.isCollection()) {  // It was a single song, remove it.
                currentlyLoaded = null;
            } /*else {  // Podcast or Playlist (collection), so try and get the next song/episode
                AudioFile nextThing =
                PlayAudio();
            }*/  // TODO HERE PROBABLY FOR TEST05
        }
    }

    public void TogglePlayPause(int timestamp) {
        if (isPlaying) {
            UpdatePlaying(timestamp);
        }
        lastUpdateTime = timestamp;
        isPlaying = !isPlaying;
    }

    public boolean LikeUnlike(Song song) {
        if (likedSongs.contains(song)) {
            likedSongs.remove(song);
            return false;
        } else {
            likedSongs.add(song);
            return true;
        }
    }
}
