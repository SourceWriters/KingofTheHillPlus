package de.yellowphoenix18.kingofthehillplus;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import de.yellowphoenix18.kingofthehillplus.utils.Arena;
import de.yellowphoenix18.kingofthehillplus.utils.PluginUtils;

public class KingOfTheHillPlus extends JavaPlugin {
	
	public static KingOfTheHillPlus m;
	
	public void onEnable() {
		m = this;
		PluginUtils.setUp();
	}
	
	public void onDisable() {
		for(String arena : PluginUtils.arenas.keySet()) {
			Arena a = PluginUtils.arenas.get(arena);
			for(Player p : a.getPlayers()) {
				a.leaveArena(p);
			}
		}
	}

}
