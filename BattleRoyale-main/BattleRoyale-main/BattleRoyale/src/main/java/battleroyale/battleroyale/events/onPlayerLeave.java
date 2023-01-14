package battleroyale.battleroyale.events;

import battleroyale.battleroyale.loaders.InventoryLoad;
import battleroyale.battleroyale.loaders.PlayerTeamLoad;
import battleroyale.battleroyale.player.RoyalPlayer;
import battleroyale.battleroyale.utils.UtilColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Team;

import static battleroyale.battleroyale.GameLogic.Game.GameStart;
import static battleroyale.battleroyale.GameLogic.Game.StopGame;
import static battleroyale.battleroyale.events.ItemClickChangeTeam.board;

public class onPlayerLeave implements Listener {
    public onPlayerLeave(){

    }
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Team team = board.getEntryTeam(player.getName());
        if (team != null) {
            if (!GameStart) {
                team.removeEntry(player.getName());
                if (ItemClickChangeTeam.PlayerSlot.get(player.getName()) != null && ItemClickChangeTeam.PlayerZnak.get(player.getName()) != null && ItemClickChangeTeam.PlayerTeam.get(player.getName()) != null) {
                    ItemStack item = InventoryLoad.getTeamChangeInventory().getItem(ItemClickChangeTeam.PlayerSlot.get(player.getName()));
                    ItemMeta meta = item.getItemMeta();
                    meta.setLore(UtilColor.toColor(ItemClickChangeTeam.loreUpdate(ItemClickChangeTeam.PlayerTeam.get(player.getName()), ItemClickChangeTeam.PlayerZnak.get(player.getName()))));
                    item.setItemMeta(meta);
                    InventoryLoad.getTeamChangeInventory().setItem(ItemClickChangeTeam.PlayerSlot.get(player.getName()), item);
                }
            } else {
                team.removeEntry(player.getName());
                int countAlive = 0;
                for (String playerName : team.getEntries()) {
                    Player teamPlayer = Bukkit.getPlayer(playerName);
                    if (!teamPlayer.getName().equals(player.getName())) {
                        if (RoyalPlayer.isAlive(teamPlayer)) {
                            countAlive++;
                        }
                    }
                }
                if (countAlive == 0) {
                    PlayerTeamLoad.teams.remove(team.getName());
                    if (PlayerTeamLoad.teams.size() == 1) {
                        StopGame();
                    }
                }
            }
        }
    }
}
