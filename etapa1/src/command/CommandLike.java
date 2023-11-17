package command;

import data.AudioFile;
import data.Library;
import data.Song;
import functionality.MusicPlayer;
import functionality.SearchBar;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommandLike extends Command {

    @Override
    public final ResponseMsg processCommand() {
        String message = "";

        MusicPlayer player = Library.instance.seekUser(this.username).getPlayer();
        AudioFile audio = player.getAudioPlaying();

        if (audio == null) {
            message = "Please load a source before liking or unliking.";
        } else if (audio.getType() != SearchBar.SearchType.SONG) {
            message = "Loaded source is not a song.";
        } else {
            boolean isLiked = player.likeUnlike((Song) audio);
            message = isLiked ? "Like registered successfully." : "Unlike registered successfully.";
        }

        return new ResponseMsg(this, message);
    }
}
