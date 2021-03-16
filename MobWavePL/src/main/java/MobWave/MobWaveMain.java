package MobWave;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import MobWave.Commands.MobWaveCommands;
import MobWave.Commands.MobWaveGUICommand;
import MobWave.Commands.coinCommand;
import MobWave.Commands.villagerCommand;
import MobWave.Listener.MobWaveListener;
import MobWave.Listener.shopListener;

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
		Bukkit.getPluginManager().registerEvents(new shopListener(),this);

		//commands
		getCommand("mob").setExecutor(new MobWaveCommands());
		getCommand("opengui").setExecutor(new MobWaveGUICommand());
		getCommand("coin").setExecutor(new coinCommand());
		getCommand("villager").setExecutor(new villagerCommand());
	}

	public static MobWaveMain getPlugin() {
		return plugin;
	}
}