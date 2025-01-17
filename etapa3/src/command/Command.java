package command;

import com.fasterxml.jackson.annotation.*;
import command.adders.AddAlbum;
import command.adders.AddAnnouncement;
import command.adders.AddEvent;
import command.adders.AddMerch;
import command.adders.AddPodcast;
import command.adders.AddUser;
import command.adders.CreatePlaylist;
import command.monetization.AdBreak;
import command.monetization.BuyMerch;
import command.monetization.BuyPremium;
import command.monetization.CancelPremium;
import command.removers.DeleteUser;
import command.removers.RemoveAlbum;
import command.removers.RemoveAnnouncement;
import command.removers.RemoveEvent;
import command.removers.RemovePodcast;
import command.response.Response;
import command.stats.*;
import command.unimplemented.LoadRecommendations;
import command.unimplemented.NextPage;
import command.unimplemented.PreviousPage;
import command.unimplemented.UpdateRecommendations;
import data.Library;
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
        // etapa1
        @JsonSubTypes.Type(value = CommandSearch.class, name = "search"),
        @JsonSubTypes.Type(value = CommandSelect.class, name = "select"),
        @JsonSubTypes.Type(value = CommandLoad.class, name = "load"),
        @JsonSubTypes.Type(value = CommandPlayPause.class, name = "playPause"),
        @JsonSubTypes.Type(value = CommandRepeat.class, name = "repeat"),
        @JsonSubTypes.Type(value = CommandShuffle.class, name = "shuffle"),
        @JsonSubTypes.Type(value = CommandForward.class, name = "forward"),
        @JsonSubTypes.Type(value = CommandBackward.class, name = "backward"),
        @JsonSubTypes.Type(value = CommandLike.class, name = "like"),
        @JsonSubTypes.Type(value = CommandNext.class, name = "next"),
        @JsonSubTypes.Type(value = CommandPrev.class, name = "prev"),
        @JsonSubTypes.Type(value = CommandModify.class, name = "addRemoveInPlaylist"),
        @JsonSubTypes.Type(value = Status.class, name = "status"),
        @JsonSubTypes.Type(value = CreatePlaylist.class, name = "createPlaylist"),
        @JsonSubTypes.Type(value = CommandSwitchVisibility.class, name = "switchVisibility"),
        @JsonSubTypes.Type(value = CommandFollowPlaylist.class, name = "follow"),
        @JsonSubTypes.Type(value = ShowPlaylists.class, name = "showPlaylists"),
        @JsonSubTypes.Type(value = ShowPreferredSongs.class, name = "showPreferredSongs"),
        @JsonSubTypes.Type(value = GetTop5Songs.class, name = "getTop5Songs"),
        @JsonSubTypes.Type(value = GetTop5Playlists.class, name = "getTop5Playlists"),
        // etapa2
        @JsonSubTypes.Type(value = AddAlbum.class, name = "addAlbum"),
        @JsonSubTypes.Type(value = AddAnnouncement.class, name = "addAnnouncement"),
        @JsonSubTypes.Type(value = AddEvent.class, name = "addEvent"),
        @JsonSubTypes.Type(value = AddMerch.class, name = "addMerch"),
        @JsonSubTypes.Type(value = AddPodcast.class, name = "addPodcast"),
        @JsonSubTypes.Type(value = AddUser.class, name = "addUser"),
        @JsonSubTypes.Type(value = DeleteUser.class, name = "deleteUser"),
        @JsonSubTypes.Type(value = RemoveAlbum.class, name = "removeAlbum"),
        @JsonSubTypes.Type(value = RemoveAnnouncement.class, name = "removeAnnouncement"),
        @JsonSubTypes.Type(value = RemoveEvent.class, name = "removeEvent"),
        @JsonSubTypes.Type(value = RemovePodcast.class, name = "removePodcast"),
        @JsonSubTypes.Type(value = GetAllUsers.class, name = "getAllUsers"),
        @JsonSubTypes.Type(value = GetOnlineUsers.class, name = "getOnlineUsers"),
        @JsonSubTypes.Type(value = GetTop5Albums.class, name = "getTop5Albums"),
        @JsonSubTypes.Type(value = GetTop5Artists.class, name = "getTop5Artists"),
        @JsonSubTypes.Type(value = PrintCurrentPage.class, name = "printCurrentPage"),
        @JsonSubTypes.Type(value = ChangePage.class, name = "changePage"),
        @JsonSubTypes.Type(value = ShowAlbums.class, name = "showAlbums"),
        @JsonSubTypes.Type(value = ShowPodcasts.class, name = "showPodcasts"),
        @JsonSubTypes.Type(value = SwitchConnectionStatus.class, name = "switchConnectionStatus"),
        // etapa3
        @JsonSubTypes.Type(value = CommandWrapped.class, name = "wrapped"),
        @JsonSubTypes.Type(value = EndProgram.class, name = "endProgram"),
        @JsonSubTypes.Type(value = BuyPremium.class, name = "buyPremium"),
        @JsonSubTypes.Type(value = CancelPremium.class, name = "cancelPremium"),
        @JsonSubTypes.Type(value = AdBreak.class, name = "adBreak"),
        @JsonSubTypes.Type(value = BuyMerch.class, name = "buyMerch"),
        @JsonSubTypes.Type(value = SeeMerch.class, name = "seeMerch"),
        @JsonSubTypes.Type(value = CommandSubscribe.class, name = "subscribe"),
        @JsonSubTypes.Type(value = GetNotifications.class, name = "getNotifications"),
        // unimplemented
        @JsonSubTypes.Type(value = UpdateRecommendations.class, name = "updateRecommendations"),
        @JsonSubTypes.Type(value = LoadRecommendations.class, name = "loadRecommendations"),
        @JsonSubTypes.Type(value = PreviousPage.class, name = "previousPage"),
        @JsonSubTypes.Type(value = NextPage.class, name = "nextPage")
})
@Getter @Setter
public abstract class Command {
    @JsonProperty("command")
    protected String command;
    @JsonProperty("timestamp")
    protected Integer timestamp;
    @JsonProperty("username")
    protected String username = null;

    @JsonIgnore
    protected final Library library = Library.getInstance();

    public Command() {
    }

    public Command(final String command, final String username, final int timestamp) {
        this.command = command;
        this.username = username;
        this.timestamp = timestamp;
    }

    /**
     * processCommand is the primary method which should be overwritten by all subclasses
     * and handles the input of any Command subclass according to its form.
     */
    public abstract Response processCommand();
}
