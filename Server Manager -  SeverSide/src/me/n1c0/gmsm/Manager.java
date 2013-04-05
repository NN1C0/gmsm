package me.n1c0.gmsm;

import me.n1c0.gmsm.network.TCPServer;
import me.n1c0.gmsm.player.PlayerOnlineStateHandle;

import org.bukkit.plugin.java.JavaPlugin;

public class Manager  extends JavaPlugin  {
	
	private PlayerOnlineStateHandle playerJoinHandle;
	private TCPServer tcpServer = new TCPServer();
	private Thread tcpServerThread = new Thread(tcpServer);
	public void onEnable() {
		registerEvents();
		System.out.println("Manager wurde geladen");
		
		try {
			tcpServerThread.start();
		} catch (Exception e) {
			System.out.println("Problem mit dem TCPServer");
			e.printStackTrace();
		}
		System.out.println("TcpServer fertig");
		
	}
	
	public void onDisable() {
		tcpServer.killServer();
	}

	
	private void registerEvents() {
		playerJoinHandle = new PlayerOnlineStateHandle(this);
	}
}
