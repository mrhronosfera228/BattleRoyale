package battleroyale.battleroyale.items;

import org.bukkit.entity.Player;

public abstract class RoyalUsableItem extends RoyalItemPrototype {
    protected RoyalUsableItem(int id) {
        super(RoyalItemManager.getPrototype(id));
        RoyalItemManager.setupUsableItem(this);
    }
    public abstract void onUse(Player var1);
}
