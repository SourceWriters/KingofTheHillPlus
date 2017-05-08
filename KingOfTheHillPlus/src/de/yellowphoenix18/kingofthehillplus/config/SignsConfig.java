package de.yellowphoenix18.kingofthehillplus.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class SignsConfig {
	
	public static File f = new File("plugins/KingOfTheHillPlus", "signs.yml");
	public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
	
	public static String getArena(Location loc) {
		String arena = null;
		if(cfg.contains("Sign." + loc.getWorld().getName() + "." + loc.getBlockX() + "." + loc.getBlockY() + "." + loc.getBlockZ())) {
			arena = cfg.getString("Sign." + loc.getWorld().getName() + "." + loc.getBlockX() + "." + loc.getBlockY() + "." + loc.getBlockZ());
		}
		return arena;
	}
	
	public static boolean isSign(Location loc) {
		if(cfg.contains("Sign." + loc.getWorld().getName() + "." + loc.getBlockX() + "." + loc.getBlockY() + "." + loc.getBlockZ())) {
			return true;
		}
		return false;
	}
	
	public static void addSign(String sign_name, Location loc, String arena) {
		List<String> signs = new ArrayList<String>();
		if(cfg.contains("Signs")) {
			signs = cfg.getStringList("Signs");
		}
		signs.add(sign_name);
		cfg.set("S." + sign_name + ".loc.world", loc.getWorld().getName());
		cfg.set("S." + sign_name + ".loc.x", loc.getX());
		cfg.set("S." + sign_name + ".loc.y", loc.getY());
		cfg.set("S." + sign_name + ".loc.z", loc.getZ());
		cfg.set("S." + sign_name + ".loc.yaw", loc.getYaw());
		cfg.set("S." + sign_name + ".loc.pitch", loc.getPitch());
		cfg.set("S." + sign_name + ".arena", arena);
		cfg.set("Signs", signs);
		cfg.set("Sign." + loc.getWorld().getName() + "." + loc.getBlockX() + "." + loc.getBlockY() + "." + loc.getBlockZ(), arena);
		save();
	}
	
	public static Location getLocation(String sign_name) {
		Location loc = null;
		if(cfg.contains("S." + sign_name + ".loc.world")) {
			World w = Bukkit.getWorld(cfg.getString("S." + sign_name + ".loc.world"));
			double x = cfg.getDouble("S." + sign_name + ".loc.x");
			double y = cfg.getDouble("S." + sign_name + ".loc.y");
			double z = cfg.getDouble("S." + sign_name + ".loc.z");
			double yaw = cfg.getDouble("S." + sign_name + ".loc.yaw");
			double pitch = cfg.getDouble("S." + sign_name + ".loc.pitch");
			loc = new Location(w, x, y, z, (float) yaw, (float) pitch);
		}
		return loc;
	}
	
	public static String getArena(String sign_name) {
		return cfg.getString("S." + sign_name + ".arena");
	}
	
	public static List<String> getSigns() {
		List<String> signs = new ArrayList<String>();
		if(cfg.contains("Signs")) {
			signs = cfg.getStringList("Signs");
		}
		return signs;
	}
	
	public static void save() {
		try {
			cfg.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
