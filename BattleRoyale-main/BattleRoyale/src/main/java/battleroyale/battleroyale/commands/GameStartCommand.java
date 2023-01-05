package battleroyale.battleroyale.commands;

import battleroyale.battleroyale.BattleRoyale;
import battleroyale.battleroyale.GameLogic.Game;
import org.bukkit.command.CommandSender;

//Команды для начала игры
public class GameStartCommand extends  AbstractCommand{
    public GameStartCommand() {
        super("br");
    }

    @Override
    public void execute(CommandSender commandSender, String label, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("start")) {
            if (Game.InLobby) {
                BattleRoyale.sendMessage(commandSender, "&aИгра успешно запущена");
                Game.StartGame();
            } else {
                BattleRoyale.sendMessage(commandSender, "&cИгра уже запущена");
            }
        }
        if (args.length == 1 && args[0].equalsIgnoreCase("stop")) {
            if (Game.GameStart) {
                BattleRoyale.sendMessage(commandSender, "&aИгра успешно закончена");
                Game.StopGame();
            } else {
                BattleRoyale.sendMessage(commandSender, "&cИгра еще не началась или уже закончена");
            }
        }
        if (args.length == 1 && args[0].equalsIgnoreCase("bal")) {
            if (Game.GameStart) {
                BattleRoyale.sendMessage(commandSender, "&aВаш баланс $" + Game.PlayerBalance.get(commandSender.getName()));
            }
        }
    }
}
