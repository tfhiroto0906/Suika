package MobWave;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import MobWave.Commands.MobWaveCommands;
import MobWave.Commands.coinCommand;
import MobWave.Commands.displayGUICommand;
import MobWave.Listener.MobWaveListener;

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
		getCommand("mob").setExecutor(new MobWaveCommands());
		getCommand("opengui").setExecutor(new displayGUICommand());
		getCommand("coin").setExecutor(new coinCommand());
	}

	public static MobWaveMain getPlugin() {
		return plugin;
	}
}