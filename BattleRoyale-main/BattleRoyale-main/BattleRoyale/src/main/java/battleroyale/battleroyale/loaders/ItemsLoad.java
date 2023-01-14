package battleroyale.battleroyale.loaders;

import battleroyale.battleroyale.db.Callback;
import battleroyale.battleroyale.db.SqlManager;
import battleroyale.battleroyale.items.RoyalItemManager;
import battleroyale.battleroyale.items.RoyalItemPrototype;
import org.bukkit.inventory.ItemStack;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ItemsLoad {
    private static List<ItemStack> qCommon = new ArrayList<>();
    private static List<ItemStack> qRare = new ArrayList<>();
    private static List<ItemStack> qEpic = new ArrayList<>();
    private static List<ItemStack> qLegendary = new ArrayList<>();
    private static final Map<Integer, RoyalItemPrototype> common = new HashMap<>();
    private static final Map<Integer, RoyalItemPrototype> rare = new HashMap<>();
    private static final Map<Integer, RoyalItemPrototype> epic = new HashMap<>();
    private static final Map<Integer, RoyalItemPrototype> legendary = new HashMap<>();


    public static void load() {
        toLoad(qCommon, "COMMON", common);
        toLoad(qRare, "UNCOMMON", rare);
        toLoad(qEpic, "EPIC", epic);
        toLoad(qLegendary, "LEGENDARY", legendary);
    }
    private static void toLoad(List<ItemStack> list, String quality, Map<Integer, RoyalItemPrototype> prototype) {
        SqlManager.findAsync(SqlManager.prepare("SELECT * FROM royal_items WHERE quality = ?",  quality),"SELECT * FROM royal_items WHERE quality = ?", new Callback() {
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

    public static List<ItemStack> getqCommon() {
        return qCommon;
    }

    public static List<ItemStack> getqEpic() {
        return qEpic;
    }

    public static List<ItemStack> getqLegendary() {
        return qLegendary;
    }

    public static List<ItemStack> getqRare() {
        return qRare;
    }
}
