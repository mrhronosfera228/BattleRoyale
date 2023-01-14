package battleroyale.battleroyale.items;

import battleroyale.battleroyale.utils.UtilColor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.List;

public class RoyalItem {
    private final RoyalItemPrototype prototype;
    private final int[] cachedStats;
    private ItemStack itemStack;

    protected RoyalItem(RoyalItemPrototype prototype) {
        this.prototype = prototype;
        this.cachedStats = new int[prototype.getStats().length];
        this.calculateCachedStats();
    }

    private  void calculateCachedStats() {
        RoyalItemStat[] var1 = RoyalItemStat.values();
        int mnd = var1.length;

        int d;
        for (d = 0; d < mnd; ++d) {
            RoyalItemStat stat = var1[d];
            this.cachedStats[stat.ordinal()] = this.prototype.getStats()[stat.ordinal()];
        }

    }
    public String getUncoloredInfo() {
        return this.getId() + "-" + "0-" + "0-" + "0";
    }
    public String getName() {
        return UtilColor.toColor("%s%s", new Object[]{this.prototype.getQuality().getUncoloredPrefix(), this.prototype.getName()});
    }
    public ItemStack getItemStack(Player p) {
        if (p == null && this.itemStack != null) {
            return this.itemStack;
        } else {
            List<String> description = new ArrayList<>();
            description.add(String.format("&7Качество: %s%s", this.prototype.getQuality().getUncoloredPrefix(), this.prototype.getQuality().getName()));
            this.addLine(description);
            this.changeList(description, "&4☠ &7Урон: %d-%d", RoyalItemStat.MIN_DAMAGE, RoyalItemStat.MAX_DAMAGE);
            this.changeList(description, "&9☠ &7Магический урон: %d-%d", RoyalItemStat.MIN_MAGIC_DAMAGE, RoyalItemStat.MAX_MAGIC_DAMAGE);
            this.changeList(description, "&6⚡ &7Шанс крит. удара: %d%%", RoyalItemStat.CRITICAL_CHANCE);
            this.changeList(description, "&c⚡ &7Сила крита: %d%%", RoyalItemStat.CRITICAL_STRENGTH);
            this.changeList(description, "&7☗ &7Броня: %d", RoyalItemStat.ARMOR);
            this.changeList(description, "&b☗ &7Магическая броня: %d", RoyalItemStat.MAGIC_ARMOR);
            this.changeList(description, "&4❤ &7Здоровье: %d", RoyalItemStat.HEALTH);
            this.changeList(description, "&2✤ &7Регенерация: %d", RoyalItemStat.REGENERATION);
            this.addLine(description);
            if (this.prototype.getDescription() != null) {
                this.prototype.getDescription().stream().map((s) -> "&7&o" + s).forEach(description::add);
            }
            description.add("&8" + this.getUncoloredInfo());
            ItemStack is = (new SimpleItemStack(Material.getMaterial(this.getStat(RoyalItemStat.ORIGINAL_ID)),  1, this.getName(), description, (short)this.getStat(RoyalItemStat.ORIGINAL_DATA)));
            if (p == null) {
                this.itemStack = is;
            }

            return is;
        }
    }
    public ItemStack getItemStack() {
            List<String> description = new ArrayList<>();
            description.add(String.format("&7Качество: %s%s", this.prototype.getQuality().getUncoloredPrefix(), this.prototype.getQuality().getName()));
            this.addLine(description);
            this.changeList(description, "&4☠ &7Урон: %d-%d", RoyalItemStat.MIN_DAMAGE, RoyalItemStat.MAX_DAMAGE);
            this.changeList(description, "&9☠ &7Магический урон: %d-%d", RoyalItemStat.MIN_MAGIC_DAMAGE, RoyalItemStat.MAX_MAGIC_DAMAGE);
            this.changeList(description, "&6⚡ &7Шанс крит. удара: %d%%", RoyalItemStat.CRITICAL_CHANCE);
            this.changeList(description, "&c⚡ &7Сила крита: %d%%", RoyalItemStat.CRITICAL_STRENGTH);
            this.changeList(description, "&7☗ &7Броня: %d", RoyalItemStat.ARMOR);
            this.changeList(description, "&b☗ &7Магическая броня: %d", RoyalItemStat.MAGIC_ARMOR);
            this.changeList(description, "&4❤ &7Здоровье: %d", RoyalItemStat.HEALTH);
            this.changeList(description, "&2✤ &7Регенерация: %d", RoyalItemStat.REGENERATION);
            this.addLine(description);
            if (this.prototype.getDescription() != null) {
                this.prototype.getDescription().stream().map((s) -> "&7&o" + s).forEach(description::add);
            }
            description.add("&8" + this.getUncoloredInfo());
        return new SimpleItemStack(Material.getMaterial(this.getStat(RoyalItemStat.ORIGINAL_ID)),  1, this.getName(), description, (short) this.getStat(RoyalItemStat.ORIGINAL_DATA));
    }
    private void setStat(RoyalItemStat stat, int value) {
        this.cachedStats[stat.ordinal()] = value;
    }

    protected int getStat(RoyalItemStat stat) {
        return this.cachedStats[stat.ordinal()];
    }

    public int getId() {
        return this.prototype.getId();
    }

    private void changeList(List<String> description, String format, RoyalItemStat... stats) {
        Object[] values = new Object[stats.length];
        boolean any = false;

        for(int i = 0; i < stats.length; ++i) {
           RoyalItemStat stat = stats[i];
            int v = this.getStat(stat);
            if (v != 0) {
                any = true;
            }

            values[i] = v;
        }

        if (any) {
            description.add(String.format(format, values));
        }
    }
    public RoyalItemPrototype getPrototype() {
        return this.prototype;
    }
    private void addLine(List<String> description) {
        if (!description.isEmpty()) {
            String last = (String)description.get(description.size() - 1);
            last = ChatColor.stripColor(UtilColor.toColor(last));
            if (!last.isEmpty()) {
                description.add("");
            }

        }
    }
    public boolean giveToPlayer(Player p, int amount) {
        ItemStack is = this.getItemStack(p);
        int mss = is.getMaxStackSize();
        PlayerInventory pinv = p.getInventory();
        ItemStack[] contents = pinv.getContents();
        int first_air = -1;

        for(int i = 0; i < contents.length; ++i) {
            ItemStack current = contents[i];
            if (current != null && current.getType() != Material.AIR) {
                RoyalItem item = RoyalItemManager.getItem(current);
                if (item != null && item.equals(this)) {
                    int am = current.getAmount();
                    if (am + amount <= mss) {
                        current.setAmount(am + amount);
                        contents[i] = current;
                        return true;
                    }

                    if (am != mss) {
                        current.setAmount(mss);
                        amount -= mss - am;
                        contents[i] = current;
                    }
                }
            } else if (first_air == -1) {
                first_air = i;
            }
        }

        if (amount > 0) {
            if (first_air == -1) {
                return false;
            }

            is.setAmount(amount);
            contents[first_air] = is;
        }

        pinv.setContents(contents);
        return true;
    }
}
