
package kside.BeyondCMD.Commands;

import kside.BeyondCMD.BeyondCMD;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.tehkode.permissions.PermissionUser;

public class ClearCommand implements CommandExecutor
{

	public static BeyondCMD plugin;

	public ClearCommand(BeyondCMD instance)
	{

		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{

		Player player = null;
		if (sender instanceof Player)
		{
			player = (Player) sender;
		}
		if (cmd.getName().equalsIgnoreCase("clear"))
		{
			if ((args.length > 1 && (player == null || player.hasPermission("bcmd.clear.self")))){
				sender.sendMessage(ChatColor.RED + "Too many variables!");
			}
			else if (args.length == 0)
			{
				if (player == null)
				{
					sender.sendMessage(ChatColor.RED + "You're the Server, stupid! Type the person!");
				}
				else if (player.hasPermission("bcmd.clear.self"))
				{
					player.getInventory().clear();
				}
			}		
		else if (args.length == 1)
		{
			if ((player == null || player.hasPermission("bcmd.clear.others")) && Bukkit.getServer().getPlayer(args[0]) != null)
			{
				Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
				targetPlayer.getInventory().clear();
			}
			else if (player == null || player.hasPermission("bcmd.clear.drifters"))
			{
				Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
				boolean isInGroup = ((PermissionUser) targetPlayer).inGroup("Drifter", true);
				if (isInGroup == true)
					targetPlayer.getInventory().clear();
			}
			else
			{
				sender.sendMessage(args[0] + " is not online");
			}
		}
		}
		return false;
	}
}
