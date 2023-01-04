package battleroyale.battleroyale.events;

import battleroyale.battleroyale.testMessage.Actionbar;
import battleroyale.battleroyale.utils.UtilColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.Collections;
import java.util.List;

public class onPlayerJoin implements Listener {
    Actionbar actionbar;
    public onPlayerJoin() {

    }
    @EventHandler
    public void onPlayerjoin(PlayerJoinEvent event) {
        if (event.getPlayer() != null) {
            Player player = event.getPlayer();
            player.getInventory().clear();
            player.setGameMode(GameMode.ADVENTURE);
            player.getInventory().addItem(CreateItem(Material.WOOL, "&6Выбор команды", "", 0));
        }
    }
    public static ItemStack CreateItem(Material material, String name, String lore, int data) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(UtilColor.toColor(name));
        meta.setLore(Collections.singletonList(UtilColor.toColor(lore)));
        itemStack.setDurability((short) data);
        itemStack.setItemMeta(meta);
        return  itemStack;
    }
    public static ItemStack CreateItem(Material material, String name, List<String> lore, int data) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(UtilColor.toColor(name));
        meta.setLore(UtilColor.toColor(lore));
        itemStack.setDurability((short) data);
        itemStack.setItemMeta(meta);
        return  itemStack;
    }
}
