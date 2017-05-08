package de.yellowphoenix18.kingofthehillplus.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ArenaConfig {

	private File f = new File("plugins/KingOfTheHillPlus/Arenas", "arena.yml");
	private FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
	
	private int min_players = 2;
	private int max_players = 10;
	private ArenaStatus status = ArenaStatus.OFFLINE;
	private String arena;
	
	public ArenaConfig(String arena) {
		f = new File("plugins/KingOfTheHillPlus/Arenas", arena + ".yml");
		cfg = YamlConfiguration.loadConfiguration(f);
	}
	
	public int getMaxPlayers() {
		return this.max_players;
	}
	
	public void setArena(String arena) {
		this.arena = arena;
	}
	
	public String getArena() {
		return this.arena;
	}
	
	public void setMaxPlayers(int max_players) {
		this.max_players = max_players;
		max_players = cfg.getInt("Players.Max");
		save();
	}
	
	public int getMinPlayers() {
		return this.min_players;
	}
	
	public void setMinPlayers(int min_players) {
		this.min_players = min_players;
		cfg.set("Players.Min", min_players);
		save();
	}
	
	public ArenaStatus getStatus() {
		return this.status;
	}
	
	public void setStatus(ArenaStatus status) {
		this.status = status;
		cfg.set("Status", status.toString());
		save();
	}
	
	
	public void load() {
		min_players = cfg.getInt("Players.Min");
		max_players = cfg.getInt("Players.Max");
		status = ArenaStatus.valueOf(cfg.getString("Status"));
		arena = cfg.getString("Arena");
	}
	
	public void saveConfig() {
		cfg.set("Players.Min", min_players);
		cfg.set("Players.Max", max_players);
		cfg.set("Status", status.toString());
		cfg.set("Arena", arena);
		save();
	}
	
	private void save() {
		try {
			cfg.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
