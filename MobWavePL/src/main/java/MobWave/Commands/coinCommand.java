package MobWave.Commands;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

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
		UUID uuid = player.getUniqueId();
		option=args;
		try {
			//optionがなければ
			if(option.length==0) {
				PreparedStatement statement = plugin.getConnection()
						.prepareStatement("SELECT COINS FROM " + plugin.table  + " WHERE UUID=?");
				statement.setString(1,uuid.toString());
				ResultSet results = statement.executeQuery();
				results.next();
				player.sendMessage(ChatColor.YELLOW +"You have "+ results.getString(1) +"coins");
				return;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

