
package kside.BeyondCMD.Commands;

import kside.BeyondCMD.BeyondCMD;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.tehkode.permissions.PermissionUser;

public class SpawnCommand implements CommandExecutor
{

	public static BeyondCMD plugin;

	public SpawnCommand(BeyondCMD instance)
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
		if (cmd.getName().equalsIgnoreCase("spawn"))
		{
			if (args.length > 2 && (player == null || player.hasPermission("bcmd.spawn.self")))
			{
				sender.sendMessage(ChatColor.RED + "Too many variables!");
			}
			else if (args.length == 0)
			{
				if (player == null)
				{
					sender.sendMessage(ChatColor.RED + "You're the Server, stupid! Type the person (and world)!");
				}
				else if (player.hasPermission("bcmd.spawn.self"))
				{
					player.teleport(player.getWorld().getSpawnLocation());
				}
			}
			else if (args.length == 1)
			{
				if ((player == null || player.hasPermission("bcmd.spawn.others")) && Bukkit.getServer().getPlayer(args[0]) != null)
				{
					Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
					targetPlayer.teleport(targetPlayer.getWorld().getSpawnLocation());
				}
				else if (player == null || player.hasPermission("bcmd.spawn.drifters"))
				{
					Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
					boolean isInGroup = ((PermissionUser) targetPlayer).inGroup("Drifter", true);
					if (isInGroup == true)
						targetPlayer.teleport(plugin.getServer().getWorld(args[1]).getSpawnLocation());
				}
				else if ((player == null || (player.hasPermission("bcmd.spawn.others") || player.hasPermission("bcmd.spawn.drifters"))))
				{
					sender.sendMessage(args[0] + " is not online");
				}
			}
			else if (args.length == 2)
			{
				if (Bukkit.getServer().getPlayer(args[0]) != null && Bukkit.getServer().getWorld(args[1]) != null)
				{
					if (player == null || player.hasPermission("bcmd.spawn.others"))
					{
						Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
						targetPlayer.teleport(plugin.getServer().getWorld(args[1]).getSpawnLocation());
					}
				}
				else
				{
					sender.sendMessage(args[0] + " is not online or " + args[1] + " is not a valid world!");
				}
			}
			else
			{
				sender.sendMessage(ChatColor.RED + "You do not have my blessing to use " + cmd.getName());
			}
		}
		return false;
	}
}
