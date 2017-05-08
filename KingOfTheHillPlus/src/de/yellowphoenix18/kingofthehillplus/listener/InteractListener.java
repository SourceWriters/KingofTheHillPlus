package de.yellowphoenix18.kingofthehillplus.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import de.yellowphoenix18.kingofthehillplus.config.MainConfig;
import de.yellowphoenix18.kingofthehillplus.utils.PluginUtils;

public class InteractListener implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void on(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(p.getItemInHand().getTypeId() == MainConfig.leave_id && p.getItemInHand().getData().getData() == MainConfig.leave_sub_id) {
				if(p.getItemInHand().hasItemMeta()) {
					if(p.getItemInHand().getItemMeta().hasDisplayName()) {
						String disp = p.getItemInHand().getItemMeta().getDisplayName();
						if(disp.equalsIgnoreCase(MainConfig.leave_name)) {
							if(PluginUtils.players.containsKey(p)) {
								String arena = PluginUtils.players.get(p).getArena();
								if(PluginUtils.arenas.containsKey(arena)) {
									PluginUtils.arenas.get(arena).leaveArena(p);
								} else {
									System.out.println("Error");
								}
							}
						}
					}
				}
			}
		}
	}

}
