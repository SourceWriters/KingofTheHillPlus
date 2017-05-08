package de.yellowphoenix18.kingofthehillplus.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import de.yellowphoenix18.kingofthehillplus.KingOfTheHillPlus;
import de.yellowphoenix18.kingofthehillplus.config.LocationConfig;
import de.yellowphoenix18.kingofthehillplus.config.MainConfig;
import de.yellowphoenix18.kingofthehillplus.config.MessagesConfig;

public class Arena {
	
	private ArenaStatus status;
	private String arena_name;
	private int min_players = 0;
	private int max_players = 0;
	
	private int waitingtask;
	private int waitingtime = 16;
	private int lobbytask;
	private int lobbytime = 61;
	private int gametask;
	private int gametime = 301;
	private int endtask;
	private int endtime = 11;
	
	private Player winner = null;
	
	private List<Player> arena_players = new ArrayList<Player>();
	
	public Arena(ArenaConfig cfg) {
		this.status = cfg.getStatus();
		this.min_players = cfg.getMinPlayers();
		this.max_players = cfg.getMaxPlayers();
		this.arena_name = cfg.getArena();
		loadTimes();
		if(this.status != ArenaStatus.OFFLINE) {
			WaitingTask();
		}
	}
	
	public void loadTimes() {
		waitingtime = MainConfig.waitingtime;
		lobbytime = MainConfig.lobbytime;
		gametime = MainConfig.gametime;
		endtime = MainConfig.endtime;
	}
	
	public int getMinPlayers() {
		return this.min_players;
	}
	
	public int getMaxPlayers() {
		return this.max_players;
	}
	
	public int getCurrentPlayers() {
		return this.arena_players.size();
	}
	
	public ArenaStatus getStatus() {
		return this.status;
	}
	
	public void broadcastArena(String message) {
		for(Player all : arena_players) {
			all.sendMessage(message);
		}
	}
	
	public void joinArea(Player p) {
		ConnectData data = new ConnectData(this.arena_name, p.getInventory(), p.getGameMode(), p.getFoodLevel());
		p.setFoodLevel(20);
		p.setGameMode(GameMode.SURVIVAL);
		PluginUtils.players.put(p, data);
		p.getInventory().clear();
		p.getInventory().setItem(8, ItemUtils.ItemStackCreatorID(MainConfig.leave_name, MainConfig.leave_lore_1, MainConfig.leave_lore_2, MainConfig.leave_id, MainConfig.leave_sub_id, 1));
		for(Player all : arena_players) {
			all.showPlayer(p);
		}
		for(Player all : Bukkit.getOnlinePlayers()) {
			if(!arena_players.contains(all) && all != p) {
				all.hidePlayer(p);
				p.hidePlayer(all);
			}
		}
		arena_players.add(p);
		Location loc = LocationConfig.getLocation(arena_name + ".Lobby");
		if(loc != null) {
			p.teleport(loc);
		}
	}
	
	public List<Player> getPlayers() {
		return this.arena_players;
	}
	
	public void leaveArena(Player p) {
		p.getInventory().clear();
		ConnectData data = PluginUtils.players.get(p);
		p.getInventory().clear();
		p.getInventory().setContents(data.getInventory().getContents());
		p.setGameMode(data.getGameMode());
		p.updateInventory();
		for(Player all : PluginUtils.players.keySet()) {
			all.hidePlayer(p);
		}
		for(Player all : Bukkit.getOnlinePlayers()) {
			if(!PluginUtils.players.containsKey(all) && all != p) {
				all.showPlayer(p);
				p.showPlayer(all);
			}
		}
		if(status != ArenaStatus.ENDGAME) {
			arena_players.remove(p);
		}
		Location loc = LocationConfig.getLocation("Lobby");
		if(loc != null) {
			p.teleport(loc);
		}	
		PluginUtils.players.remove(p);
		p.setFoodLevel(data.getFoodlevel());
	}
	
	public void endGame(Player winner) {
		this.winner = winner;
		Bukkit.getScheduler().cancelTask(this.gametask);
		EndTask();
	}
	
