package de.yellowphoenix18.kingofthehillplus.commands;

import java.util.HashSet;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.yellowphoenix18.kingofthehillplus.config.LocationConfig;
import de.yellowphoenix18.kingofthehillplus.config.MainConfig;
import de.yellowphoenix18.kingofthehillplus.config.MessagesConfig;
import de.yellowphoenix18.kingofthehillplus.config.SignsConfig;
import de.yellowphoenix18.kingofthehillplus.utils.ArenaConfig;
import de.yellowphoenix18.kingofthehillplus.utils.ArenaStatus;
import de.yellowphoenix18.kingofthehillplus.utils.PluginUtils;

public class KOTHPCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length == 0) {
				if(p.hasPermission("kothp.*") || p.hasPermission("kothp.help")) {
					p.sendMessage(MessagesConfig.prefix + MessagesConfig.help);
				} else {
					p.sendMessage(MessagesConfig.prefix + MessagesConfig.no_permission);
				}
			} else {
				if(args[0].equalsIgnoreCase("addArena")) {
					if(p.hasPermission("kothp.*") || p.hasPermission("kothp.addarena")) {
						if(args.length == 4) {
							String arena = args[1];
							if(!MainConfig.arenas.contains(arena)) {
								int min_players = Integer.valueOf(args[2]);
								int max_players = Integer.valueOf(args[3]);
								ArenaConfig cfg = new ArenaConfig(arena);
								cfg.setStatus(ArenaStatus.WAITING);
								cfg.setMaxPlayers(max_players);
								cfg.setMinPlayers(min_players);
								cfg.setArena(arena);
								cfg.saveConfig();
								MainConfig.addArena(arena);
								PluginUtils.loadArena(arena);
								p.sendMessage(MessagesConfig.prefix + "§7Arena §a" + arena + " §7successfully added to Map-Pool");
							} else {
								p.sendMessage(MessagesConfig.prefix + "§7Arena §c" + arena + " §7already in Map-Pool");
							}
						} else {
							p.sendMessage(MessagesConfig.prefix + MessagesConfig.argument_error);
						}
					} else {
						p.sendMessage(MessagesConfig.prefix + MessagesConfig.no_permission);
					}
				} else if(args[0].equalsIgnoreCase("addSign")) {
					if(p.hasPermission("kothp.*") || p.hasPermission("kothp.addsign")) {
						if(args.length == 3) {
							String sign_name = args[1];
							String arena = args[2];
							if(MainConfig.arenas.contains(arena)) {
								if(!SignsConfig.getSigns().contains(sign_name)) {
									@SuppressWarnings("deprecation")
									Location loc = p.getTargetBlock((HashSet<Byte>) null, 20).getLocation();
									SignsConfig.addSign(sign_name, loc, arena);
									p.sendMessage(MessagesConfig.prefix + "§7Sign §a" + sign_name + " §7for Arena §a" + arena + " §7successfully added");
								} else {
									p.sendMessage(MessagesConfig.prefix + "§cSign-Name already in use");
								}
							} else {
								p.sendMessage(MessagesConfig.prefix + "§cInvalid arena");
							}
						} else {
							p.sendMessage(MessagesConfig.prefix + MessagesConfig.argument_error);
						}
					} else {
						p.sendMessage(MessagesConfig.prefix + MessagesConfig.no_permission);
					}
				} else if(args[0].equalsIgnoreCase("setLobby")) {
					if(p.hasPermission("kothp.*") || p.hasPermission("kothp.setlobby")) {
						if(args.length == 2) {
							String arena = args[1];
							if(MainConfig.arenas.contains(arena)) {
								LocationConfig.setLocation(arena + ".Lobby", p.getLocation());
								p.sendMessage(MessagesConfig.prefix + "§7Lobby for Arena §a" + arena + " §7successfully added");
							} else {
								p.sendMessage(MessagesConfig.prefix + "§cInvalid arena");
							}
						} else if(args.length == 1) {
							LocationConfig.setLocation("Lobby", p.getLocation());
							p.sendMessage(MessagesConfig.prefix + "§7Lobby successfully added");
						} else {
							p.sendMessage(MessagesConfig.prefix + MessagesConfig.argument_error);
						}
					} else {
						p.sendMessage(MessagesConfig.prefix + MessagesConfig.no_permission);
					}
				} else if(args[0].equalsIgnoreCase("setFinish")) {
					if(p.hasPermission("kothp.*") || p.hasPermission("kothp.setfinish")) {
						if(args.length == 2) {
							String arena = args[1];
							if(MainConfig.arenas.contains(arena)) {
								LocationConfig.setLocation(arena + ".Finish", p.getLocation());
								p.sendMessage(MessagesConfig.prefix + "§7Finish for Arena §a" + arena + " §7successfully added");
							} else {
								p.sendMessage(MessagesConfig.prefix + "§cInvalid arena");
							}
						} else {
							p.sendMessage(MessagesConfig.prefix + MessagesConfig.argument_error);
						}
					} else {
						p.sendMessage(MessagesConfig.prefix + MessagesConfig.no_permission);
					}
				} else if(args[0].equalsIgnoreCase("setSpawn")) {
					if(p.hasPermission("kothp.*") || p.hasPermission("kothp.setspawn")) {
						if(args.length == 3) {
							String arena = args[1];
							int spawn = Integer.valueOf(args[2]);
							if(MainConfig.arenas.contains(arena)) {
								LocationConfig.setLocation(arena + ".Spawn." + spawn, p.getLocation());
								p.sendMessage(MessagesConfig.prefix + "§7Spawn §a" + spawn + " §7for Arena §a" + arena + " §7successfully added");
							} else {
								p.sendMessage(MessagesConfig.prefix + "§cInvalid arena");
							}
						} else {
							p.sendMessage(MessagesConfig.prefix + MessagesConfig.argument_error);
						}
					} else {
						p.sendMessage(MessagesConfig.prefix + MessagesConfig.no_permission);
					}
				} else if(args[0].equalsIgnoreCase("help")) {
					if(p.hasPermission("kothp.*") || p.hasPermission("kothp.help")) {
						p.sendMessage("§6/kothp help §8- §7Show all Commands");
						p.sendMessage("§6/kothp addArena <Name> <Min-Players> <Max-Players> §8- §7Add a new Arena");
						p.sendMessage("§6/kothp addSign <Name> <Arena> §8- §7Add a Sign");
						p.sendMessage("§6/kothp setLobby [<Arena>] §8- §7Set Main-Lobby/Lobby for Arena");
						p.sendMessage("§6/kothp setFinish <Arena> §8- §7Set Finish-Point for Arena");
						p.sendMessage("§6/kothp setSpawn <Arena> <Number> §8- §7Set Spawn-Point for Arena[1-MaxPlayers]");
					} else {
						p.sendMessage(MessagesConfig.prefix + MessagesConfig.no_permission);
					}
				} else {
					
				}
			}
		} else {
			
		}
		
		
		return true;
	}

}
