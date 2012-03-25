package kside.BeyondCMD.Commands;

import kside.BeyondCMD.BeyondCMD;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class KitCommand implements CommandExecutor
{

	public static BeyondCMD plugin;

	public KitCommand(BeyondCMD instance)
	{

		plugin = instance;
	}
 
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		return false;
	}
}
