package battleroyale.battleroyale.player;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Squad {
    private static List<String> Squads = new ArrayList<>();
    private static Map<Player, String> squad = new HashMap<>();
    private RoyalPlayer rp;
    public Squad(RoyalPlayer rp) {
        this.rp = rp;
    }

    public static Map<Player, String> getSquad() {
        return squad;
    }

    public static List<String> getSquads() {
        return Squads;
    }

    public static boolean hasSquad(Player rp) {
        return getSquad(rp) != null;
    }
    public static void addToSquad(Player rp, String name) {
        squad.put(rp, name);
    }
    public static String getSquad(Player rp) {
        return getSquad().get(rp);
    }
    public static void CreateSquad(String name) {
        getSquads().add(name);
    }
}
