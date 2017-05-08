package de.yellowphoenix18.kingofthehillplus.utils;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class ConnectData {
	
	private String arena;
	private Inventory inv = Bukkit.createInventory(null, InventoryType.PLAYER);
	private GameMode gamemode;
	private int food_level;
	
	public ConnectData(String arena, Inventory inv, GameMode gm, int food) {
		this.arena = arena;
		this.inv.setContents(inv.getContents());
		this.gamemode = gm;
		this.food_level = food;
	}
	
	public String getArena() {
		return this.arena;
	}
	
	public int getFoodlevel() {
		return this.food_level;
	}
	
	public GameMode getGameMode() {
		return this.gamemode;
	}
	
	public Inventory getInventory() {
		return this.inv;
	}

}
