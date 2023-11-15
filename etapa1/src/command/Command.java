package command;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "command",
        include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
        visible = true
//        defaultImpl = Command.class
)
@JsonSubTypes(value = {
        @JsonSubTypes.Type(value = CommandSearch.class, name = "search"),
        @JsonSubTypes.Type(value = CommandSelect.class, name = "select"),
        @JsonSubTypes.Type(value = CommandLoad.class, name = "load"),
        @JsonSubTypes.Type(value = CommandPlayPause.class, name = "playPause"),
        @JsonSubTypes.Type(value = CommandRepeat.class, name = "repeat"),
        @JsonSubTypes.Type(value = CommandShuffle.class, name = "shuffle"),
        @JsonSubTypes.Type(value = CommandForward.class, name = "forward"),
        @JsonSubTypes.Type(value = CommandBackward.class, name = "backward"),
        @JsonSubTypes.Type(value = CommandLike.class, name = "like"),
//        @JsonSubTypes.Type(value = CommandNext.class, name = "next"),
//        @JsonSubTypes.Type(value = CommandPrev.class, name = "prev"),
        @JsonSubTypes.Type(value = CommandModify.class, name = "addRemoveInPlaylist"),
        @JsonSubTypes.Type(value = CommandStatus.class, name = "status"),
        @JsonSubTypes.Type(value = CommandCreatePlaylist.class, name = "createPlaylist"),
        @JsonSubTypes.Type(value = CommandSwitchVisibility.class, name = "switchVisibility"),
        @JsonSubTypes.Type(value = CommandFollowPlaylist.class, name = "follow"),
        @JsonSubTypes.Type(value = CommandShowPlaylists.class, name = "showPlaylists"),
        @JsonSubTypes.Type(value = CommandShowPreferredSongs.class, name = "showPreferredSongs"),
        @JsonSubTypes.Type(value = GetTop5Songs.class, name = "getTop5Songs"),
        @JsonSubTypes.Type(value = GetTop5Playlists.class, name = "getTop5Playlists")
})
@Getter
@Setter
public abstract class Command {
    @JsonProperty("command")
    protected String command;
    @JsonProperty("timestamp")
    protected int timestamp;
    @JsonProperty("username")
    protected String username = null;

    public Command() {}

    public Command(String command, String username, int timestamp) {
        this.command = command;
        this.username = username;
        this.timestamp = timestamp;
    }

    public abstract Response processCommand();
}
