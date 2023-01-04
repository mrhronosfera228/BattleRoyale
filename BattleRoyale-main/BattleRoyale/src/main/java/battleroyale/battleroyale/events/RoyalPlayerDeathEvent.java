package battleroyale.battleroyale.events;

import battleroyale.battleroyale.BattleRoyale;
import battleroyale.battleroyale.GameLogic.Game;
import battleroyale.battleroyale.PlayerTeamLoad;
import battleroyale.battleroyale.player.RoyalDamageable;
import battleroyale.battleroyale.player.RoyalPlayer;
import battleroyale.battleroyale.utils.UtilColor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.Map;

import static battleroyale.battleroyale.GameLogic.Game.CreateItem;
import static battleroyale.battleroyale.GameLogic.Game.StopGame;

public class RoyalPlayerDeathEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final RoyalPlayer player;
    private final RoyalDamageable killer;
    public  static Map<ArmorStand, Inventory> armorStandInventoryMap = new HashMap<>();
    public static Map<String, ItemStack> PlayerHead = new HashMap<>();
    Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();

    public RoyalPlayerDeathEvent(RoyalPlayer player, RoyalDamageable killer) {
        this.player = player;
        this.killer = killer;
        Death();
    }
    public void Death() {
        Player player1 = player.getPlayer();
        player1.setGameMode(GameMode.SPECTATOR);
        ArmorStand armorStand = player1.getWorld().spawn(player1.getLocation(), ArmorStand.class);
        armorStand.setVisible(false);
        armorStand.setSmall(true);
        armorStand.setCustomName(UtilColor.toColor("&eНажмите пкм, чтобы увидеть лут"));
        armorStand.setCustomNameVisible(true);
        ItemStack item = PlayerHead.get(player1.getName());
        armorStand.setHelmet(item);
        Bukkit.getScheduler().runTaskLater(BattleRoyale.getInstance(), () -> {
            armorStand.remove();
            armorStandInventoryMap.remove(armorStand);
        }, 400);
        Inventory inventory = Bukkit.createInventory(null, 54, "noob");
        inventory.setItem(0, CreateItem(Material.GOLD_INGOT, "&6Деньги", "&a" + Game.PlayerBalance.get(player1.getName()), 0));
        for (int i = 0; i < player1.getInventory().getSize(); i++) {
            AddItem(player1.getInventory().getItem(i), inventory);
        }
        armorStandInventoryMap.put(armorStand, inventory);
        player.setArmor(0);
        player.setMaxHealth(20);
        player.setHealth(20);
        player.setMagicArmor(0);
        player.setRegeneration(1);
        Game.PlayerBalance.put(player1.getName(), 0);
        player1.getInventory().clear();
        player1.setFoodLevel(20);
        Team team = board.getEntryTeam(player1.getName());
        for (String playerName : team.getEntries()) {
            Player teamPlayer = Bukkit.getPlayer(playerName);
            if (!teamPlayer.getName().equals(player1.getName()) && RoyalPlayer.isAlive(teamPlayer)) {
                player1.setSpectatorTarget(teamPlayer);
            }
        }
        int countAlive = 0;
        for (String playerName : team.getEntries()) {
            Player teamPlayer = Bukkit.getPlayer(playerName);
            if (!teamPlayer.getName().equals(player1.getName())) {
                if (RoyalPlayer.isAlive(teamPlayer)) {
                    BattleRoyale.sendMessage(teamPlayer, "&aВаш союзник умер, но вы можете выкупить его в магазине.");
                    countAlive++;
                }
                Game.ShopInventory.get(teamPlayer.getName()).addItem(CreateItem(PlayerHead.get(player1.getName()), "&c" + player1.getName(), "Вы можете купить вашего союзника за &6{sum}", 3));
            }
        }
        if (countAlive == 0) {
            PlayerTeamLoad.teams.remove(team.getName());
            if (PlayerTeamLoad.teams.size() == 1) {
                StopGame();
            }
        }
    }
    public void AddItem(ItemStack itemStack, Inventory inventory) {
        if(itemStack != null) {
            inventory.addItem(itemStack);
        }
    }
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public RoyalPlayer getPlayer() {
        return player;
    }

    public RoyalDamageable getKiller() {
        return killer;
    }

}
