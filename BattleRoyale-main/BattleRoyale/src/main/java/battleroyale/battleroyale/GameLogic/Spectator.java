package battleroyale.battleroyale.GameLogic;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.*;

public class Spectator implements Listener {
    Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
    Map<String, Deque<String>> changePlayer = new HashMap<>();
    @EventHandler
    public void onPlayerSpectate(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getHand() != EquipmentSlot.HAND) {
            return;
        }
        if (player.getGameMode().equals(GameMode.SPECTATOR) && board.getEntryTeam(player.getName()) != null) {
            if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
                Team team = board.getEntryTeam(player.getName());
                if (changePlayer.get(player.getName()) == null) {
                    Deque<String> deque = new ArrayDeque<>(team.getEntries());
                    changePlayer.put(player.getName(), deque);
                } else {
                    Deque<String> deque = changePlayer.get(player.getName());
                    String teamName = deque.pop();
                    deque.addLast(teamName);
                    player.sendMessage(teamName);
                    changePlayer.put(player.getName(), deque);
                    player.setSpectatorTarget(Bukkit.getPlayer(teamName));
                }
            }
        }
    }
    @EventHandler
    public void onPlayerShifting(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode().equals(GameMode.SPECTATOR) && board.getEntryTeam(player.getName()) != null) {
            event.setCancelled(true);
        }
    }
}
