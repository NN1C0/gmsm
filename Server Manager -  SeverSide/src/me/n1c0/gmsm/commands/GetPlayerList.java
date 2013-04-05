package me.n1c0.gmsm.commands;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import lib.PatPeter.SQLibrary.SQLite;

public class GetPlayerList {
	public List<String> getPlayerList() {
		List<String> players = new ArrayList<String>();
		ResultSet result;
		SQLite sqlite = new SQLite(Logger.getLogger("Minecraft"), "GMSM", "plugins\\Manager\\", "Manager.sqlite");
		sqlite.open();
		try {
			result = sqlite.query("SELECT NAME, ONLINE_STATE FROM Players;");
			while(result.next()) {
				players.add(result.getString("NAME"));
				players.add(result.getString("ONLINE_STATE"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		return players;
	}
}
