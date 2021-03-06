package MobWave.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import MobWave.MobWaveMain;
import MobWave.Task.MobWaveTask;

public class MobWaveCommands implements CommandExecutor {

	static BukkitTask task;
	static int hantei;
	public boolean onCommand(CommandSender sender,Command cmd,String label,String[] args) {

		Player player =null;

		if(sender instanceof Player) {
			player =(Player)sender;
		}

		else {
			sender.sendMessage("コンソールからは実行できまっせ――ん！！");
			return true;
		}


		//mobstart
		if(label.equalsIgnoreCase("mobstart")) {
			//既に実行しているかの判定
			if(hantei==0) {
				player.sendMessage("Commandでstartします");
				//SpawnMobを20delay3秒間隔で呼ぶ
				task = new MobWaveTask(player).runTaskTimer(MobWaveMain.getPlugin(),20,60); //SpawnMobを20delay3秒間隔で呼ぶ
				hantei=1;
			}
			else {
				player.sendMessage("既に実行しています。");
			}
		}

		//mobstop
		if(label.equalsIgnoreCase("mobstop")) {
			task.cancel();
			hantei=0;
			player.sendMessage("CommandでStopします");
		}
		return true;
	}
}
