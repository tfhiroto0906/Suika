package MobWave.Listener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import MobWave.Task.MobWaveGUITask;
import MobWave.Task.shopTask;

public class shopListener implements Listener {

	@EventHandler
	public void villagerDamage(EntityDamageByEntityEvent event) {
		Entity entity=event.getEntity();
		if(event.getCause() == DamageCause.ENTITY_ATTACK){
			if(entity instanceof Villager){
				event.setDamage(0.0);
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void interactShop(PlayerInteractEntityEvent e) {
		//右クリされたのが村人ならtrue
		if(e.getRightClicked().getType() == EntityType.VILLAGER) {
			Villager v = (Villager) e.getRightClicked();
			e.setCancelled(true);
			//右クリされたのがshopなら
			if(v.getCustomName().equalsIgnoreCase("§5§lSHOP")) {
				Player player = e.getPlayer();
				shopTask.shopGUI(player);
			}
			//右クリされたのがWaveなら
			else if(v.getCustomName().equalsIgnoreCase("§4§lWAVE")) {
				Player player = e.getPlayer();
				MobWaveGUITask.firstGUI(player);
			}
		}
	}

	@EventHandler
	public void InventoryClickEvent(InventoryClickEvent e) {
		Player pl=(Player) e.getWhoClicked();
		//開いたGUIが作ったGUIなら
		if (e.getInventory().getName().equals(shopTask.invID().getName())) {
			e.setCancelled(true);
		//何もないところをクリックしたときreturn
			if(e.getCurrentItem()==null) {
				return;
			}
			//空気をクリックしたらreturn
			else if(e.getCurrentItem().getType().equals(Material.AIR)) {
				return;
			}
			else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "" + ChatColor.BOLD + "itemを購入する")) {
				pl.closeInventory();
				shopTask.buyGUI(pl);
				return;
			}
			else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "" + ChatColor.BOLD +"itemを売る")) {
				pl.closeInventory();
				shopTask.sellGUI(pl);
				return;
			}
		}
	}
}
