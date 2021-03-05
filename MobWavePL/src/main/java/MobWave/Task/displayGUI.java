package MobWave.Task;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class displayGUI implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)) return false;
		Player player = (Player)sender;

		//27SlotのGUI
		Inventory inv = Bukkit.createInventory(null, 27,ChatColor.RED + "MobWaveGUI");

		//Wave開始のCooKedBEEF
		ItemStack food = new ItemStack(Material.COOKED_BEEF);
		ItemMeta foodMeta = food.getItemMeta();
		foodMeta.setDisplayName(ChatColor.RED+"ウェーブを開始する");
		food.setItemMeta(foodMeta);

		//WaveStopの骨
		ItemStack food2 = new ItemStack(Material.BONE);
		ItemMeta foodMeta2 = food2.getItemMeta();
		foodMeta2.setDisplayName(ChatColor.DARK_GREEN+"ウェーブを終了する");
		food2.setItemMeta(foodMeta2);

		//アイテムの配置
		inv.setItem(11, food);
		inv.setItem(15, food2);
		player.openInventory(inv);
		return true;
	}
}
