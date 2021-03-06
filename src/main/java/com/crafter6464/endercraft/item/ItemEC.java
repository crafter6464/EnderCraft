package com.crafter6464.endercraft.item;

import java.util.ArrayList;
import java.util.List;

import com.crafter6464.endercraft.EnderCraft;
import com.crafter6464.endercraft.reference.Reference;
import com.crafter6464.endercraft.utility.Log;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemEC extends Item {
static List itemList = new ArrayList<ItemEC>();
	public ItemEC(String name){
		this.setUnlocalizedName(name);
		
		GameRegistry.registerItem(this, name);
		this.setCreativeTab(EnderCraft.endercrafttab);
		itemList.add(this);
		
	}
	public static void registerRenders(){
		for(int i = 0; i<itemList.size();++i){
			registerRender((Item) itemList.get(i));
		}
	
	}
	
	public static void registerRender(Item item){
		
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));		
	}
	
}
