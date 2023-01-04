package battleroyale.battleroyale.utils;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class UtilColor {
    public static String toColor(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
    public static String toColor(String message, Object[] args) {
        return args != null && args.length != 0 ? toColor(String.format(message, args)) : toColor(message);
    }
        public static List<String> toColor(List<String> lore) {
        List<String> newLore = new ArrayList<>();
        for (int i = 0; i < lore.size(); i++) {
            newLore.add(ChatColor.translateAlternateColorCodes('&', lore.get(i)));
        }
        return newLore;
    }
}
