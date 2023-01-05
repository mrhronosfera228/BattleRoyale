package battleroyale.battleroyale.events;

import battleroyale.battleroyale.loaders.InventoryLoad;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static battleroyale.battleroyale.GameLogic.Game.CreateItem;
import static battleroyale.battleroyale.GameLogic.Game.GameStart;

public class ItemClickChangeTeam implements Listener {
    static Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
    public static Map<String, Integer> PlayerSlot = new HashMap<>();
    public static Map<String, String> PlayerTeam = new HashMap<>();
    public static Map<String, String> PlayerZnak = new HashMap<>();
    Map<String, Integer> PlayerData  = new HashMap<>();
    @EventHandler
    public void onPlayerClicked(PlayerInteractEvent event) {
        if (event.getItem() != null) {
            if ((event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) && event.getItem().equals(CreateItem(Material.WOOL, "&6Выбор команды", "", 0))) {
                Player p = event.getPlayer();
                p.openInventory(InventoryLoad.getTeamChangeInventory());
            }
        }
    }
    @EventHandler
    public void onInventoryClicked(InventoryClickEvent event) {
        if(event.getInventory().equals(InventoryLoad.getTeamChangeInventory())) {
            if (event.getCurrentItem() != null) {
                ChangeTeam(event,  "Розовые", "&d", 6, 0);
                ChangeTeam(event, "Синие", "&9", 11, 1);
            }
        }
        if (!GameStart) {
            if (event.getInventory().equals(event.getView().getTopInventory())) {
                event.setCancelled(true);
            }
        }
    }
    //Реализация смены команд игроком
    public void ChangeTeam(InventoryClickEvent event, String teamName, String znak, int data, int slot) {
        if (event.getCurrentItem().equals(event.getInventory().getItem(slot))) {
            event.setCancelled(true);
            Team team = board.getTeam(teamName);
            team.addEntry(event.getWhoClicked().getName());
            List<String> lore = new ArrayList<>();
            Object[] lores = team.getEntries().toArray();
            lore.add("&fИгроков: " + team.getEntries().size() + "/2");
            for (int i = 0; i < lores.length; i++) {
                lore.add(znak + lores[i]);
            }
            ItemStack teams = CreateItem(Material.WOOL, znak + teamName, lore, data);
            event.getInventory().setItem(slot, teams);
            Player player = (Player) event.getWhoClicked();
            if (PlayerSlot.get(player.getName()) != null && PlayerZnak.get(player.getName()) != null
                    && PlayerTeam.get(player.getName()) != null && PlayerData.get(player.getName()) != null) {
                int anotherSlot = PlayerSlot.get(player.getName());
                String anotherTeam = PlayerTeam.get(player.getName());
                String anotherZnak = PlayerZnak.get(player.getName());
                int anotherData = PlayerData.get(player.getName());
                UpdateItemChange(event, anotherTeam, anotherZnak, anotherData, anotherSlot);
            }
            PlayerSlot.put(player.getName(), slot);
            PlayerZnak.put(player.getName(), znak);
            PlayerTeam.put(player.getName(), teamName);
            PlayerData.put(player.getName(), data);
        }
    }
    //Подпись ника игрока под блоком шерсти
    public static List<String> loreUpdate(String teamName, String znak) {
        Team team = board.getTeam(teamName);
        List<String> lore = new ArrayList<>();
        Object[] lores = team.getEntries().toArray();
        lore.add("&fИгроков: " + team.getEntries().size() + "/2");
        for (int i = 0; i < lores.length; i++) {
            lore.add(znak + lores[i]);
        }
        return lore;
    }

    public void UpdateItemChange(InventoryClickEvent event,  String teamName, String znak, int data, int slot) {
        ItemStack teams = CreateItem(Material.WOOL, znak + teamName, loreUpdate(teamName, znak), data);
        event.getInventory().setItem(slot, teams);
    }
}
