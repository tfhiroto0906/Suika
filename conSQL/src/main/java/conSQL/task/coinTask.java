package conSQL.task;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.entity.Player;

import conSQL.SQLMain;

public class coinTask {
	static SQLMain plugin = SQLMain.getPlugin(SQLMain.class);
	String[] option = new String[3];
	Player player;
	public static int getCoin(Player pl) {
		UUID uuid = pl.getUniqueId();
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
	public static void updateCoins(int setcoins,Player pl,boolean b) {
		UUID uuid = pl.getUniqueId();
		//加算処理の時
		if(b) {
			setcoins +=getCoin(pl);
		}
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

