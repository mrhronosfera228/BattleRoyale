package battleroyale.battleroyale.player;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Random;

public class RoyaleAlivePlayers {
    public static HashMap<Integer, Player> players = new HashMap();

    public static Player AlivePlayer() {

        for(Player player : Bukkit.getServer().getOnlinePlayers())
        {
            if(RoyalPlayer.getPlayer(player.getName()).getHealth() > 0 && player.getGameMode() == GameMode.ADVENTURE) {
                return player;
            }
        }
        return null;
    }

    public static int AlivePlayerCounter() {
        int counter = 0;
        for(Player player : Bukkit.getServer().getOnlinePlayers())
        {
            if(RoyalPlayer.getPlayer(player.getName()).getHealth() > 0 && player.getGameMode() == GameMode.ADVENTURE) {
                counter++;
            }
        }
        return counter;
    }

    public static Player GetRandomPlayer() {
        for (int i = 0; i < Bukkit.getServer().getOnlinePlayers().size(); i++) {
            players.put(i, RoyaleAlivePlayers.AlivePlayer());
        }
        int size = players.size();
        int randomIndex = new Random().nextInt(size);
        return players.get(randomIndex);
    }
}
