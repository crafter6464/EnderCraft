package com.crafter6464.endercraft.proxy;

import net.minecraftforge.fml.common.registry.GameRegistry;

import com.crafter6464.endercraft.block.BlockEC;
import com.crafter6464.endercraft.block.ECBlockTileEntity;
import com.crafter6464.endercraft.item.ItemEC;
import com.crafter6464.endercraft.machines.ender_furnace.TileEntityEnderFurnace;

public class CommonProxy implements IProxy {

	@Override
	public void registerRenders() {
ItemEC.registerRenders();	
BlockEC.registerRenders();
ECBlockTileEntity.registerRenders();
	}

	@Override
	public void registerTileEntities() {
GameRegistry.registerTileEntity(TileEntityEnderFurnace.class, "ender_furnace_off");		
	}

}
