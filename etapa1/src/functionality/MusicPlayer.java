package functionality;

import data.*;
import lombok.Getter;
import lombok.Setter;

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
    private int lastUpdateTime;

    private AudioFile audioPlaying;
    private ISelectable currentlyLoaded;
    private ISelectable currentSelection;
    private List<ISelectable> searchResults;
//        "stats" : {
//                "name" : "The Power of Design",
//                "remainedTime" : 3065,
//                "repeat" : "No Repeat",
//                "shuffle" : false,
//                "paused" : false
//        }

    public MusicPlayer() {}

    public void LoadAudio() {
        this.setCurrentlyLoaded(this.getCurrentSelection());

        switch (currentlyLoaded.getType()) {
            case SONG:
                this.audioPlaying = (Song)this.getCurrentSelection();
                this.remainedTime = audioPlaying.getDuration();
                break;
            case PLAYLIST:
                this.audioPlaying = (Song)(((Playlist)this.getCurrentSelection()).getSongList().get(0));
                this.remainedTime = audioPlaying.getDuration();
                break;
            case PODCAST:
                this.audioPlaying = (Episode)(((Podcast)this.getCurrentSelection()).getEpisodes().get(0));
                this.remainedTime = audioPlaying.getDuration();
                break;
            default:
                throw new IllegalArgumentException("Invalid loadType");
        }
    }

    public void UpdatePlaying() {
        if (!isPlaying) return;

        if (audioPlaying == null) return;

        remainedTime -= 5;
    }
}
