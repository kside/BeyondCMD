
package kside.BeyondCMD.Commands;

import kside.BeyondCMD.BeyondCMD;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WeatherCommand implements CommandExecutor
{

	public static BeyondCMD plugin;

	public WeatherCommand(BeyondCMD instance)
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
		if (cmd.getName().equalsIgnoreCase("weather"))
		{
			if (args.length > 2 && (player == null || player.hasPermission("bcmd.weather")))
			{
				sender.sendMessage(ChatColor.RED + "Too many variables!");
			}
			else if (args.length == 0)
			{
				if (player == null)
				{
					sender.sendMessage(ChatColor.RED + "You're the Server, stupid! Type sunny or stormy & which world!");
				}
				else if (player.hasPermission("bcmd.weather"))
				{
					sender.sendMessage(ChatColor.RED + "Type Type sunny or stormy (& which world)!");
				}
			}
			else if (args.length == 1)
			{
				if (player != null && player.hasPermission("bcmd.weather"))
				{
					if (args[0] == "sunny")
					{
						player.getWorld().setStorm(true);
					}
					else if (args[0] == "stormy")
					{
						player.getWorld().setStorm(false);
					}
				}
				else if (player == null)
				{
					sender.sendMessage(ChatColor.RED + "Type Type sunny or stormy & which world)!");
				}
				else if (player.hasPermission("bcmd.weather"))
				{
					sender.sendMessage(ChatColor.RED + "Type Type sunny or stormy (& which world)!");
				}
			}
			else if (args.length == 2)
			{
				if (player != null && player.hasPermission("bcmd.weather"))
				{
					if (args[0] == "sunny")
					{
						player.getWorld().setStorm(true);
					}
					else if (args[0] == "stormy")
					{
						player.getWorld().setStorm(false);
					}
				}
				if (player == null || player.hasPermission("bcmd.weather"))
				{
					World world = Bukkit.getServer().getWorld(args[1]);
					if (world != null)
					{
						if (args[0] == "sunny")
						{
							world.setStorm(true);
						}
						else if (args[0] == "stormy")
						{
							world.setStorm(false);
						}
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
