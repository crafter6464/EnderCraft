package com.crafter6464.endercraft.client;

import java.util.List;

import com.crafter6464.endercraft.handler.ConfigurationHandler;
import com.crafter6464.endercraft.reference.Reference;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

public class ModGuiConfig extends GuiConfig {

	public ModGuiConfig(GuiScreen guiscreen){
		super(guiscreen , new ConfigElement(ConfigurationHandler.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), Reference.MOD_ID, false,
				false, GuiConfig.getAbridgedConfigPath(ConfigurationHandler.config.toString()));
		// TODO Auto-generated constructor stub
	}
}
