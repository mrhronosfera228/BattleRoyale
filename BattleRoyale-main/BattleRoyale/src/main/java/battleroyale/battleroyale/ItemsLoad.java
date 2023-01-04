package battleroyale.battleroyale;

import battleroyale.battleroyale.db.Callback;
import battleroyale.battleroyale.db.SqlManager;
import battleroyale.battleroyale.items.RoyalItemManager;
import battleroyale.battleroyale.items.RoyalItemPrototype;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ItemsLoad {
    public static List<ItemStack> qCommon = new ArrayList<>();
    public static List<ItemStack> qRare = new ArrayList<>();
    public static List<ItemStack> qEpic = new ArrayList<>();
    public static List<ItemStack> qLegendary = new ArrayList<>();
    private static final Map<Integer, RoyalItemPrototype> common = new HashMap<>();
    private static final Map<Integer, RoyalItemPrototype> rare = new HashMap<>();
    private static final Map<Integer, RoyalItemPrototype> epic = new HashMap<>();
    private static final Map<Integer, RoyalItemPrototype> legendary = new HashMap<>();


    public static void load() {
        toLoad(qCommon, "royal_items", "COMMON", common);
        toLoad(qRare, "royal_items", "UNCOMMON", rare);
        toLoad(qEpic, "royal_items", "EPIC", epic);
        toLoad(qLegendary, "royal_items", "LEGENDARY", legendary);
    }
    public static void toLoad(List<ItemStack> list, String tableName, String quality, Map<Integer, RoyalItemPrototype> prototype) {
        SqlManager.findAsync("SELECT * FROM " + tableName + " WHERE quality = '" + quality + "'", new Callback() {
            @Override
            public void onQueryDone(ResultSet result) {
                try {
                    while (result.next()) {
                        int id = result.getInt("item_id");
                        System.out.println(id);
                        prototype.put(id, new RoyalItemPrototype(id, result));
                    }
                    List<Integer> ids = new ArrayList<>(prototype.keySet());
                    for (int i = 0; i < ids.size(); i++) {
                        if (RoyalItemManager.getItem(ids.get(i)).getItemStack() != null) {
                            list.add(RoyalItemManager.getItem(ids.get(i)).getItemStack());
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        result.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }
}
