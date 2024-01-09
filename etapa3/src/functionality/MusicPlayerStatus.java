package functionality;

import data.ISelectable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MusicPlayerStatus {
    private String name;
    private int remainedTime;
    private String repeat;
    private boolean shuffle, paused;

    public MusicPlayerStatus() {
    }

    public MusicPlayerStatus(final MusicPlayer player) {
        this.name = player.getAudioPlaying() != null ? player.getAudioPlaying().getName() : "";
        ISelectable currentlyLoaded = player.getCurrentlyLoaded();
        switch (player.getRepeatType()) {
            case NO:
                this.repeat = "No Repeat";
                break;
            case ALL:
                if (currentlyLoaded != null
                        && (currentlyLoaded.getType() == ISelectable.SearchType.PLAYLIST
                        || currentlyLoaded.getType() == ISelectable.SearchType.ALBUM)) {
                    this.repeat = "Repeat All";
                } else {
                    this.repeat = "Repeat Once";
                }
                break;
            case CURRENT:
                if (currentlyLoaded != null
                        && (currentlyLoaded.getType() == ISelectable.SearchType.PLAYLIST
                        || currentlyLoaded.getType() == ISelectable.SearchType.ALBUM)) {
                    this.repeat = "Repeat Current Song";
                } else {
                    this.repeat = "Repeat Infinite";
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid repeatType");
        }

        this.remainedTime = player.getRemainedTime();
        this.shuffle = player.isShuffle();
        this.paused = !player.isPlaying();
    }
}
