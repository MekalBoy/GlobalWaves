package functionality;

import data.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    public MusicPlayer() {}

    public void LoadAudio(int timestamp) {
        this.setCurrentlyLoaded(this.getCurrentSelection());

        switch (currentlyLoaded.getType()) {
            case SONG:
                this.audioPlaying = (Song)this.getCurrentSelection();
                this.remainedTime = audioPlaying.getDuration();
                break;
            case PLAYLIST:
                this.audioPlaying = ((Playlist)this.getCurrentSelection()).getSongList().get(0);
                this.remainedTime = audioPlaying.getDuration();
                break;
            case PODCAST:
                this.audioPlaying = ((Podcast)this.getCurrentSelection()).getEpisodes().get(0);
                this.remainedTime = audioPlaying.getDuration();
                break;
            default:
                throw new IllegalArgumentException("Invalid loadType");
        }

        this.lastUpdateTime = timestamp;
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
            if (currentlyLoaded instanceof AudioFile) {  // It was a single song, remove it.
                currentlyLoaded = null;
            } /*else {  // Podcast or Playlist (collection), so try and get the next song/episode
                AudioFile nextThing =
                PlayAudio();
            }*/
        }
    }

    public void TogglePlayPause(int timestamp) {
        if (isPlaying) {
            UpdatePlaying(timestamp);
        }
        lastUpdateTime = timestamp;
        isPlaying = !isPlaying;
    }
}
