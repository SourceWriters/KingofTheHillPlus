package de.yellowphoenix18.kingofthehillplus.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class LocationConfig {
	
	public static File f = new File("plugins/KingOfTheHillPlus", "locations.yml");
	public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
	
	public static void setLocation(String path, Location loc) {
		cfg.set(path + ".world" , loc.getWorld().getName());
		cfg.set(path + ".x" , loc.getX());
		cfg.set(path + ".y" , loc.getY());
		cfg.set(path + ".z" , loc.getZ());
		cfg.set(path + ".yaw" , loc.getYaw());
		cfg.set(path + ".pitch" , loc.getPitch());
		save();
	}
	
	public static Location getLocation(String path) {
		Location loc = null;
		if(cfg.contains(path + ".world")) {
			World w = Bukkit.getWorld(cfg.getString(path + ".world"));
			double x = cfg.getDouble(path + ".x");
			double y = cfg.getDouble(path + ".y");
			double z = cfg.getDouble(path + ".z");
			double yaw = cfg.getDouble(path + ".yaw");
			double pitch = cfg.getDouble(path + ".pitch");
			loc = new Location(w, x, y, z, (float) yaw, (float) pitch);
		}
		return loc;		
	}
	
	public static void removeLocation(String path) {
		cfg.set(path + ".world" , null);
		cfg.set(path + ".x" , null);
		cfg.set(path + ".y" , null);
		cfg.set(path + ".z" , null);
		cfg.set(path + ".yaw" , null);
		cfg.set(path + ".pitch" , null);
		save();		
	}
	
	public static void save() {
		try {
			cfg.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
