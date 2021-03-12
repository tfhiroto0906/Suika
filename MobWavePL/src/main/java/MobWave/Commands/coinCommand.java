package MobWave.Commands;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import conSQL.SQLMain;
import net.md_5.bungee.api.ChatColor;

public class coinCommand implements CommandExecutor {
	SQLMain plugin = SQLMain.getPlugin(SQLMain.class);
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
			int coins = getCoin();
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
				updateCoins(value,player);
				player.sendMessage(ChatColor.YELLOW +"Coin set to " + option[1] );
			}

			//optionがsetでほかのプレイヤーのcoinをsetする時
			catch(NumberFormatException e) {
				try {
					Player pl = Bukkit.getServer().getPlayer(option[1]);
					int value = Integer.valueOf(option[2]);
					updateCoins(value,pl);
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

	//データベースから対象のcoinを取得
	public int getCoin() {
		UUID uuid = player.getUniqueId();
		PreparedStatement statement;
		try {
			statement = plugin.getConnection()
					.prepareStatement("SELECT COINS FROM " + plugin.table  + " WHERE UUID=?");
			statement.setString(1,uuid.toString());
			ResultSet results = statement.executeQuery();
			results.next();
			int coins = results.getInt(1);
			return coins;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return -99999999;
	}

	//playerのCOINSを指定された値にupdate
	public void updateCoins(int setcoins,Player pl) {
		UUID uuid = pl.getUniqueId();
		try {
			PreparedStatement statement = plugin.getConnection()
					.prepareStatement("UPDATE " + plugin.table  + " SET COINS=? WHERE UUID=?");
			statement.setInt(1,setcoins);
			statement.setString(2,uuid.toString());
			statement.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

