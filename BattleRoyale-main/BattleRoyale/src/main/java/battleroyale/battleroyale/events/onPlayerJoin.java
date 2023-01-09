package battleroyale.battleroyale.events;

import battleroyale.battleroyale.player.RoyalPlayer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static battleroyale.battleroyale.GameLogic.Game.*;

public class onPlayerJoin implements Listener {
    public onPlayerJoin(){

    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.getInventory().clear();
        player.setFoodLevel(20);
        if (InLobby) {
            if (Bukkit.getServer().getOnlinePlayers().size() >= 2) {
                StartGame();
            }
        }
        //Start
        if (!GameStart) {
            player.setGameMode(GameMode.ADVENTURE);
            player.getInventory().addItem(CreateItem(Material.WOOL, "&6Выбор команды", "", 0));
            player.teleport(new Location(Bukkit.getWorld("battle"), 48, 18, 79));
            player.setHealthScale(20);
            AttributeInstance instance = event.getPlayer().getAttribute(Attribute.GENERIC_ATTACK_SPEED);
            if (instance != null) {
                instance.setBaseValue(16);
            }
            RoyalPlayer rp = RoyalPlayer.getPlayer(player.getName());
            rp.setMaxHealth(20);
            rp.setRegeneration(1);
            rp.setHealth(20);
            rp.setMagicArmor(0);
            rp.setArmor(0);
        }
    }
}
