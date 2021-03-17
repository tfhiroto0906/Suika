package conSQL.task;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import conSQL.SQLMain;
import net.md_5.bungee.api.ChatColor;

public class coinTask {
	static SQLMain plugin = SQLMain.getPlugin(SQLMain.class);
	static FileConfiguration config = plugin.getConfig();
	String[] option = new String[3];
	Player player;

	public static int getCoin(Player pl) {
		UUID uuid = pl.getUniqueId();
		PreparedStatement statement;
		try {
			statement = plugin.getConnection()
					.prepareStatement("SELECT COINS FROM " + config.getString("database.table")+ " WHERE UUID=?");
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
	public static boolean updateCoins(int setcoins,Player pl,boolean b) {
		UUID uuid = pl.getUniqueId();
		int incoin=setcoins;
		//加算処理の時
		if(b) {
			setcoins +=getCoin(pl);
			if(setcoins<0) {
				pl.sendMessage(ChatColor.DARK_RED + "coinが足りません");
				return false;
			}
			pl.sendMessage(ChatColor.YELLOW + "" + incoin +"Coin");
		}
		try {
			PreparedStatement statement = plugin.getConnection()
					.prepareStatement("UPDATE " + config.getString("database.table")  + " SET COINS=? WHERE UUID=?");
			statement.setInt(1,setcoins);
			statement.setString(2,uuid.toString());
			statement.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
}

