package battleroyale.battleroyale;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static battleroyale.battleroyale.GameLogic.Game.CreateItem;

public class InventoryLoad {
    private static Inventory TeamChangeInventory;
    public InventoryLoad() {
    }
    public static void load() {
        TeamChangeInventory = createInventory( "Выбор команды");
        changeInventory(TeamChangeInventory, CreateItem(Material.WOOL, "&dРозовые", "&fИгроков: 0/2", 6), 0);
        changeInventory(TeamChangeInventory, CreateItem(Material.WOOL, "&9Синие", "&fИгроков: 0/2", 11), 1);
    }
    public static Inventory createInventory(String name) {
        return Bukkit.createInventory(null, 18, name);
    }
    public static void changeInventory(Inventory inventory, ItemStack item, int slot) {
        inventory.setItem(slot, item);
    }

    public static Inventory getTeamChangeInventory() {
        return TeamChangeInventory;
    }
}
