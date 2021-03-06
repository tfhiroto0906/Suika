package MobWave.Task;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.Creature;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class MobWaveTask extends BukkitRunnable{
	Player pl=null;;
	Creature mob = null;
	int kazu;

	public MobWaveTask(Player pl,int kazu){
		this.pl=pl;
		this.kazu=kazu;
	}

	@Override
	public void run() {

		//playerの座標をlcに代入
		Location lc = pl.getLocation().clone();

		Random rand = new Random();

		//-5~+5のxyz乱数の生成
		int x = rand.nextInt(10)-5,y=1,z = rand.nextInt(10)-5;

		//プレイヤーの範囲-5~+5に座標を設定
		lc.add(x,y,z);

		//mobのスポーン
		for(int i=0;i<kazu;i++) {
			mob = (Creature) pl.getWorld().spawnEntity(lc, EntityType.ZOMBIE);
		}
	}
}
