package com.crafter6464.endercraft.block;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.crafter6464.endercraft.reference.Reference;

public abstract class ECBlockTileEntity extends BlockContainer {
protected static String name;

	@Override
	public abstract TileEntity createNewTileEntity(World worldIn, int meta);

	static List TEblockList = new ArrayList<ECBlockTileEntity>();
	
	public ECBlockTileEntity(String name) {
		super(Material.iron);
		this.name=name;
		this.setUnlocalizedName(name);
		GameRegistry.registerBlock(this, name);
		
		
		TEblockList.add(this);
	}
	
	public static void registerRenders(){
		for(int i = 0;i<TEblockList.size();++i){
			registerRender((Block)TEblockList.get(i));
		}
	}

	public static void registerRender(Block block){
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + name, "inventory"));
	}
	
	public static String getFormattedName(Block block){
		return block.getUnlocalizedName().substring(5);
	}
	@Override
	public int getRenderType() {
		// TODO Auto-generated method stub
		return 3;
	}
	
}
