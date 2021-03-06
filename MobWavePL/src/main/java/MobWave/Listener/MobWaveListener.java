package MobWave.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import MobWave.MobWaveMain;
import MobWave.Task.MobWaveTask;
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
			//ステーキをクリックしたら
			if(hikakuIDs[0].getType().equals(e.getCurrentItem().getType())) {
				pl.closeInventory();
				displayGUI.difficultyGUI(pl);
				return;
			}
			if(hantei) {
				//木の剣がクリックされたとき
				if(hikakuIDs[2].getType().equals(e.getCurrentItem().getType())) {
					pl.sendMessage("Easy");
					pl.closeInventory();
					task = new MobWaveTask(pl,1).runTaskTimer(MobWaveMain.getPlugin(),20,60);
					hantei=false;
					return;
				}
				//石の剣がクリックされたとき
				if(hikakuIDs[3].getType().equals(e.getCurrentItem().getType())) {
					pl.sendMessage("Normal");
					pl.closeInventory();
					task = new MobWaveTask(pl,2).runTaskTimer(MobWaveMain.getPlugin(),20,60);
					return;
				}
				//鉄の件がクリックされたとき
				if(hikakuIDs[4].getType().equals(e.getCurrentItem().getType())) {
					pl.sendMessage("Hard");
					pl.closeInventory();
					task = new MobWaveTask(pl,3).runTaskTimer(MobWaveMain.getPlugin(),20,40);
					hantei=false;
					return;
				}
				//ダイヤの剣がクリックされたとき
				if(hikakuIDs[5].getType().equals(e.getCurrentItem().getType())) {
					pl.sendMessage("VeryHard");
					pl.closeInventory();
					task = new MobWaveTask(pl,4).runTaskTimer(MobWaveMain.getPlugin(),20,20);
					hantei=false;
					return;
				}
			}
			//hanteiがfalseなら
			else {
				pl.sendMessage("既に実行中です");
			}
		}
		//骨がクリックされたとき
		if(hikakuIDs[1].getType().equals(e.getCurrentItem().getType())) {
			//taskの終了処理
			task.cancel();
			hantei=true;
			pl.sendMessage("Waveを終了します");
			pl.closeInventory();
			return;
		}
		return;
	}
}
