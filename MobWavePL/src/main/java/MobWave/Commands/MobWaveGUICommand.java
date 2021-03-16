package MobWave.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import MobWave.Task.MobWaveGUITask;
import MobWave.Task.shopGUITask;

public class MobWaveGUICommand implements CommandExecutor{
	static Player player;

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			player = (Player)sender;
			try {
				//commandでwaveが指定されたとき
				if(args[0].equalsIgnoreCase("wave")) {
					MobWaveGUITask.firstGUI(player);
					return true;
				}
				//commandでshopが指定されたとき
				else if(args[0].equalsIgnoreCase("shop")) {
					shopGUITask.shopGUI(player);
					return true;
				}
			}catch(ArrayIndexOutOfBoundsException e) {
				player.sendMessage("引数:wave,shopを指定してください");
			}
		}
		return false;
	}
}



