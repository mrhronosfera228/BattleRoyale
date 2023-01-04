package battleroyale.battleroyale.items;

import org.bukkit.entity.Player;

public class RoyalUsItem extends RoyalItem{
    protected RoyalUsItem(RoyalItemPrototype prototype) {
        super(prototype);
    }

    public RoyalUsableItem getPrototype() {
        return (RoyalUsableItem) super.getPrototype();
    }

    public void onUse(Player p) {
        this.getPrototype().onUse(p);
    }
}
