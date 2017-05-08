package de.yellowphoenix18.kingofthehillplus.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import de.yellowphoenix18.kingofthehillplus.utils.PluginUtils;

public class FoodListener implements Listener {
	
	@EventHandler
	public void on(FoodLevelChangeEvent e) {
		Player p = (Player) e.getEntity();
		
		if(PluginUtils.players.containsKey(p)) {
			e.setCancelled(true);
		}
	}

}
