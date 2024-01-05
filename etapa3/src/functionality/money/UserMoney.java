package functionality.money;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public final class UserMoney {
    private boolean isPremium = false;

    /**
     * Toggles premium status on and off.
     * @return Whatever the status is now.
     */
    public boolean togglePremium() {
        isPremium = !isPremium;
        return isPremium;
    }
}
