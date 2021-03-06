package MobWave.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import MobWave.Task.displayGUI;

public class MobWaveListener implements Listener {

	static Player pl;
	static BukkitTask task;
	static boolean hantei=true;
	//ItemStack配列の取得 displayGUIクラスから
	ItemStack[] hikakuIDs = displayGUI.itemIDs();

	@EventHandler
	public void InventoryClickEvent(InventoryClickEvent e) {
		pl=(Player) e.getWhoClicked();

		//開いたGUIが作ったGUIなら
		if (e.getInventory().getName().equals(displayGUI.invID().getName())) {
			e.setCancelled(true);
			//検出テスト
			pl.sendMessage("kiteru");

			if(hikakuIDs[0].equals(e.getCurrentItem())) {
				pl.closeInventory();
				displayGUI.difficultyGUI(pl);
				return;
			}
			//骨がクリックされたとき
			if(hikakuIDs[1].equals(e.getCurrentItem())) {
				//taskの終了処理
				task.cancel();
				hantei=true;
				pl.sendMessage("Waveを終了します");
				pl.closeInventory();
				return;
			}
			//木の剣がクリックされたとき
			if(hikakuIDs[2].equals(e.getCurrentItem())) {
				pl.sendMessage("Easy");
				pl.closeInventory();
				return;
			}
			//石の剣がクリックされたとき
			if(hikakuIDs[3].equals(e.getCurrentItem())) {
				pl.sendMessage("Normal");
				pl.closeInventory();
				return;
			}
			//鉄の件がクリックされたとき
			if(hikakuIDs[4].equals(e.getCurrentItem())) {
				pl.sendMessage("Hard");
				pl.closeInventory();
				return;
			}
			//ダイヤの剣がクリックされたとき
			if(hikakuIDs[5].equals(e.getCurrentItem())) {
				pl.sendMessage("VeryHard");
				pl.closeInventory();
				return;
			}

		}
		return;
	}
}
