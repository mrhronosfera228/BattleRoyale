package battleroyale.battleroyale;

import battleroyale.battleroyale.player.Squad;

public class SquadsLoad {
    public SquadsLoad() {

    }
    public static void load() {
        Squad.CreateSquad("Розовые");
        Squad.CreateSquad("Синие");
    }
}
