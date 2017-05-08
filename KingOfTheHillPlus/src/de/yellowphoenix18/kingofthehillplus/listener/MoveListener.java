package de.yellowphoenix18.kingofthehillplus.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import de.yellowphoenix18.kingofthehillplus.config.LocationConfig;
import de.yellowphoenix18.kingofthehillplus.utils.Arena;
import de.yellowphoenix18.kingofthehillplus.utils.ArenaStatus;
import de.yellowphoenix18.kingofthehillplus.utils.PluginUtils;

public class MoveListener implements Listener {
	
	@EventHandler
	public void on(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		
		if(PluginUtils.players.containsKey(p)) {
			if(LocationConfig.getLocation(PluginUtils.players.get(p).getArena() + ".Finish").distance(p.getLocation()) <= 1) {
				Arena a = PluginUtils.arenas.get(PluginUtils.players.get(p).getArena());
				if(a.getStatus() == ArenaStatus.INGAME) {
					a.endGame(p);
				}
			}
		}
	}

}
