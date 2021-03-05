package MobWave;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import MobWave.Commands.MobWaveCommands;
import MobWave.Listener.MobWaveListener;
import MobWave.Task.displayGUI;

public class MobWaveMain extends JavaPlugin{

	private static MobWaveMain plugin;

	@Override
	public void onDisable() {
		super.onDisable();
	}

	@Override
	public void onEnable() {
		super.onEnable();
		plugin = this;
		//events
		Bukkit.getPluginManager().registerEvents(new MobWaveListener(),this);


		//commands
		getCommand("mobstart").setExecutor(new MobWaveCommands());
		getCommand("mobstop").setExecutor(new MobWaveCommands());
		getCommand("opengui").setExecutor(new displayGUI());
	}

	public static MobWaveMain getPlugin() {
		return plugin;
	}
}