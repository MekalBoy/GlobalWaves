package command.monetization;

import command.Command;
import command.response.ResponseMsg;
import data.Library;
import data.User;
import functionality.Page;
import functionality.money.ArtistMoney;
import functionality.money.MoneyManager;
import functionality.money.UserMoney;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BuyMerch extends Command {
    private String name;
    @Override
    public final ResponseMsg processCommand() {
        String message;

        User user = Library.getInstance().seekUser(username);

        if (user == null) {
            message = "The username " + username + " doesn't exist.";
        } else if (user.getPage().getType() != Page.PageType.ARTIST) {
            message = "Cannot buy merch from this page.";
        } else if (user.getPage().getCreator().seekMerch(name) == null) {
            message = "The merch " + name + " doesn't exist.";
        } else {
            MoneyManager manager = MoneyManager.getInstance();

            // pay artist
            User artist = user.getPage().getCreator();
            manager.getArtistDatabase().putIfAbsent(artist.getUsername(), new ArtistMoney());
            ArtistMoney artistMoney = manager.getArtistDatabase().get(artist.getUsername());
            artistMoney.addMerchRevenue(artist.seekMerch(name));

            // buy merch
            manager.getUserDatabase().putIfAbsent(username, new UserMoney());
            UserMoney userMoney = manager.getUserDatabase().get(username);
            userMoney.addToMerchList(artist.seekMerch(name));

            message = username + " has added new merch successfully.";
        }

        return new ResponseMsg(this, message);
    }
}
