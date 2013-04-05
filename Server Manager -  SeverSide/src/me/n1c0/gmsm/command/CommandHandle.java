package me.n1c0.gmsm.command;

import java.util.List;

import me.n1c0.gmsm.commands.GetPlayerList;

public class CommandHandle {
	public List<String> commandHandle(String command) {
		List<String> result = null;
 		
		switch(command) {
		case "getPlayerList" :
			result = new GetPlayerList().getPlayerList();
			break;
		}
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i));
		}		
		System.out.println("Schleife fertig");
		return result;
	}
}
