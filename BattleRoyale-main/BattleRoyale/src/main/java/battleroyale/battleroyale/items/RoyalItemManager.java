package battleroyale.battleroyale.items;

import battleroyale.battleroyale.db.Callback;
import battleroyale.battleroyale.db.SqlManager;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class RoyalItemManager {
    private static Random random = new Random();
    private static final Map<Integer, RoyalItemPrototype> PROTOTYPES = new HashMap<>();
    private static final Map<Integer, Map<Integer, RoyalItem>> PROTOTYPES_CACHE = new HashMap<>();
    public RoyalItemManager() {
    }
    public static void initialize() {
        SqlManager.findAsync("SELECT * FROM royal_items", new Callback() {
            @Override
            public void onQueryDone(ResultSet result) {
                Throwable var1 = null;
                try {
                    while (result.next()) {
                        int id = result.getInt("item_id");
                        PROTOTYPES.put(id, new RoyalItemPrototype(id, result));
                    }
                } catch (Throwable var11) {
                    var1 = var11;
                    try {
                        throw var11;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } finally {
                    if (result != null) {
                        if (var1 != null) {
                            try {
                                result.close();
                            } catch (Throwable var10) {
                                var1.addSuppressed(var10);
                            }
                        } else {
                            try {
                                result.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
    }
    public static RoyalItem getItem(ItemStack is) {
        if (is != null && is.hasItemMeta()) {
            ItemMeta im = is.getItemMeta();
            if (!im.hasLore()) {
                return null;
            } else {
                List<String> lore = im.getLore();
                if (lore.isEmpty()) {
                    return null;
                } else {
                    String[] info = ChatColor.stripColor(lore.get(lore.size() - 1)).split("\\-");
                    return getItem(info);
                }
            }
        } else {
            return null;
        }
    }
    public static RoyalItem getItem(String[] info) {
        try {
            return getItem(i(info[0]));
        } catch (NumberFormatException var2) {
            return null;
        }
    }
    public static RoyalItem getItem(int prototypeId) {
        RoyalItemPrototype prototype = PROTOTYPES.get(prototypeId);
        if (prototype == null) {
            return null;
        } else {

            Map<Integer, RoyalItem> cache = PROTOTYPES_CACHE.get(prototypeId);
            int key = random.nextInt(100000);
            if (cache == null) {
                cache = new HashMap<>();
                PROTOTYPES_CACHE.put(prototypeId, cache);
            }
            RoyalItem item = (RoyalItem) ((Map<?, ?>)cache).get(key);
            if (item != null) {
                return item;
            } else {
                RoyalItem item1;
                if (prototype instanceof RoyalUsableItem) {
                    item1 = new RoyalUsItem(prototype);
                } else if (prototype.getStat(RoyalItemStat.ARMOR) == 0 && prototype.getStat(RoyalItemStat.MAGIC_ARMOR) == 0) {
                    if (prototype.getStat(RoyalItemStat.MAX_DAMAGE) == 0 && prototype.getStat(RoyalItemStat.MAX_MAGIC_DAMAGE) == 0) {
                        item1 = new RoyalItem(prototype);
                    } else {
                        item1 = new RoyalWeapon(prototype);
                    }
                } else {
                    item1 = new RoyalArmor(prototype);
                }

                ((Map)cache).put(key, item1);
                return item1;
            }
        }
    }
    static void setupUsableItem(RoyalUsableItem prototype) {
        PROTOTYPES.put(prototype.getId(), prototype);
    }
    public static RoyalItemPrototype getPrototype(int prototypeId) {
        return (RoyalItemPrototype) PROTOTYPES.get(prototypeId);
    }
    static int i(String s) {
        return Integer.parseInt(s);
    }
}
