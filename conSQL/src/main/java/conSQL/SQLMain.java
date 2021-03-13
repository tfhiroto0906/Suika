package conSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import conSQL.task.loadPlayerData;
import net.md_5.bungee.api.ChatColor;



public class SQLMain extends JavaPlugin implements Listener{
	private Connection connection;
	public String host,database,username,password,table;
	FileConfiguration config=getConfig();
	public int  port;

	@Override
	public void onEnable() {
		this.getConfig().options().copyDefaults(true);

		mysqlSetup();

		getConfig().options().copyDefaults(true);
		saveDefaultConfig();

		this.getServer().getPluginManager().registerEvents(new loadPlayerData(), this);
	}


	//コンフィグからロード
	public void mysqlSetup(){
		try {
			synchronized(this) {
				if(getConnection() != null && !getConnection().isClosed()) {
					return;
				}
				Class.forName("com.mysql.jdbc.Driver");

				setConnection(DriverManager.getConnection("jdbc:mysql://" + config.getString("database.host") + ":"+ config.getString("database.port") + "/"
						+ config.getString("database.database"),config.getString("database.username"),config.getString("database.password")));

				Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN+"MYSQL CONNECTED");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}
