package de.supremedev.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.supremedev.main.main;
import de.supremedev.mysql.CoinsManager;




public class setcoins implements CommandExecutor {
	public boolean onCommand(CommandSender cs, Command cmd, String arg2, String[] args) {
    if (cs instanceof Player) {
    	Player player = (Player)cs;
      
    	if (player.hasPermission("coinsapi.*")) {
    		if (args.length == 3) {
    			Player target = Bukkit.getPlayerExact(args[1]);
    			int Coins = Integer.valueOf(args[2]).intValue();
    			
    			if (args[0].equalsIgnoreCase("add")) {
    				if (Coins < 0) {
        					player.sendMessage(main.Prefix + "Du musst einen §eHöheren Betrag §7angeben!");
        					return true;
    				} 
            
    				CoinsManager.addCoins(target.getUniqueId().toString(), Coins);
    					target.sendMessage(main.Prefix + "Dir wurden §e" + Coins + " §7Coins hinzugefügt");
    			} else if (args[0].equalsIgnoreCase("set")) {
    				if (Coins < 0) {
    						player.sendMessage(main.Prefix + "Du musst einen §eHöheren Betrag §7angeben!");
    						return true;
    				} 
    				
    				CoinsManager.setCoins(target.getUniqueId().toString(), Coins);
    					target.sendMessage(main.Prefix + "Deine Coins wurden auf §e" + Coins + " §7gesetzt!");
    			} else if (args[0].equalsIgnoreCase("remove")) {
    				if (Coins < 0) {
	    					player.sendMessage(main.Prefix + "Du musst einen §eHöheren Betrag §7angeben!");
	    					return true;
    				} 
    				
    				CoinsManager.removeCoins(target.getUniqueId().toString(), Coins);
    					target.sendMessage(main.Prefix +"Dir wurden §e" + Coins + " §7Euro §centfernt§7.");
    			} 
    		} else if (args.length == 1) {
    			if (args[0].equalsIgnoreCase("?")) {
    				player.sendMessage(main.Prefix + "/coins <add/set/remove> <Name> <Coins>");
    				player.sendMessage(main.Prefix + "/coins see <Name>");
    				player.sendMessage(main.Prefix + "/coins");
    			} else if (args[0].equalsIgnoreCase("help")) {
    				player.sendMessage(main.Prefix + "/coins <add/set/remove> <Name> <Coins>");
    				player.sendMessage(main.Prefix + "/coins see <Name>");
    				player.sendMessage(main.Prefix + "/coins");
    			}
    		} else if (args.length == 2) {
    			if (args[0].equalsIgnoreCase("see")) {
    				Player target = Bukkit.getPlayerExact(args[1]);
    					player.sendMessage(main.Prefix + "Der Spieler §e" + target.getName() + " §7hat §e" + CoinsManager.getCoins(target.getUniqueId().toString()) + " §7Coins.");
    			}
    		} else {
    				player.sendMessage(main.Prefix + "Du hast §e" + CoinsManager.getCoins(player.getUniqueId().toString()) + " §7Coins.");
    		} 
    	} else {
			player.sendMessage(main.Prefix + "Du hast §e" + CoinsManager.getCoins(player.getUniqueId().toString()) + " §7Coins.");
    	} 
    }  return false;
  }
}