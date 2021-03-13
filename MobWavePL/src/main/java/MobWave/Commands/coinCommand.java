package MobWave.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import conSQL.SQLMain;
import conSQL.task.coinTask;
import net.md_5.bungee.api.ChatColor;

public class coinCommand implements CommandExecutor {
	static SQLMain plugin = SQLMain.getPlugin(SQLMain.class);
	String[] option = new String[3];
	Player player;

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			player = (Player)sender;
			coinCommands(player,args);
			return true;
		}
		return false;
	}

	public void coinCommands(Player player,String[] args) {
		option=args;
		//optionがなければ所持コインを表示
		if(option.length==0) {
			int coins = coinTask.getCoin(player);
			if(coins!=-99999999) {
				player.sendMessage(ChatColor.YELLOW + "You have " + coins +"coins");
			}
			return;
		}

		//optionがsetの時
		if (option[0].equalsIgnoreCase("set")) {
			//プレイヤー指定がない時自分のcoinをset
			try {
				int value = Integer.valueOf(option[1]);
				coinTask.updateCoins(value,player,false);
				player.sendMessage(ChatColor.YELLOW +"Coin set to " + option[1] );
			}

			//optionがsetでほかのプレイヤーのcoinをsetする時
			catch(NumberFormatException e) {
				try {
					Player pl = Bukkit.getServer().getPlayer(option[1]);
					int value = Integer.valueOf(option[2]);
					coinTask.updateCoins(value,pl,false);
					pl.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + pl
							.getName() + "'s Coin set to " + value);
				}

				//指定されたプレイヤーIDのプレイヤーデータがない時
				catch(NullPointerException eve) {
					player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "指定されたIDのプレイヤーデータは存在しません");
				}

			//coin set の後に引数の入力がない場合
			}catch(ArrayIndexOutOfBoundsException ev) {
				player.sendMessage("/coin set <金額>\n自分のcoinをset \n \n/coin set <PlayerID> <金額>\n特定のプレイヤーのcoinをset");
			}
		}
	}
}

