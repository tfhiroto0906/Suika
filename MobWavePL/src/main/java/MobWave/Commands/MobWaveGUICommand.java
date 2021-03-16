package MobWave.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import MobWave.Task.MobWaveGUITask;
import MobWave.Task.shopTask;

public class MobWaveGUICommand implements CommandExecutor{
	static Player player;

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			player = (Player)sender;
			try {
				if(args[0].equalsIgnoreCase("wave")) {
					MobWaveGUITask.firstGUI(player);
					return true;
				}
				else if(args[0].equalsIgnoreCase("shop")) {
					shopTask.shopGUI(player);
					return true;
				}
			}catch(ArrayIndexOutOfBoundsException e) {
				player.sendMessage("引数:wave,shopを指定してください");
			}
		}
		return false;
	}
}



