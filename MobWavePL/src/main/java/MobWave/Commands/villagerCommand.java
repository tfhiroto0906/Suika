package MobWave.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import MobWave.Task.villagerTask;

public class villagerCommand implements CommandExecutor{
	Player player;
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			player = (Player)sender;
			try {
				if(args[0].equalsIgnoreCase("spawn")) {
					//spawn shopが指定されたとき
					if(args[1].equalsIgnoreCase("shop")) {
						villagerTask.spawnShop(player);
						return true;
					}
					//spawn waveが指定されたとき
					else if(args[1].equalsIgnoreCase("wave")){
						villagerTask.spawnWave(player);
						return true;
					}
				}
			}catch(ArrayIndexOutOfBoundsException e) {
				player.sendMessage("/villager spawn <wave,shop>");
			}

			return true;
		}
		return false;
	}
}

