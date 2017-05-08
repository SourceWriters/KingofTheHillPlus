package de.yellowphoenix18.kingofthehillplus.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class MessagesConfig {
	
	public static File f = new File("plugins/KingofTheHillPlus", "messages.yml");
	public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
	
	public static String prefix = "&8[&6KingOfTheHill&8] &7";
	
	public static String no_permission = "&cYou dont have the permission to use this command";
	public static String argument_error = "&7Less or too much Arguments! Please enter &c%Command%";
	
	public static String help = "&7Please enter &c/kothp help &7for help";
	
	public static String waiting_line1 = "&8[&aLobby&8]";
	public static String waiting_line2 = "%Arena%";
	public static String waiting_line3 = "&7%CurrentPlayers%&8/&7%MaxPlayers%";
	public static String waiting_line4 = "";
	
	public static String lobby_line1 = "&8[&aLobby&8]";
	public static String lobby_line2 = "%Arena%";
	public static String lobby_line3 = "&7%CurrentPlayers%&8/&7%MaxPlayers%";
	public static String lobby_line4 = "";
	
	public static String ingame_line1 = "&8[&6InGame&8]";
	public static String ingame_line2 = "%Arena%";
	public static String ingame_line3 = "&7%CurrentPlayers%&8/&7%MaxPlayers%";
	public static String ingame_line4 = "";
	
	public static String offline_line1 = "&4----------";
	public static String offline_line2 = "%Arena%";
	public static String offline_line3 = "&7Offline";
	public static String offline_line4 = "&4----------";
	
	public static String waiting_message = "&7Game starts with &a%MinPlayers% &7Players";
	public static String lobby_message = "&7Game starts in &e%Time% &7second/s";
	public static String game_message_minutes = "&7The Game ends in &e%Time% &7minute/s";
	public static String game_message_seconds = "&7The Game ends in &e%Time% &7second/s";
	public static String end_message = "&7The Arena restarts in &e%Time% &7second/s";
	
	public static String broadcast_winner = "&e%Player% &7has won the game";
	public static String broadcast_tie = "&7The game ends with a &eTie";
	
	public static void load() {
		prefix = fixColors(setObject("Global.Prefix", prefix));
		
		waiting_message = fixColors(setObject("Messages.Timer.Waiting", waiting_message));
		lobby_message = fixColors(setObject("Messages.Timer.Lobby", lobby_message));
		game_message_minutes = fixColors(setObject("Messages.Timer.Game.Minutes", game_message_minutes));
		game_message_seconds = fixColors(setObject("Messages.Timer.Game.Seconds", game_message_seconds));
		end_message = fixColors(setObject("Messages.Timer.End", end_message));
		
		broadcast_winner = fixColors(setObject("Messages.Broadcast.Winner", broadcast_winner));
		broadcast_tie = fixColors(setObject("Messages.Broadcast.Tie", broadcast_tie));
		
		no_permission = fixColors(setObject("Error.No-Perm", no_permission));
		argument_error = fixColors(setObject("Error.Argument-Error", argument_error));
		
		help = fixColors(setObject("Help.Help-Inform", help));
		
		waiting_line1 = fixColors(setObject("Sign-Layout.Waiting.Line1", waiting_line1));
		waiting_line2 = fixColors(setObject("Sign-Layout.Waiting.Line2", waiting_line2));
		waiting_line3 = fixColors(setObject("Sign-Layout.Waiting.Line3", waiting_line3));
		waiting_line4 = fixColors(setObject("Sign-Layout.Waiting.Line4", waiting_line4));
		
		lobby_line1 = fixColors(setObject("Sign-Layout.Lobby.Line1", lobby_line1));
		lobby_line2 = fixColors(setObject("Sign-Layout.Lobby.Line2", lobby_line2));
		lobby_line3 = fixColors(setObject("Sign-Layout.Lobby.Line3", lobby_line3));
		lobby_line4 = fixColors(setObject("Sign-Layout.Lobby.Line4", lobby_line4));
		
		ingame_line1 = fixColors(setObject("Sign-Layout.InGame.Line1", ingame_line1));
		ingame_line2 = fixColors(setObject("Sign-Layout.InGame.Line2", ingame_line2));
		ingame_line3 = fixColors(setObject("Sign-Layout.InGame.Line3", ingame_line3));
		ingame_line4 = fixColors(setObject("Sign-Layout.InGame.Line4", ingame_line4));
		
		offline_line1 = fixColors(setObject("Sign-Layout.Offline.Line1", offline_line1));
		offline_line2 = fixColors(setObject("Sign-Layout.Offline.Line2", offline_line2));
		offline_line3 = fixColors(setObject("Sign-Layout.Offline.Line3", offline_line3));
		offline_line4 = fixColors(setObject("Sign-Layout.Offline.Line4", offline_line4));
	}
	
	

	public static String fixColors(String code) {
		code = code.replace("&", "§");
		return code;
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
	
	public static void save() {
		try {
			cfg.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
