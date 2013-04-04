package me.n1c0.gmsm.player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import lib.PatPeter.SQLibrary.SQLite;
import me.n1c0.gmsm.Manager;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerOnlineStateHandle implements Listener {
	
	private Manager plugin;
	private SQLite sqlite = new SQLite(Logger.getLogger("Minecraft"), "GMSM", "plugins\\Manager\\", "Manager.sqlite");

	public PlayerOnlineStateHandle(Manager plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) throws SQLException {
		String Playername = event.getPlayer().getDisplayName(); 
		if(!sqlite.open()) {
			sqlite.open();
		}
		ResultSet resultSetBan = sqlite.query("SELECT REASON FROM BanList WHERE Name = '"+Playername+"';");
		if(resultSetBan != null) {
			String reason = resultSetBan.getString("REASON");
			event.getPlayer().kickPlayer("You are Banned because: " + reason);
		}
		
		
		ResultSet resultSetState = sqlite.query("SELECT ONLINE_STATE FROM Players WHERE NAME = '"+Playername+"';");
		if(resultSetState != null) {
				sqlite.query("UPDATE Players SET ONLINE_STATE = 'true' WHERE NAME = '"+Playername+"';");				
		}
		sqlite.query("INSERT INTO Players (NAME, ONLINE_STATE) VALUES ('"+Playername+"', 'true');");
		sqlite.close();
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) throws SQLException {
		String Playername = event.getPlayer().getDisplayName();
		if(!sqlite.open()) {
			sqlite.open();
		}
		
		sqlite.query("UPDATE Players SET ONLINE_STATE = 'false' WHERE NAME = '"+Playername+"';");
		sqlite.close();
	}
}
