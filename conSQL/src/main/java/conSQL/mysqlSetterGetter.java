package conSQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.md_5.bungee.api.ChatColor;

public class mysqlSetterGetter implements Listener {

	SQLMain plugin = SQLMain.getPlugin(SQLMain.class);

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player=e.getPlayer();
		createPlayer(player.getUniqueId(),player);
	}

	public boolean playerExists(UUID uuid) {
		try {
			PreparedStatement statement = plugin.getConnection()
					.prepareStatement("SELECT * FROM " + plugin.table  + " WHERE UUID=?");
			statement.setString(1,uuid.toString());

			ResultSet results = statement.executeQuery();
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
			if((playerExists(uuid)) != true) {
				PreparedStatement insert = plugin.getConnection()
						.prepareStatement("INSERT INTO "+ plugin.table + ("(UUID,NAME,COINS) VALUE (?,?,?)") );
				insert.setString(1, uuid.toString());
				insert.setString(2, player.getName());
				insert.setInt(3, 500);
				insert.executeUpdate();

				plugin.getServer().broadcastMessage(ChatColor.GREEN+ "プレイヤーデータを取得しました");
			}
		}
		catch(SQLException e ) {
			e.printStackTrace();
		}
	}


}
