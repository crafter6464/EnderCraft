package com.crafter6464.endercraft.client;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.DummyConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

import com.crafter6464.endercraft.handler.ConfigurationHandler;
import com.crafter6464.endercraft.reference.Reference;

public class ModGuiConfig extends GuiConfig {
	public static List<IConfigElement> elementList = new ArrayList<IConfigElement>();

	public ModGuiConfig(GuiScreen guiscreen) {
		super(guiscreen, getConfigElements(), Reference.MOD_ID, false, false,
				GuiConfig.getAbridgedConfigPath(ConfigurationHandler.config
						.toString()));

		// TODO Auto-generated constructor stub
	}

	private static List<IConfigElement> getConfigElements() {
		// add config categories here//
		int maxSize = 2; /* change this when you add more elements*/
		final String pre = "endercraft.configCategory.";
		// params: key to look for, name to be displayed, tooltip name to be
		// displayed
		if (elementList.size() < maxSize) {
			elementList.add(categoryElement("general", "General", pre
					+ "general"));
			elementList.add(categoryElement("machines", "Machines", pre
					+ "machines"));
		}

		return elementList;
	}

	private static IConfigElement categoryElement(String category, String name,
			String tooltip_key) {
		return new DummyConfigElement.DummyCategoryElement(name, tooltip_key,
				new ConfigElement(ConfigurationHandler.config
						.getCategory(category)).getChildElements());
	}
}
