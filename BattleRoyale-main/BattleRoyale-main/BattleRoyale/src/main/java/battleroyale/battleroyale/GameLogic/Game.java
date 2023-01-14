package battleroyale.battleroyale.GameLogic;

import battleroyale.battleroyale.BattleRoyale;
import battleroyale.battleroyale.loaders.InventoryLoad;
import battleroyale.battleroyale.loaders.PlayerTeamLoad;
import battleroyale.battleroyale.loaders.TeamUnload;
import battleroyale.battleroyale.events.ItemClickChangeTeam;
import battleroyale.battleroyale.events.RoyalPlayerDeathEvent;
import battleroyale.battleroyale.player.RoyalPlayer;
import battleroyale.battleroyale.player.RoyaleAlivePlayers;
import battleroyale.battleroyale.utils.UtilColor;
import org.apache.commons.io.FileUtils;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static battleroyale.battleroyale.loaders.PlaneLoad.getPlaneStart;

public class Game {
    public static boolean GameStart = false;
    private static  Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
    public static boolean GameEnd = false;
    public static boolean InLobby = true;
    public static Map<String, Integer> PlayerBalance = new HashMap<>();
    public static Map<String, Inventory> ShopInventory = new HashMap<>();
    private static Zone GameZone;
    public static void StartGame() {
        InLobby = false;
        Bukkit.getServer().setWhitelist(true);
        //Запуск через 30 секунд
        Bukkit.getScheduler().runTaskLater(BattleRoyale.getInstance(), () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                ShopInventory.put(player.getName(), Bukkit.createInventory(player, 54, "Магазин"));
                Inventory inventory = ShopInventory.get(player.getName());
                //SetItems(inventory);
                player.getInventory().clear();
                player.closeInventory();
                getPlaneStart().addPassenger(player);
                PlayerBalance.put(player.getName(), 0);
                RoyalPlayerDeathEvent.PlayerHead.put(player.getName(), createHead(player.getName()));
                ItemStack itemStack = CreateItem(Material.GOLD_INGOT, "&6Деньги", "&a" + 100, 0);
                player.getInventory().addItem(itemStack);
                if (board.getEntryTeam(player.getName()) == null) {
                    switch (1) {
                        case 1:
                            if (board.getTeam("Розовые").getEntries().size() < 2) {
                                board.getTeam("Розовые").addEntry(player.getName());
                                break;
                            }
                        case 2:
                            if (board.getTeam("Синие").getEntries().size() < 2) {
                                board.getTeam("Синие").addEntry(player.getName());
                                break;
                            }
                    }
                }
            }
            GameStart = true;
            Bukkit.getServer().setWhitelist(false);
            World w = Bukkit.getWorld("battle");
            //Генерация зоны
            GameZone = new Zone(BattleRoyale.getInstance());
            if (RoyaleAlivePlayers.AlivePlayerCounter() < 20) {
                GameZone.StartZone(w);
            }
            else {
                GameZone.StartTeamsZone(w);
            }
            Bukkit.getScheduler().runTaskTimer(BattleRoyale.getInstance(), () -> {
                if(!GameEnd) {
                    GameZone.SecProcessor();
                }
            }, 0 , 20);
        }, 600);
    }
    //Конец игры
    public static void StopGame() {
        Team team = board.getTeam(PlayerTeamLoad.teams.get(0));
        for (String playerName : team.getEntries()) {
            Player player = Bukkit.getPlayer(playerName);
            player.sendTitle(UtilColor.toColor("&6&lVICTORY"), "", 20, 20, 100);
        }
        GameEnd = true;
        Bukkit.getScheduler().runTaskLater(BattleRoyale.getInstance(), () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.kickPlayer(UtilColor.toColor("&cСервер перезагружается"));
            }
            TeamUnload.unload();
            Bukkit.getServer().setWhitelist(true);
            deleteWorld("battle");
            Bukkit.getServer().reload();
                }, 200);
    }
    //Перегрузки метода CreateItem, который реализует создание предмета
    public static ItemStack CreateItem(Material material, String name, String lore, int data) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(UtilColor.toColor(name));
        meta.setLore(Collections.singletonList(UtilColor.toColor(lore)));
        itemStack.setDurability((short) data);
        itemStack.setItemMeta(meta);
        return  itemStack;
    }
    public static ItemStack CreateItem(ItemStack itemStack, String name, String lore, int data) {
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
    //Копирование мира
    public static void copyWorld(final String oldDirectory, final String name) {
        try {
            final File dest = new File("./");
            final File source = new File("./" + oldDirectory + "/");
            FileUtils.copyDirectory(source, dest);
            Bukkit.getServer().createWorld(new WorldCreator(name));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Удаление старого мира
    public static void deleteWorld(final String world) {
        BattleRoyale.getInstance().getServer().unloadWorld(world, true);
        final File dir = new File("./" + world);
        try {
            FileUtils.deleteDirectory(dir);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static ItemStack createHead(String name) {
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        meta.setOwningPlayer(Bukkit.getPlayer(name));
        head.setItemMeta(meta);

        return head;
    }
    //Генерация кастомных предметов в магазине
    /*public static void SetItems(Inventory inventory) {
        inventory.setItem(20, qEpic.get(0));
        inventory.setItem(21, qEpic.get(1));
        inventory.setItem(22, qEpic.get(2));
        inventory.setItem(23, qEpic.get(3));
    }*/

}
