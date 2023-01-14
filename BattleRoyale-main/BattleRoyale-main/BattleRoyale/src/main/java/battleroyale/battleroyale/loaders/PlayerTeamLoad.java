package battleroyale.battleroyale.loaders;

import battleroyale.battleroyale.utils.UtilColor;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;

public class PlayerTeamLoad {
    public static List<String> teams = new ArrayList<>();
    public static void load() {
        Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
        createTeam(board, "Розовые", "&d");
        teams.add("Розовые");
        createTeam(board, "Синие", "&9");
        teams.add("Синие");
    }
    private static void createTeam(Scoreboard board, String teams, String color) {
        if (board.getTeam(teams) == null) {
            board.registerNewTeam(teams);
            Team team = board.getTeam(teams);
            team.setPrefix(UtilColor.toColor(color) +"");
        }
    }
}
