package de.supremedev.main;

import org.bukkit.plugin.java.JavaPlugin;

import de.supremedev.cmds.setcoins;
import de.supremedev.mysql.MySQL;

public class main extends JavaPlugin {
	
	public static String Prefix = "§8┃ §dCoins §8» §7";
	public static String NoPerms = Prefix + "Befehl wurde nicht gefunden."; 


	
	@Override
	public void onEnable() {
		MySQL.connect();
		MySQL.createTable();
		
		getCommand("coins").setExecutor(new setcoins());

	}
	
	@Override
	public void onDisable() {
		MySQL.disconnect();
	}

}