package de.yellowphoenix18.kingofthehillplus.utils;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

import de.yellowphoenix18.kingofthehillplus.KingOfTheHillPlus;
import de.yellowphoenix18.kingofthehillplus.commands.KOTHPCommand;
import de.yellowphoenix18.kingofthehillplus.config.MainConfig;
import de.yellowphoenix18.kingofthehillplus.config.MessagesConfig;
import de.yellowphoenix18.kingofthehillplus.listener.BlockListener;
import de.yellowphoenix18.kingofthehillplus.listener.CommandListener;
import de.yellowphoenix18.kingofthehillplus.listener.DamageListener;
import de.yellowphoenix18.kingofthehillplus.listener.FoodListener;
import de.yellowphoenix18.kingofthehillplus.listener.InteractListener;
import de.yellowphoenix18.kingofthehillplus.listener.MoveListener;
import de.yellowphoenix18.kingofthehillplus.listener.SignListener;

public class PluginUtils {
	
	public static HashMap<Player, ConnectData> players = new HashMap<Player, ConnectData>();
	
	public static HashMap<String, Arena> arenas = new HashMap<String, Arena>();
	
	public static void setUp() {
		loadConfigs();
		loadListener();
		loadCommands();
		loadArenas();
		SignUtils.updateSigns();
	}
	
	public static void loadListener() {
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new BlockListener(), KingOfTheHillPlus.m);
		pm.registerEvents(new InteractListener(), KingOfTheHillPlus.m);
		pm.registerEvents(new MoveListener(), KingOfTheHillPlus.m);
		pm.registerEvents(new CommandListener(), KingOfTheHillPlus.m);
		pm.registerEvents(new FoodListener(), KingOfTheHillPlus.m);
		pm.registerEvents(new DamageListener(), KingOfTheHillPlus.m);
		
		
		pm.registerEvents(new SignListener(), KingOfTheHillPlus.m);
	}
	
	public static void loadCommands() {
		KingOfTheHillPlus.m.getCommand("kothp").setExecutor(new KOTHPCommand());
	}
	
	public static void loadConfigs() {
		MainConfig.load();
		MessagesConfig.load();
	}
	
	public static void loadArenas() {
		for(String arena : MainConfig.arenas) {
			ArenaConfig cfg = new ArenaConfig(arena);
			cfg.load();
			Arena a = new Arena(cfg);
			arenas.put(arena, a);
		}
	}
	
	public static void loadArena(String arena) {
		ArenaConfig cfg = new ArenaConfig(arena);
		cfg.load();
		Arena a = new Arena(cfg);
		arenas.put(arena, a);
	}
}
