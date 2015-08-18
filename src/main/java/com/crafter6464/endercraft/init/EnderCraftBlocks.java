package com.crafter6464.endercraft.init;

import net.minecraft.block.Block;

import com.crafter6464.endercraft.block.BlockEC;


public class EnderCraftBlocks {
public static Block test;
	
	
	public static void init(){
		test = new BlockEC("test").setHardness(2.0F);
	}
	
}
