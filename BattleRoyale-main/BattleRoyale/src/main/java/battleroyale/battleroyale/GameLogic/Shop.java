package battleroyale.battleroyale.GameLogic;

import battleroyale.battleroyale.BattleRoyale;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Shop implements Listener {
    Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
    @EventHandler
    public void onOpenShop(PlayerInteractEvent event) {
        if (Game.GameStart) {
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType().equals(Material.PUMPKIN)) {
                Player player = event.getPlayer();
                player.openInventory(Game.ShopInventory.get(player.getName()));
            }
        }
    }
    @EventHandler
    public void  onShopClicked(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getInventory().equals(Game.ShopInventory.get(player.getName()))) {
            if (event.getCurrentItem() != null) {
                if ((event.getInventory().getItem(0) != null && event.getInventory().getItem(0).equals(event.getCurrentItem())) || (event.getInventory().getItem(1) != null && event.getInventory().getItem(1).equals(event.getCurrentItem()))) {
                    event.setCancelled(true);
                    ItemStack buyPlayer = event.getCurrentItem();
                    ItemMeta meta = buyPlayer.getItemMeta();
                    Team team = board.getEntryTeam(player.getName());
                    if (team.hasEntry(ChatColor.stripColor(meta.getDisplayName()))) {
                        Player purchasedPlayer = Bukkit.getPlayer(ChatColor.stripColor(meta.getDisplayName()));
                        for (String playerName : team.getEntries()) {
                            Player teamPlayer = Bukkit.getPlayer(playerName);
                            if (!teamPlayer.getName().equals(purchasedPlayer.getName())) {
                                Game.ShopInventory.get(teamPlayer.getName()).remove(event.getCurrentItem());
                            }
                        }
                        purchasedPlayer.setGameMode(GameMode.ADVENTURE);
                        purchasedPlayer.teleport(player.getLocation());
                        BattleRoyale.sendMessage(player, "&aВы успешно выкупили своего союзника.");
                    } else {
                        BattleRoyale.sendMessage(player, "&cДанный игрок вышел");
                        event.getInventory().remove(event.getCurrentItem());
                    }
                }
            }
        }
    }
}
