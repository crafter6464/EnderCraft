package com.crafter6464.endercraft.client;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class TabEnderCraft extends CreativeTabs {

	public TabEnderCraft() {
		super("Ender Craft");
	}

	@Override
	public Item getTabIconItem() {
		return Items.ender_pearl;
	}

}
