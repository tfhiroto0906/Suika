package MobWave.Task;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.minecraft.server.v1_8_R3.NBTTagCompound;

public class shopTask {
	static Player player;
	static Inventory inv = Bukkit.createInventory(null, 27,ChatColor.WHITE+ "SHOP");
	static ItemStack[] itemIDs = itemIDs();
	static ItemMeta[] itemMetas = itemMetas();

	//shop villagerのスポーン
	public static Entity spawnShop(Player player) {
        Location loc = player.getLocation();
        Entity v = (Entity) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
        v.setCustomName("§5§lSHOP");
        v.setCustomNameVisible(true);
        removeAI(v);
        return v;
	}

	//wave villagerのspawn
	public static Entity spawnWave(Player player) {
        Location loc = player.getLocation();
        Entity v = (Entity) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
        v.setCustomName("§4§lWAVE");
        v.setCustomNameVisible(true);
        removeAI(v);
        return v;
	}

	//shopvillagerのAIを消す
	public static void removeAI(Entity v) {
		net.minecraft.server.v1_8_R3.Entity nmsEnt = ((CraftEntity) v).getHandle();
		NBTTagCompound tag = nmsEnt.getNBTTag();
		if (tag == null) {
			tag = new NBTTagCompound();

		}
		nmsEnt.c(tag);
		tag.setInt("NoAI", 1);
		nmsEnt.f(tag);
	}

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