	public void WaitingTask() {
		status = ArenaStatus.WAITING;
		this.waitingtask = Bukkit.getScheduler().scheduleSyncRepeatingTask(KingOfTheHillPlus.m, new Runnable() {
			@Override
			public void run() {
				waitingtime--;
				
				if(arena_players.size() >= min_players) {
					waitingtime = MainConfig.waitingtime;
					Bukkit.getScheduler().cancelTask(waitingtask);
					LobbyTask();
				}
 				
				if(waitingtime == 0) {
					broadcastArena(MessagesConfig.prefix + MessagesConfig.waiting_message.replace("%MinPlayers%", min_players + ""));
					waitingtime = 16;
				}
			}		
		}, 15, 20);
	}
	
	public void LobbyTask() {
		status = ArenaStatus.LOBBY;
		this.lobbytask = Bukkit.getScheduler().scheduleSyncRepeatingTask(KingOfTheHillPlus.m, new Runnable() {
			@Override
			public void run() {
				lobbytime--;
				
				if(arena_players.size() < min_players) {
					lobbytime = MainConfig.lobbytime;
					Bukkit.getScheduler().cancelTask(lobbytask);
					WaitingTask();
				}
				
				if(lobbytime == 60 || lobbytime == 30 || lobbytime == 15 || lobbytime == 10 || lobbytime == 5 || lobbytime == 4 || lobbytime == 3 || lobbytime == 2 || lobbytime == 1) {
					broadcastArena(MessagesConfig.prefix + MessagesConfig.lobby_message.replace("%Time%", "" + lobbytime));
				}
				if(lobbytime == 0) {
					int i = 0;
					for(Player all : arena_players) {
						i ++;
						all.getInventory().clear();
						all.teleport(LocationConfig.getLocation(arena_name + ".Spawn." + i));
					}
					Bukkit.getScheduler().cancelTask(lobbytask);
					GameTask();
				}
			}	
		}, 15, 20);
	}
	
	public void GameTask() {
		status = ArenaStatus.INGAME;
		this.gametask = Bukkit.getScheduler().scheduleSyncRepeatingTask(KingOfTheHillPlus.m, new Runnable() {
			@Override
			public void run() {
				gametime--;
				if(gametime == 300 || gametime == 240 || gametime == 180 || gametime == 120) {
					broadcastArena(MessagesConfig.prefix + MessagesConfig.game_message_minutes.replace("%Time%", "" + gametime/60));
				}
				if(gametime == 60 || gametime == 30 || gametime == 15 || gametime == 10 || gametime == 5 || gametime == 4 || gametime == 3 || gametime == 2 || gametime == 1) {
					broadcastArena(MessagesConfig.prefix + MessagesConfig.game_message_seconds.replace("%Time%", "" + gametime));
				}
				if(gametime == 0) {
					
					
					Bukkit.getScheduler().cancelTask(gametask);
					EndTask();
				}
			}		
		}, 15, 20);
	}
	
	public void EndTask() {
		status = ArenaStatus.ENDGAME;
		if(winner != null) {
			broadcastArena(MessagesConfig.prefix + MessagesConfig.broadcast_winner.replace("%Player%", winner.getName()));
		} else {
			broadcastArena(MessagesConfig.prefix + MessagesConfig.broadcast_tie);
		}
		this.endtask = Bukkit.getScheduler().scheduleSyncRepeatingTask(KingOfTheHillPlus.m, new Runnable() {
			@Override
			public void run() {
				endtime--;
				if(endtime == 10 || endtime == 5 || endtime == 4 || endtime == 3 || endtime == 2 || endtime == 1) {
					broadcastArena(MessagesConfig.prefix + MessagesConfig.end_message.replace("%Time%", "" + endtime));
				}
				if(endtime == 0) {
					for(Player all : arena_players) {
						leaveArena(all);
					}
					arena_players.clear();
					Bukkit.getScheduler().cancelTask(endtask);
					loadTimes();
					WaitingTask();
					loadTimes();
					winner = null;
					status = ArenaStatus.WAITING;
				}
			}		
		}, 15, 20);
	}

}
