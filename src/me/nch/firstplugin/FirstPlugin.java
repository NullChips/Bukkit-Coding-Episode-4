package me.nch.firstplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class FirstPlugin extends JavaPlugin implements Listener {

	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("firstcommand")) {

			if (!sender.hasPermission("firstplugin.command")) {
				sender.sendMessage(ChatColor.RED + "You don't have permission to perform this command.");
				return true;
			}

			if (sender instanceof Player) {

				Player p = (Player) sender;

				if (args.length == 0) {
					p.setHealth(20);
					p.getInventory().clear();
					p.sendMessage("You have healed yourself and cleared your inventory.");
					return true;
				}

				String targetName = args[0];
				Player target = Bukkit.getServer().getPlayer(targetName);

				if (target == null) {
					p.sendMessage(ChatColor.RED + "That player is not online!");
					return true;
				}

				target.setHealth(20);
				target.getInventory().clear();
				target.sendMessage("You have been healed and your inventory has been cleared by " + p.getName());
				p.sendMessage("You have cleared the inventory of and healed " + target.getName());

			} else {
				sender.sendMessage(ChatColor.RED + "The console cannot use this command.");
			}
		}
		return true;
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		e.getPlayer().sendMessage(ChatColor.RED + "You have broken a block.");
	}

}
