package MobWave.Listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import MobWave.MobWaveMain;
import MobWave.Commands.displayGUICommand;
import MobWave.Task.MobWaveTask;

public class MobWaveListener implements Listener {

	static Player pl;
	static BukkitTask task;
	static boolean hantei=true;
	//ItemStack配列の取得 displayGUIクラスから
	ItemStack[] hikakuIDs = displayGUICommand.itemIDs();

	@EventHandler
	public void InventoryClickEvent(InventoryClickEvent e) {
		pl=(Player) e.getWhoClicked();

		//開いたGUIが作ったGUIなら
		if (e.getInventory().getName().equals(displayGUICommand.invID().getName())) {
			e.setCancelled(true);
			//何もないところをクリックしたときreturn
			if(e.getCurrentItem()==null) {
				return;
			}
			//空気をクリックしたらreturn
			if(e.getCurrentItem().getType().equals(Material.AIR)) {
				return;
			}
			//ステーキをクリックしたら
			if(hikakuIDs[0].getType().equals(e.getCurrentItem().getType())) {
				pl.closeInventory();
				displayGUICommand.difficultyGUI(pl);
				return;
			}
			//既に実行されていないか
			if(hantei) {
				//木の剣がクリックされたとき
				if(hikakuIDs[2].getType().equals(e.getCurrentItem().getType())) {
					pl.sendMessage("難易度:EasyでWaveを開始します");
					task = new MobWaveTask(pl,1).runTaskTimer(MobWaveMain.getPlugin(),20,60);
					hantei=false;
					return;
				}
				//石の剣がクリックされたとき
				if(hikakuIDs[3].getType().equals(e.getCurrentItem().getType())) {
					pl.sendMessage("難易度:NormalでWaveを開始します");
					task = new MobWaveTask(pl,2).runTaskTimer(MobWaveMain.getPlugin(),20,60);
					return;
				}
				//鉄の件がクリックされたとき
				if(hikakuIDs[4].getType().equals(e.getCurrentItem().getType())) {
					pl.sendMessage("難易度:HardでWaveを開始します");
					task = new MobWaveTask(pl,3).runTaskTimer(MobWaveMain.getPlugin(),20,40);
					hantei=false;
					return;
				}
				//ダイヤの剣がクリックされたとき
				if(hikakuIDs[5].getType().equals(e.getCurrentItem().getType())) {
					pl.sendMessage("難易度:VeryHardでWaveを開始します");
					task = new MobWaveTask(pl,4).runTaskTimer(MobWaveMain.getPlugin(),20,20);
					hantei=false;
					return;
				}
				//hantei trueの時にbackをクリック
				if(hikakuIDs[6].getType().equals(e.getCurrentItem().getType())) {
					pl.closeInventory();
					displayGUICommand.firstGUI(pl);
					return;
				}
				if(hikakuIDs[1].getType().equals(e.getCurrentItem().getType())) {
					//taskの終了処理
					pl.sendMessage("実行中のWaveはありません");
					return;
				}
			}
			else if(hikakuIDs[1].getType().equals(e.getCurrentItem().getType())) {
				//taskの終了処理
				task.cancel();
				hantei=true;
				pl.sendMessage("Waveを終了します");
				return;
			}
			//hantei falseの時にbackをクリック
			else if(hikakuIDs[6].getType().equals(e.getCurrentItem().getType())) {
				pl.closeInventory();
				displayGUICommand.firstGUI(pl);
				return;
			}
			//hanteiがfalseなら
			else {
				pl.sendMessage("既に実行中です");
			}
		}
	}
}
