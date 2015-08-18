package com.crafter6464.endercraft.handler;

import java.io.File;

import com.crafter6464.endercraft.reference.Reference;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigurationHandler {
	static boolean testValue = true;
	public static Configuration config;
	public static void init(File file){
		
		
if(config == null){
	config = new Configuration(file);
	loadConfig();
}

		
	}
	
	
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event){
		if(event.modID.equalsIgnoreCase(Reference.MOD_ID)){
			loadConfig();
		}
	}
	public static void loadConfig(){
		 testValue = config.getBoolean("test",Configuration.CATEGORY_GENERAL, true, "this is a test value");
		if(config.hasChanged()){
			config.save();
		}
		
	}
}