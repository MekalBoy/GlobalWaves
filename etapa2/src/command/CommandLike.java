package command;

import command.response.ResponseMsg;
import data.AudioFile;
import data.ISelectable;
import data.Library;
import data.Song;
import functionality.MusicPlayer;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommandLike extends Command {

    @Override
    public final ResponseMsg processCommand() {
        String message = "";

        if (!Library.instance.seekUser(this.username).isOnline()) {
            message = this.username
                    + " is offline.";
        } else {
            MusicPlayer player = Library.instance.seekUser(this.username).getPlayer();
            player.updatePlaying(timestamp);

            AudioFile audio = player.getAudioPlaying();

            if (audio == null) {
                message = "Please load a source before liking or unliking.";
            } else if (audio.getType() != ISelectable.SearchType.SONG) {
                message = "Loaded source is not a song.";
            } else {
                boolean isLiked = player.likeUnlike((Song) audio);
                message = isLiked
                        ? "Like registered successfully."
                        : "Unlike registered successfully.";
            }
        }

        return new ResponseMsg(this, message);
    }
}
