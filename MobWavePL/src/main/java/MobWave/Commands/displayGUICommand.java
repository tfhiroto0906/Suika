package MobWave.Commands;

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

public class displayGUICommand implements CommandExecutor{
	static Player player;
	//27SlotのGUI
	static Inventory inv = Bukkit.createInventory(null, 27,ChatColor.RED + "MobWaveGUI");
	static ItemStack[] itemIDs = itemIDs();
	static ItemMeta[] itemMetas = itemMetas();

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			firstGUI((Player)sender);
			return true;
		}
		return false;
	}
	//最初のGUI
	public static void firstGUI(Player pl) {
		player = pl;
		inv=null;
		inv = Bukkit.createInventory(null, 27,ChatColor.RED + "MobWaveGUI");

		itemMetas[0].setDisplayName(ChatColor.RED+"ウェーブの難易度を選択する");
		itemMetas[1].setDisplayName(ChatColor.DARK_GREEN+"ウェーブを終了する");
		for(int i=0;i<2;i++) {
			itemIDs[i].setItemMeta(itemMetas[i]);
		}

		//アイテムの配置
		inv.setItem(11, itemIDs[0]);
		inv.setItem(15, itemIDs[1]);
		player.openInventory(inv);
	}

	//invIDを返す
	public static Inventory invID () {
		Inventory invID = inv;
		return invID;
	}

	public static void difficultyGUI(Player pl) {
		player = pl;
		//27SlotのGUI
		inv=null;
		inv = Bukkit.createInventory(null, 27,ChatColor.RED + "DifficultyGUI");
		itemMetas[6].setDisplayName("back");
		itemMetas[5].setDisplayName(ChatColor.RED+"VeryHard");
		itemMetas[4].setDisplayName(ChatColor.RED+"Hard");
		itemMetas[3].setDisplayName(ChatColor.RED+"Normal");
		itemMetas[2].setDisplayName(ChatColor.RED+"Easy");

		for(int i=2;i<7;i++) {
			itemIDs[i].setItemMeta(itemMetas[i]);
		}

		//アイテムの配置
		inv.setItem(26, itemIDs[6]);
		inv.setItem(16, itemIDs[5]);
		inv.setItem(14, itemIDs[4]);
		inv.setItem(12, itemIDs[3]);
		inv.setItem(10, itemIDs[2]);
		player.openInventory(inv);
		return;
	}

	//itemIDsにMaterialを入れる
	public static ItemStack[] itemIDs() {
		ItemStack[] itemIDs = new ItemStack[]{
			new ItemStack(Material.COOKED_BEEF),
			new ItemStack(Material.BONE),
			new ItemStack(Material.WOOD_SWORD),
			new ItemStack(Material.STONE_SWORD),
			new ItemStack(Material.IRON_SWORD),
			new ItemStack(Material.DIAMOND_SWORD),
			new ItemStack(Material.ARROW),
			};
		return itemIDs;
	}

	//ItemMetasにItemIDsのメタを入れる
	public static ItemMeta[] itemMetas() {
		ItemMeta[] ItemMetas = new ItemMeta[itemIDs.length];
		for(int i=0;i<itemIDs.length;i++) {
			ItemMetas[i] = itemIDs[i].getItemMeta();
		}
		return ItemMetas;
	}


}



