package com.crafter6464.endercraft.handler;

import java.io.File;

import com.crafter6464.endercraft.reference.Reference;
import com.crafter6464.endercraft.utility.Log;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigurationHandler {
	public static int enderFurnaceSpeed = 100;
	public static Configuration config;
	public static void init(File file){
		Log.info("called constructor");
		
if(config == null){
	config = new Configuration(file);
	Log.info("called constructor null");
	loadConfig();
}

		
	}
	
	
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event){
		Log.info("called changed");
		if(event.modID.equalsIgnoreCase(Reference.MOD_ID)){
			loadConfig();
		}
	}
	public static void loadConfig(){
		Log.info("called load");
		boolean testBool  = config.getBoolean("test", Configuration.CATEGORY_GENERAL, true, "this is a test");
		 enderFurnaceSpeed = config.getInt("Ender Furnace Speed","Machines", 100, 10, 10000, "How long per 1 item to be smelted");
		if(config.hasChanged()){
			config.save();
		}
		
	}
}
