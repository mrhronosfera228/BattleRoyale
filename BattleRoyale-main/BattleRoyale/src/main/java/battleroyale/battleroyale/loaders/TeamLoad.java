package battleroyale.battleroyale.loaders;

import battleroyale.battleroyale.utils.UtilColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class TeamLoad {
    public TeamLoad() {

    }
    public static void load() {
        Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
        createTeam(board,"COMMON", "&f", 'f');
        createTeam(board,"UNCOMMON", "&2", '2');
        createTeam(board,"RARE", "&9", '9');
        createTeam(board,"EPIC", "&5", '5');
        createTeam(board,"LEGENDARY", "&6", '6');
        createTeam(board,"MIFIC", "&c", 'c');
        createTeam(board,"ARTIFACT", "&4", '4');
    }
    public static void createTeam(Scoreboard board, String teams, String color, char colors) {
        if (board.getTeam(teams) == null) {
            board.registerNewTeam(teams);
            Team team = board.getTeam(teams);
            team.setPrefix(UtilColor.toColor(color) +"");
            team.setColor(ChatColor.getByChar(colors));
        }
    }
}
