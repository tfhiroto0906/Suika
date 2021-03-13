package conSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import conSQL.task.loadPlayerData;
import net.md_5.bungee.api.ChatColor;



public class SQLMain extends JavaPlugin implements Listener{
	private Connection connection;
	public String host,database,username,password,table;
	public int  port;

	@Override
	public void onEnable() {
		loadConfig();
		mysqlSetup();

		this.getServer().getPluginManager().registerEvents(new loadPlayerData(), this);
	}

	//コンフィグをロード
	public void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
	}

	//コンフィグからロード
	public void mysqlSetup(){
		host = getConfig().getString("host");
		port=getConfig().getInt("port");
		database=getConfig().getString("database");
		username=getConfig().getString("username");
		password=getConfig().getString("password");
		table=getConfig().getString("table");
		getLogger().info(table);
		try {

			synchronized(this) {
				if(getConnection() != null && !getConnection().isClosed()) {
					return;
				}
				Class.forName("com.mysql.jdbc.Driver");

				setConnection(DriverManager.getConnection("jdbc:mysql://" + host + ":"+ port + "/"
						+ database,username,password));

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
