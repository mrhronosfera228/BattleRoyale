package battleroyale.battleroyale.events;

import battleroyale.battleroyale.items.RoyalItem;
import battleroyale.battleroyale.player.RoyalPlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerDamagePlayerRoyalEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final RoyalPlayer damager;
    private final RoyalPlayer target;
    private int damage;
    private final RoyalItem weapon;
    private boolean cancelled;
    private boolean withBow;

    /*public PlayerDamagePlayerRoyalEvent(RoyalPlayer damager, RoyalPlayer target, RoyalItem weapon, boolean withBow) {
        this(damager, target, weapon, (double)onPlayerDamage.getFinalDamage(damager, weapon, target), withBow);
    }*/

    public PlayerDamagePlayerRoyalEvent(RoyalPlayer damager, RoyalPlayer target, RoyalItem weapon, double damage, boolean withBow) {
        this.damager = damager;
        this.target = target;
        this.weapon = weapon;
        this.damage = (int)damage;
        this.withBow = withBow;
    }

    public boolean withBow() {
        return this.withBow;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public RoyalPlayer getDamager() {
        return this.damager;
    }

    public RoyalPlayer getTarget() {
        return this.target;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return this.damage;
    }

    public RoyalItem getWeapon() {
        return this.weapon;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }
}
