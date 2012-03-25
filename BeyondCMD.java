
package kside.BeyondCMD;

import java.util.logging.Logger;

import kside.BeyondCMD.Commands.ClearCommand;
import kside.BeyondCMD.Commands.KitCommand;
import kside.BeyondCMD.Commands.SpawnCommand;
import kside.BeyondCMD.Commands.WeatherCommand;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class BeyondCMD extends JavaPlugin
{

	Logger log;
private ClearCommand clear;
private KitCommand kit;
private SpawnCommand spawn;
private WeatherCommand weather;
	private PluginDescriptionFile pdfFile = this.getDescription();

	public void onEnable()
	{
		log = this.getLogger();
		log.info(pdfFile.getName() + "has been enabled!");
		clear = new ClearCommand(this);
		kit = new KitCommand(this);
		spawn = new SpawnCommand(this);
		weather = new WeatherCommand(this);
		getCommand("spawn").setExecutor(clear);
		getCommand("kit").setExecutor(kit);
		getCommand("spawn").setExecutor(spawn);
		getCommand("weather").setExecutor(weather);
	}

	public void onDisable()
	{

		this.saveConfig();
		log = this.getLogger();
		log.info(pdfFile.getName() + " is now disabled!");
	}
}
