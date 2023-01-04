package battleroyale.battleroyale.commands;

import battleroyale.battleroyale.BattleRoyale;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;

public abstract class AbstractCommand implements CommandExecutor {
    public AbstractCommand(String command) {
        PluginCommand pluginCommand = BattleRoyale.getInstance().getCommand(command);
        pluginCommand.setExecutor(this);
    }

    public abstract void execute(CommandSender commandSender, String label, String[] args);

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        this.execute(sender, label, args);
        return true;
    }
}
