package me.spigot.SuperPyroManiac;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main 
extends JavaPlugin
implements Listener {
	@Override
	public void onEnable(){
		Bukkit.getPluginManager().registerEvents(this, this);
		registerConfig();
		getLogger().info("Custom Join Messages successfully enabled.");
	}
	@Override
	public void onDisable(){
		getLogger().info("Custom Join Messages successfully disabled.");
	}
	public void registerConfig(){
		getConfig().options().copyDefaults(true);
		saveConfig();
		
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent p){
		String joinMsg = ChatColor.translateAlternateColorCodes('&', getConfig().getString("Join"));
		Player pl = p.getPlayer();
		joinMsg = joinMsg.replace("[player]",  pl.getName());	
	    if(getConfig().getStringList("Staff").contains(pl.getName())) {
	    String staffJoin = ChatColor.translateAlternateColorCodes('&', getConfig().getString("StaffJoin"));
	    staffJoin = staffJoin.replace("[player]", pl.getName());
	    p.setJoinMessage(staffJoin);
	    }
	    else{	    
		p.setJoinMessage(joinMsg);		
		if(!pl.hasPlayedBefore()){
			pl.sendMessage(ChatColor.GREEN + "Welcome for the first time! To get right into the action:");
			pl.sendMessage(ChatColor.GOLD + "/warp SB " + ChatColor.GREEN + "For Creative");
			pl.sendMessage(ChatColor.GOLD + "/warp Wild " + ChatColor.GREEN + "For Survival");
			pl.sendMessage(ChatColor.GREEN + "Or you may just explore from the spawn!");
		}
		}
	}
	public void onPlayerQuit(PlayerQuitEvent p){
		String quitMsg = ChatColor.translateAlternateColorCodes('&', getConfig().getString("Leave"));
		Player pl = p.getPlayer();
		quitMsg = quitMsg.replace("[player]",  pl.getName());
	    if(getConfig().getStringList("Staff").contains(pl.getName())) {
	    String staffQuit = ChatColor.translateAlternateColorCodes('&', getConfig().getString("StaffLeave"));
	    staffQuit = staffQuit.replace("[player]", pl.getName());
	    p.setQuitMessage(staffQuit);
	    }
	    else{
		p.setQuitMessage(quitMsg);
	    }
	}
	
}
