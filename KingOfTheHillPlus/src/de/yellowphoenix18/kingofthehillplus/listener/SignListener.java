package de.yellowphoenix18.kingofthehillplus.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import de.yellowphoenix18.kingofthehillplus.config.SignsConfig;
import de.yellowphoenix18.kingofthehillplus.utils.Arena;
import de.yellowphoenix18.kingofthehillplus.utils.ArenaStatus;
import de.yellowphoenix18.kingofthehillplus.utils.PluginUtils;

public class SignListener implements Listener {
	
	@EventHandler
	public void on(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(e.getClickedBlock() != null) {
				Material m = e.getClickedBlock().getType();
				if(m == Material.SIGN || m == Material.SIGN_POST || m == Material.WALL_SIGN) {
					if(SignsConfig.isSign(e.getClickedBlock().getLocation())) {
						String arena = SignsConfig.getArena(e.getClickedBlock().getLocation());
						if(PluginUtils.arenas.containsKey(arena)) {
							Arena a = PluginUtils.arenas.get(arena);
							if(a.getStatus() != ArenaStatus.OFFLINE) {
								if(a.getStatus() != ArenaStatus.INGAME) {
									a.joinArea(p);
								}
							}
						} else {
							p.sendMessage("§cArena not loaded");
						}
					}
				}
			}
		}
	}

}
