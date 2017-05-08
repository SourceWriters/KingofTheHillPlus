package de.yellowphoenix18.kingofthehillplus.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import de.yellowphoenix18.kingofthehillplus.utils.PluginUtils;

public class CommandListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void on(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		if(PluginUtils.players.containsKey(p)) {
			e.setCancelled(true);
		}
	}

}
