package MobWave.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.scheduler.BukkitTask;

import MobWave.MobWaveMain;
import MobWave.Task.MobWaveTask;

public class MobWaveListener implements Listener {

	BukkitTask task;
	Player pl = null;
	boolean hantei;

	@EventHandler
	public void InventoryClickEvent(InventoryClickEvent event,InventoryInteractEvent e) {

		//プレイヤー取得
		pl=(Player) e.getWhoClicked();
		//Event検知テスト
		pl.sendMessage("t");
		//何クリックをしたか
		ClickType type = event.getClick();

		//右クリか左クリをしたら
		if(type.isRightClick() || type.isLeftClick()) {
			//Event検知テスト
			pl.sendMessage("tt");
			//クリックしたslotの取得
			int slot=event.getSlot();
			//クリックキャンセル
			e.setCancelled(true);

			//スロット１１(ステーキ)がクリックされたとき
			if(slot==11) {
				//既に実行済みじゃない時
				if(hantei) {
					pl.sendMessage("Waveを開始します");
					task = new MobWaveTask(pl).runTaskTimer(MobWaveMain.getPlugin(), 20, 60);
					hantei=false;
				}
				//既に実行済みの時
				else {
					pl.sendMessage("既に実行しています");
				}
			}
			//スロット１5(ほね)がクリックされたとき
			else if (slot==15) {
				//task終了
				task.cancel();
				//実行済みの判定初期化
				hantei=true;
				pl.sendMessage("Waveを終了します");
			}
		}
		return;
	}
}
