package de.yellowphoenix18.kingofthehillplus.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class MainConfig {
	
	public static File f = new File("plugins/KingOfTheHillPlus", "config.yml");
	public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
	
	public static int waitingtime = 16;
	public static int lobbytime = 61;
	public static int gametime = 301;
	public static int endtime = 11;
	
	public static List<String> arenas = new ArrayList<String>();
	
	public static int leave_id = 378;
	public static int leave_sub_id = 0;
	public static String leave_name = "&7Back to Lobby";
	public static String leave_lore_1 = "&8Teleport back";
	public static String leave_lore_2 = "&8to Lobby";
	
	public static void load() {
		waitingtime = setObject("Times.Waiting", waitingtime);
		lobbytime = setObject("Times.Lobby", lobbytime);
		gametime = setObject("Times.Game", gametime);
		endtime = setObject("Times.End", endtime);
		
		arenas = setObject("Arenas", arenas);
		
		leave_id = setObject("Items.Leave.ID", leave_id);
		leave_sub_id = setObject("Items.Leave.Sub-ID", leave_sub_id);
		leave_name = fixColors(setObject("Items.Leave.Name", leave_name));
		leave_lore_1 = fixColors(setObject("Items.Leave.Lore-1", leave_lore_1));
		leave_lore_2 = fixColors(setObject("Items.Leave.Lore-2", leave_lore_2));
	}
	
	public static void addArena(String arena) {
		arenas.add(arena);
		cfg.set("Arenas", arenas);
		save();
	}
	
	

	public static String fixColors(String code) {
		code = code.replace("&", "§");
		return code;
	}
	
	public static List<String> setObject(String path, List<String> obj) {
		if(cfg.contains(path)) {
			return cfg.getStringList(path);
		} else {
			cfg.set(path, obj);
			save();
			return obj;
		}
	}
	
	public static String setObject(String path, String obj) {
		if(cfg.contains(path)) {
			return cfg.getString(path);
		} else {
			cfg.set(path, obj);
			save();
			return obj;
		}
	}
	
	public static double setObject(String path, double obj) {
		if(cfg.contains(path)) {
			return cfg.getDouble(path);
		} else {
			cfg.set(path, obj);
			save();
			return obj;
		}
	}
	
	public static int setObject(String path, int obj) {
		if(cfg.contains(path)) {
			return cfg.getInt(path);
		} else {
			cfg.set(path, obj);
			save();
			return obj;
		}
	}
	
	public static void save() {
		try {
			cfg.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
