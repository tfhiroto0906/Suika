package MobWave.Task;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.NBTTagCompound;

public class villagerTask {

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

}
