package battleroyale.battleroyale;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class TeamUnload {
    public static void unload() {
        Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
        unloadTeam(board, "Синие");
        unloadTeam(board, "Розовые");
    }
    public static void unloadTeam(Scoreboard board, String teamName) {
        if (board.getTeam(teamName) != null) {
            Team team = board.getTeam(teamName);
            for (String playerName : team.getEntries()) {
                team.removeEntry(playerName);
            }
            team.unregister();
        }
    }
}
