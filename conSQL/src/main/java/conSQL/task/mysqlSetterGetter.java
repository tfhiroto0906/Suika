package conSQL.task;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import conSQL.SQLMain;
import net.md_5.bungee.api.ChatColor;

public class mysqlSetterGetter implements Listener {

	SQLMain plugin = SQLMain.getPlugin(SQLMain.class);

	@EventHandler
	//playerID の取得
	public void onJoin(PlayerJoinEvent e) {
		Player player=e.getPlayer();
		createPlayer(player.getUniqueId(),player);
	}

	public boolean playerExists(UUID uuid) {
		try {
			//SQL文を生成
			PreparedStatement statement = plugin.getConnection()
					.prepareStatement("SELECT * FROM " + plugin.table  + " WHERE UUID=?");
			statement.setString(1,uuid.toString());
			//SQL文をセット
			ResultSet results = statement.executeQuery();
			//UUIDが一致するものがあれば
			if(results.next()) {
				plugin.getServer().broadcastMessage(ChatColor.GREEN + "プレイヤーデータをロードしました");
				return true;
			}
			plugin.getServer().broadcastMessage(ChatColor.RED + "プレイヤーデータが見つかりませんでした");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void createPlayer(final UUID uuid,Player player) {
		try {
			PreparedStatement statement = plugin.getConnection()
					.prepareStatement("SELECT * FROM " + plugin.table  + " WHERE UUID=?");
			statement.setString(1,uuid.toString());
			ResultSet results = statement.executeQuery();
			results.next();
			//プレイヤーデータがなければ
			if((playerExists(uuid)) != true) {
				//INSERT文の生成
				PreparedStatement insert = plugin.getConnection()
						.prepareStatement("INSERT INTO "+ plugin.table + ("(UUID,NAME,COINS) VALUE (?,?,?)") );
				insert.setString(1, uuid.toString());
				insert.setString(2, player.getName());
				//COIN初期値500
				insert.setInt(3, 500);
				insert.executeUpdate();

				plugin.getServer().broadcastMessage(ChatColor.GREEN+ "プレイヤーデータを取得しました");
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}


}
