package me.n1c0.gmsm.network;

import java.sql.ResultSet;
import java.util.logging.Logger;

import lib.PatPeter.SQLibrary.SQLite;

public class Authentification {
	public boolean checkCredentials(String username, String password) {
		SQLite sqlite = new SQLite(Logger.getLogger("Minecraft"), "GMSM", "plugins\\Manager\\", "Manager.sqlite");
		boolean checkReturn = false;
		String passwordFromDB = null;
		
		try {
			sqlite.open();
			ResultSet returnFromDB = null;
			returnFromDB = sqlite.query("SELECT PASSWORD FROM Users WHERE USERNAME = '"+username+"';");
			returnFromDB.next();
			passwordFromDB = returnFromDB.getString("password");
			if(password.equals(passwordFromDB)) {
				checkReturn = true;
			}
			sqlite.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkReturn;
	}
}
