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
	static Player player;
	//27SlotのGUI
	static Inventory inv = Bukkit.createInventory(null, 27,ChatColor.RED + "MobWaveGUI");
	static ItemStack[] itemIDs = itemIDs();
	static ItemMeta[] itemMetas = itemMetas();

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)) return false;
		player = (Player)sender;
		inv=null;
		inv = Bukkit.createInventory(null, 27,ChatColor.RED + "MobWaveGUI");

		itemMetas[0].setDisplayName(ChatColor.RED+"ウェーブを開始する");
		itemMetas[1].setDisplayName(ChatColor.DARK_GREEN+"ウェーブを終了する");
		for(int i=0;i<2;i++) {
			itemIDs[i].setItemMeta(itemMetas[i]);
		}

		//アイテムの配置
		inv.setItem(11, itemIDs[0]);
		inv.setItem(15, itemIDs[1]);
		player.openInventory(inv);
		return true;
	}

	public static void difficultyGUI(Player pl) {
		player = pl;
		//27SlotのGUI
		inv=null;
		inv = Bukkit.createInventory(null, 27,ChatColor.RED + "DifficultyGUI");
		itemMetas[2].setDisplayName(ChatColor.RED+"VeryHard");
		itemMetas[3].setDisplayName(ChatColor.RED+"Hard");
		itemMetas[4].setDisplayName(ChatColor.RED+"Normal");
		itemMetas[5].setDisplayName(ChatColor.RED+"Easy");

		for(int i=2;i<6;i++) {
			itemIDs[i].setItemMeta(itemMetas[i]);
		}

		//アイテムの配置
		inv.setItem(16, itemIDs[5]);
		inv.setItem(14, itemIDs[4]);
		inv.setItem(12, itemIDs[3]);
		inv.setItem(10, itemIDs[2]);
		player.openInventory(inv);
		return;
	}


	public static Inventory invID () {
		Inventory invID = inv;
		return invID;
	}

	public static ItemStack[] itemIDs() {
		ItemStack[] itemIDs = new ItemStack[]{
			new ItemStack(Material.COOKED_BEEF),
			new ItemStack(Material.BONE),
			new ItemStack(Material.WOOD_SWORD),
			new ItemStack(Material.STONE_SWORD),
			new ItemStack(Material.IRON_SWORD),
			new ItemStack(Material.DIAMOND_SWORD),
			};
		return itemIDs;
	}

	public static ItemMeta[] itemMetas() {
		ItemMeta[] ItemMetas = new ItemMeta[itemIDs.length];
		for(int i=0;i<itemIDs.length;i++) {
			ItemMetas[i] = itemIDs[i].getItemMeta();
		}
		return ItemMetas;
	}
}


