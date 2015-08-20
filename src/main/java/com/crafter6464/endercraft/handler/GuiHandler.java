package com.crafter6464.endercraft.handler;

import com.crafter6464.endercraft.machines.ender_furnace.ContainerEnderFurnace;
import com.crafter6464.endercraft.machines.ender_furnace.GuiEnderFurnace;
import com.crafter6464.endercraft.machines.ender_furnace.TileEntityEnderFurnace;
import com.crafter6464.endercraft.reference.GuiIDs;
import com.crafter6464.endercraft.utility.Log;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity te = world.getTileEntity(new BlockPos(x,y,z));
		Log.info("ID: "+ID);
		switch(ID){
		//returns a container
		case GuiIDs.Ender_FurnaceID:return new ContainerEnderFurnace(player.inventory,te);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		Log.info("ID: "+ID);
		TileEntity te = world.getTileEntity(new BlockPos(x,y,z));
		//returns a gui
		switch(ID){
		case GuiIDs.Ender_FurnaceID:return new GuiEnderFurnace("ender_furnace",new ContainerEnderFurnace(player.inventory,te),"Ender Furnace",(TileEntityEnderFurnace) te);
		
		}
		
	
		return null;
	}

}
