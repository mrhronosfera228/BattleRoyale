package battleroyale.battleroyale.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UtilChat {
    public static void ps(String Server, Player player, String msg, Object[] objects) {
        player.sendMessage(String.format(ChatColor.translateAlternateColorCodes('&', "&8&l[" + Server + "&8&l] " + msg), objects));
    }
    public static void ps(String Server, CommandSender player, String msg, Object[] objects) {
        player.sendMessage(String.format(ChatColor.translateAlternateColorCodes('&', "&8&l[" + Server + "&8&l] " + msg), objects));
    }
}
