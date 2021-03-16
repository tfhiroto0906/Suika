package MobWave.Task;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class shopGUITask {
	static Player player;
	static Inventory inv = Bukkit.createInventory(null, 27,ChatColor.WHITE+ "SHOP");
	static ItemStack[] itemIDs = itemIDs();
	static ItemMeta[] itemMetas = itemMetas();

	//shop買い、売り選択画面
	public static void shopGUI(Player pl) {
		player = pl;
		inv=null;
		inv = Bukkit.createInventory(null, 27,ChatColor.WHITE + "SHOP");

		itemMetas[0].setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD +"itemを購入する");
		itemMetas[1].setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD +"itemを売る");
		for(int i=0;i<2;i++) {
			itemIDs[i].setItemMeta(itemMetas[i]);
		}

		//アイテムの配置
		inv.setItem(11, itemIDs[0]);
		inv.setItem(15, itemIDs[1]);
		player.openInventory(inv);
	}

	//shop 買いを選んだ時のGUI
	public static void buyGUI(Player pl) {
		player = pl;
		inv=null;
		inv = Bukkit.createInventory(null, 27,ChatColor.WHITE + "SHOP");

		itemMetas[0].setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD +"itemを購入する");
		itemMetas[1].setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD +"itemを売る");
		for(int i=0;i<2;i++) {
			itemIDs[i].setItemMeta(itemMetas[i]);
		}

		//アイテムの配置
		inv.setItem(15, itemIDs[0]);
		inv.setItem(11, itemIDs[1]);
		player.openInventory(inv);
	}

	//shop 売りを選んだ時のGUI
	public static void sellGUI(Player pl) {
		player = pl;
		inv=null;
		inv = Bukkit.createInventory(null, 27,ChatColor.WHITE + "SHOP");

		itemMetas[0].setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD +"itemを購入する");
		itemMetas[1].setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD +"itemを売る");
		for(int i=0;i<2;i++) {
			itemIDs[i].setItemMeta(itemMetas[i]);
		}

		//アイテムの配置
		inv.setItem(11, itemIDs[0]);
		inv.setItem(16, itemIDs[1]);
		player.openInventory(inv);
	}

	public static ItemStack[] itemIDs() {
		ItemStack[] itemIDs = new ItemStack[]{
			new ItemStack(Material.BOOK),
			new ItemStack(Material.GOLD_INGOT),
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

	public static Inventory invID () {
		Inventory invID = inv;
		return invID;
	}
}

