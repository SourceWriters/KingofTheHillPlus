package de.yellowphoenix18.kingofthehillplus.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Sign;

import de.yellowphoenix18.kingofthehillplus.KingOfTheHillPlus;
import de.yellowphoenix18.kingofthehillplus.config.MessagesConfig;
import de.yellowphoenix18.kingofthehillplus.config.SignsConfig;

public class SignUtils {
	
	public static void updateSigns() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(KingOfTheHillPlus.m, new Runnable() {
			@Override
			public void run() {
				for(String sign : SignsConfig.getSigns()) {
					if(SignsConfig.getLocation(sign).getBlock() != null) {
						Material m = SignsConfig.getLocation(sign).getBlock().getType();
						if(m == Material.SIGN || m == Material.SIGN_POST || m == Material.WALL_SIGN) {
							String arena = SignsConfig.getArena(sign);
							Sign s = (Sign) SignsConfig.getLocation(sign).getBlock().getState();
							if(PluginUtils.arenas.containsKey(arena)) {
								Arena a = PluginUtils.arenas.get(arena);
								if(a.getStatus() == ArenaStatus.INGAME) {
									s.setLine(0, replaceVars(MessagesConfig.ingame_line1, arena));
									s.setLine(1, replaceVars(MessagesConfig.ingame_line2, arena));
									s.setLine(2, replaceVars(MessagesConfig.ingame_line3, arena));
									s.setLine(3, replaceVars(MessagesConfig.ingame_line4, arena));
								} else if(a.getStatus() == ArenaStatus.WAITING) {
									s.setLine(0, replaceVars(MessagesConfig.waiting_line1, arena));
									s.setLine(1, replaceVars(MessagesConfig.waiting_line2, arena));
									s.setLine(2, replaceVars(MessagesConfig.waiting_line3, arena));
									s.setLine(3, replaceVars(MessagesConfig.waiting_line4, arena));
								} else if(a.getStatus() == ArenaStatus.LOBBY) {
									s.setLine(0, replaceVars(MessagesConfig.lobby_line1, arena));
									s.setLine(1, replaceVars(MessagesConfig.lobby_line2, arena));
									s.setLine(2, replaceVars(MessagesConfig.lobby_line3, arena));
									s.setLine(3, replaceVars(MessagesConfig.lobby_line4, arena));
								} else {
									s.setLine(0, replaceVars(MessagesConfig.offline_line1, arena));
									s.setLine(1, replaceVars(MessagesConfig.offline_line2, arena));
									s.setLine(2, replaceVars(MessagesConfig.offline_line3, arena));
									s.setLine(3, replaceVars(MessagesConfig.offline_line4, arena));
								}
							} else {
								s.setLine(0, MessagesConfig.offline_line1.replace("%Arena%", arena));
								s.setLine(1, MessagesConfig.offline_line2.replace("%Arena%", arena));
								s.setLine(2, MessagesConfig.offline_line3.replace("%Arena%", arena));
								s.setLine(3, MessagesConfig.offline_line4.replace("%Arena%", arena));
							}
							s.update();
						}
					}
				}
			}
		}, 15, 20);
	}
	
	public static String replaceVars(String text, String arena) {
		Arena a = PluginUtils.arenas.get(arena);
		text = text.replace("%Arena%", arena);
		text = text.replace("%CurrentPlayers%", "" + a.getCurrentPlayers());
		text = text.replace("%MaxPlayers%", "" + a.getMaxPlayers());
		return text;
	}

}
