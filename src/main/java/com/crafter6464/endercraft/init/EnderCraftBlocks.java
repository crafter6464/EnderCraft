package com.crafter6464.endercraft.init;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

import com.crafter6464.endercraft.EnderCraft;
import com.crafter6464.endercraft.block.BlockEC;
import com.crafter6464.endercraft.machines.ender_furnace.Ender_Furnace;


public class EnderCraftBlocks {
	static CreativeTabs tab = EnderCraft.endercrafttab;
	public static Block ender_furnace_off;
	public static Block ender_furnace_on;
	
	public static void init(){
		ender_furnace_off=new Ender_Furnace(false,"ender_furnace_off").setCreativeTab(tab);
		ender_furnace_on=new Ender_Furnace(true,"ender_furnace_on");

	}
	
}
