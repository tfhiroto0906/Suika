package MobWave;

import org.bukkit.plugin.java.JavaPlugin;

import MobWave.Commands.MobWaveCommands;

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
		getCommand("mobstart").setExecutor(new MobWaveCommands());
		getCommand("mobstop").setExecutor(new MobWaveCommands());
	}

	public static MobWaveMain getPlugin() {
		return plugin;
	}
}