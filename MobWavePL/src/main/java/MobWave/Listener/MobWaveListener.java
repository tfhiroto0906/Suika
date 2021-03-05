package MobWave.Listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import MobWave.MobWaveMain;
import MobWave.Task.MobWaveTask;


public class MobWaveListener implements Listener {
	static BukkitTask task;
	Player pl = null;

	@EventHandler
	public void InventoryOpenEvent(InventoryOpenEvent event) {

		pl = (Player) event.getPlayer();
		pl.sendMessage("開きました");

	}

	@EventHandler
	public void InventoryPickupItemEvent(InventoryPickupItemEvent event) {
		//イベント検知テスト
		pl.sendMessage("tt");

		//pickupをキャンセル
		event.setCancelled(true);
		//pickupしたMaterialの取得
		ItemStack item = (ItemStack) event.getItem();
		ItemStack hikaku = new ItemStack(Material.COOKED_BEEF);

		//pickupしたのがcookedbeefなら
		if(item==hikaku) {
			//SpawnMobを20delay3秒間隔で呼ぶ
			task = new MobWaveTask(pl).runTaskTimer(MobWaveMain.getPlugin(), 20, 60);

		}
		else {

			pl.sendMessage("比較ミスってるよ");

		}
	}

}
