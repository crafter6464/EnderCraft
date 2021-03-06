package com.crafter6464.endercraft.block;

import java.util.ArrayList;
import java.util.List;

import com.crafter6464.endercraft.EnderCraft;
import com.crafter6464.endercraft.reference.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockEC extends Block {
static List blockList = new ArrayList<BlockEC>();
	public BlockEC(String name) {
		super(Material.rock);
		this.setUnlocalizedName(name);
		GameRegistry.registerBlock(this, name);
		this.setCreativeTab(EnderCraft.endercrafttab);
		blockList.add(this);
	}
	
	public static void registerRenders(){
		for(int i = 0;i<blockList.size();++i){
			registerRender((Block)blockList.get(i));
		}
	}

	public static void registerRender(Block block){
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
	
	public static String getFormattedName(Block block){
		return block.getUnlocalizedName().substring(5);
	}
}
