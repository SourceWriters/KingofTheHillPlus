package de.yellowphoenix18.kingofthehillplus.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import de.yellowphoenix18.kingofthehillplus.utils.PluginUtils;

public class DamageListener implements Listener {
	
	@EventHandler
	public void on(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if(PluginUtils.players.containsKey(p)) {
				e.setCancelled(true);
			}
		}
	}

}
