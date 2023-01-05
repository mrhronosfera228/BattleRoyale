package battleroyale.battleroyale.player;

import battleroyale.battleroyale.BattleRoyale;
import battleroyale.battleroyale.events.RoyalPlayerDeathEvent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;
import java.util.List;

public class RoyalPlayer extends RoyalDamageable{
    private static List<String> players = new ArrayList<>();

    private ItemStack MapItem;

    public ItemStack getMap() {return MapItem;}

    public void setMap(ItemStack is) {MapItem = is;}

    public static RoyalPlayer getPlayer(String name) {
        if (!players.contains(name)) {
            RoyalPlayer rp = new RoyalPlayer(name);
            players.add(name);

            return rp;
        } else {
            return new RoyalPlayer(name);
        }
    }

    private RoyalPlayer(String name) {
        super(Bukkit.getPlayer(name));
        Player p = this.getPlayer();
        if (p != null) {
            if (!players.contains(p.getName())) {
                players.add(p.getName());
            }
        }
    }
    public void setArmor(int value) {
        Player p = this.getPlayer();
        if (p != null) {
            p.setMetadata("royal_armor", new FixedMetadataValue(BattleRoyale.getInstance(), value));
        }
    }
    public void setMagicArmor(int value) {
        Player p = this.getPlayer();
        if (p != null) {
            p.setMetadata("royal_marmor", new FixedMetadataValue(BattleRoyale.getInstance(), value));
        }
    }
    public void setRegeneration(int value) {
        if (value < 0) {
            value = 0;
        }
        Player p = this.getPlayer();
        if (p != null) {
            p.setMetadata("royal_regeneration", new FixedMetadataValue(BattleRoyale.getInstance(), value));
        }
    }
    @Override
    public void kill(RoyalDamageable killer) {
        Player p = this.getPlayer();
        if (killer != null) {
            if (killer instanceof RoyalPlayer) {
                //начислить в базу данных убийце монеты за килл и килл в стату....
            }
        }
        Bukkit.getPluginManager().callEvent(new RoyalPlayerDeathEvent(this, killer));
    }
    public int getRegeneration() {
        Player p = this.getPlayer();
        if (p != null) {
            return p.hasMetadata("royal_regeneration") ? p.getMetadata("royal_regeneration").get(0).asInt() : 0;
        } else {
            return -1;
        }
    }
    @Override
    public int getArmor() {
        Player p = this.getPlayer();
        return  p != null ? p.getMetadata("royal_armor").get(0).asInt() : -1;
    }

    @Override
    public int getMagicArmor() {
        Player p = this.getPlayer();
        return  p != null ? p.getMetadata("royal_marmor").get(0).asInt() : -1;
    }
    public void setMaxHealth(int value) {
        if (value < 10) {
            value = 10;
        }
        Player p = this.getPlayer();
        super.max_health = value;
        if (p != null) {
            p.setMetadata("royal_maxhealth", new FixedMetadataValue(BattleRoyale.getInstance(), value));
        }

        try {
            if (this.getHealth() > value) {
                this.setHealth(value);
            } else if (this.getHealth() != 0) {
                this.setHealth((this.getHealth()));
            }
        } catch (IndexOutOfBoundsException var4) {
        }
    }

    public int getMaxHealth() {
        try {
            Player p = this.getPlayer();
            if (p != null) {
                return  p.getMetadata("royal_maxhealth").get(0).asInt();
            }
        } catch (Exception var2) {
            var2.printStackTrace();
        }

        return 10;
    }

    public void setHealth(int health) {
        Player p = this.getPlayer();
        super.health = health = Math.min(health, this.getMaxHealth());
        if (health <= 0) {
            this.setHealth(this.getMaxHealth());
            this.kill(null);
        } else if (p != null) {
            p.setMetadata("royal_health", new FixedMetadataValue(BattleRoyale.getInstance(), health));
            double d = 20.0 * (double)health / (double)this.getMaxHealth();
            if (d < 0.5) {
                d = 0.5;
            }
            p.setHealth(d);
            p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(this.getMaxHealth());
        }
    }
    public static boolean isAlive(Player p) {
        return p.getGameMode().equals(GameMode.ADVENTURE);
    }

}
